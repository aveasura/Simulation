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

    public void moveEntity(Entity entity, Position newPosition, Entity target) {
        if (isValidMove(newPosition)) {

            gameMap.updateEntityPosition(entity, newPosition);
            entity.setPosition(newPosition);

        } else {

            if (entity instanceof Herbivore herbivore && newPosition.equals(target.getPosition()) && herbivore.isAlive()) {
                if (!herbivore.eatGrass((Grass) target)) {
                    gameMap.removeEntity(newPosition);
                    System.out.println("Herbivore eat grass");
                }
            }

            if (entity instanceof Predator predator && newPosition.equals(target.getPosition())) {
                int herbivoreHp = predator.attack((Herbivore) target);

                if (herbivoreHp < 1) {
                    gameMap.removeEntity(newPosition);
                    System.out.println("Predator killed Herbivore");
                }
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