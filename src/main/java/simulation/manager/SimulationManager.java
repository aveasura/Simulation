package simulation.manager;

import simulation.controller.DisplayController;
import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.List;

public class SimulationManager {
    private static int stepCounter;

    private final GameMap gameMap;
    private final MovementController movementController;
    private final DisplayController displayController;
    private final EntityManager entityManager;
    private final InputManager inputManager;

    public SimulationManager(GameMap gameMap, MovementController movementController, DisplayController displayController,
                             EntityManager entityManager, InputManager inputManager) {
        this.gameMap = gameMap;
        this.movementController = movementController;
        this.displayController = displayController;
        this.entityManager = entityManager;
        this.inputManager = inputManager;
    }

    public void startSimulation() {
        System.out.println("Enter the number of entities to place on the map.");
        int numberOfRandomEntities = inputManager.readInt();

        if (!isSpaceAvailable(numberOfRandomEntities)) {
            displayController.printNoSpaceAvailable();
            return;
        }

        EntityManager.EntityCollections entities = entityManager.createAndPlaceEntities(gameMap, numberOfRandomEntities);
        List<Creature> creatures = entities.getCreatures();
        List<Entity> staticEntities = entities.getStaticEntities();

        while (simulationIsRunning(creatures, staticEntities)) {
            ++stepCounter;

            displayController.cleanConsole();
            displayController.displayMap(gameMap);

            if (!moveCreatures(creatures)) {
                displayController.printTargetUnavailable();
                break;
            }
            sleep(1000);
        }

        displayController.cleanConsole();
        displayController.displayMap(gameMap);
        printMissingEntities(creatures, staticEntities);
        displayController.printSimulationIsOver(stepCounter);

        // Ожидание нажатия enter перед закрытием приложения.
        inputManager.readLine();
    }

    private boolean moveCreatures(List<Creature> creatures) {
        boolean creatureMoved = false;
        for (Creature creature : creatures) {
            if (creature.isExist()) {
                List<Position> path = creature.findTarget(gameMap);

                if (!path.isEmpty()) {
                    creatureMoved = true;
                    creature.makeMove(path, movementController);
                }
            }
        }
        return creatureMoved;
    }

    private boolean simulationIsRunning(List<Creature> creatures, List<Entity> staticEntities) {
        // Проверка живых травоядных, хищников и наличия травы
        return checkMissingEntities(creatures, staticEntities); // просто проверяем на наличие всех сущностей
    }

    private boolean checkMissingEntities(List<Creature> creatures, List<Entity> staticEntities) {
        boolean hasHerbivores = creatures.stream().anyMatch(creature -> creature instanceof Herbivore && creature.isExist());
        boolean hasPredators = creatures.stream().anyMatch(creature -> creature instanceof Predator && creature.isExist());
        boolean hasGrass = staticEntities.stream().anyMatch(entity -> entity instanceof Grass && entity.isExist());

        // Игра заканчивается, если хотя бы одного вида нет
        displayController.printMissingEntities(hasHerbivores, hasPredators, hasGrass);
        return hasHerbivores && hasPredators && hasGrass;
    }

    // Вынес метод для вывода информации о недостающих сущностях
    private void printMissingEntities(List<Creature> creatures, List<Entity> staticEntities) {
        checkMissingEntities(creatures, staticEntities); // просто вызываем один и тот же метод для вывода информации
    }

    private boolean isSpaceAvailable(int numberOfEntities) {
        int numberOfCell = gameMap.getWidth() * gameMap.getHeight();
        return numberOfEntities <= numberOfCell;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error when trying to sleep", e);
        }
    }
}
