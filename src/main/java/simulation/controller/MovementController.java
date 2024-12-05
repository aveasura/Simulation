package simulation.controller;

import simulation.entity.Entity;
import simulation.entity.creature.animal.Attackable;
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
        } else {
            handleInteraction(entity, newPosition);
        }
    }

    private void handleInteraction(Entity entity, Position newPosition) {
        Entity target = gameMap.getEntityAt(newPosition);

        if (entity instanceof Herbivore herbivore) {
            handleHerbivoreInteraction(herbivore, target, newPosition);

        } else if (entity instanceof Predator predator) {
            handlePredatorInteraction(predator, target, newPosition);

        }
    }

    private void handleHerbivoreInteraction(Herbivore herbivore, Entity target, Position position) {
        if (target instanceof Grass grass && herbivore.isExist()) {
            gameMap.removeEntity(position);
            grass.setExist(false);
        }
    }

    private void handlePredatorInteraction(Predator predator, Entity target, Position position) {
        if (target instanceof Attackable attackable) {
            predator.attack(attackable);

            if (target instanceof Herbivore herbivore && !herbivore.isExist()) {
                gameMap.removeEntity(position);
            }
        }
    }

    private boolean isValidMove(Position newPosition) {
        return gameMap.isValidPosition(newPosition) &&
                gameMap.getEntityAt(newPosition) == null;
    }
}
