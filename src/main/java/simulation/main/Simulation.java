package simulation.main;

import simulation.entity.EntityType;
import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;
import simulation.entity.resource.Grass;
import simulation.factory.*;
import simulation.map.GameMap;
import simulation.map.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// todo доработать Simulation / разбить методы по классам
public class Simulation {
    private static int stepCounter;

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10);
        MovementController movementController = new MovementController(gameMap);

        run(gameMap, movementController);
    }

    private static void run(GameMap gameMap, MovementController movementController) {
        Random random = new Random();

        List<Creature> creatures = new ArrayList<>();
        List<Entity> staticEntities = new ArrayList<>(); // Камни, трава, горы и т.п.

        for (int i = 0; i < 25; i++) {
            Position position;

            // Генерация случайной позиции с проверкой на занятость клетки
            do {
                int randomX = random.nextInt(0, gameMap.getWidth());
                int randomY = random.nextInt(0, gameMap.getHeight());
                position = new Position(randomX, randomY);
            } while (gameMap.getEntityAt(position) != null);

            // Создаём случайную сущность
            EntityType randomType = EntityType.values()[random.nextInt(EntityType.values().length)];
            Entity entity = EntityFactory.createEntity(randomType, position.getX(), position.getY());

            // Добавляем в соответствующий список и размещаем на карте
            if (entity instanceof Creature creature) {
                creatures.add(creature);
            } else {
                staticEntities.add(entity);
            }

            gameMap.placeEntity(entity.getPosition(), entity);
        }

        while (simulationIsRunning(creatures, staticEntities)) {
            cleanConsole();
            gameMap.displayMap();

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

            if (!creatureMoved) {
                System.out.println("Animals cannot reach their target - an insurmountable obstacle stands in their way.");
                break;
            }

            ++stepCounter;
            sleep(1000);
        }

        System.out.println("Game is over. Number of steps: " + stepCounter);
    }

    private static boolean simulationIsRunning(List<Creature> creatures, List<Entity> staticEntities) {
        // Проверка живых травоядных, хищников и наличия травы
        boolean hasHerbivores = creatures.stream().anyMatch(creature -> creature instanceof Herbivore && creature.isExist());
        boolean hasPredators = creatures.stream().anyMatch(creature -> creature instanceof Predator && creature.isExist());
        boolean hasGrass = staticEntities.stream().anyMatch(entity -> entity instanceof Grass && entity.isExist());

        // Игра заканчивается, если хотя бы одного вида нет
        printMissingEntities(hasHerbivores, hasPredators, hasGrass);
        return hasHerbivores && hasPredators && hasGrass;
    }

    private static void printMissingEntities(boolean hasHerbivores, boolean hasPredators, boolean hasGrass) {
        if (!hasHerbivores) System.out.println("No herbivores on the map");
        if (!hasPredators) System.out.println("No predators on the map ");
        if (!hasGrass) System.out.println("No grass on the map");
    }

    private static void cleanConsole() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
