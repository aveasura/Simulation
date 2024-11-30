package simulation.controller;

import simulation.entity.Entity;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;

public class MovementController {
    private final GameMap gameMap;

    public MovementController(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void moveEntity(Entity entity, Position newPosition) {

        if (isValidMove(newPosition)) {

            gameMap.updateEntityPosition(entity, newPosition);
            System.out.println(entity.getSymbol() + ": is move to " + newPosition);

        } else {
            handleInteraction(entity, newPosition);
        }
    }

    private void handleInteraction(Entity entity, Position newPosition) {
        Entity target = gameMap.getEntityAt(newPosition);

        if (entity instanceof Herbivore herbivore && target instanceof Grass grass && herbivore.isAlive()) {
            herbivore.eatGrass(grass);
            gameMap.removeEntity(newPosition);

        } else if (entity instanceof Predator predator && target instanceof Herbivore herbivore) {
            predator.attack(herbivore);

            if (!herbivore.isAlive()) {
                gameMap.removeEntity(newPosition);
                System.out.println(predator.getSymbol() + " is killed: " + herbivore.getSymbol());
                // gameMap.updateEntityPosition(predator, newPosition); // Хищник занимает клетку жертвы после победы
            }
        }
    }

    private boolean isValidMove(Position newPosition) {
        return gameMap.isValidPosition(newPosition) && gameMap.getEntityAt(newPosition) == null;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}