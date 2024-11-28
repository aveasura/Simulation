package simulation.controller;

import simulation.entity.Entity;
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
            entity.setPosition(newPosition);

        } else {
            System.out.println("Невозможно переместить сущность на позицию: " + newPosition);
        }
    }

    private boolean isValidMove(Position newPosition) {
        return gameMap.isValidPosition(newPosition) && gameMap.getEntityAt(newPosition) == null;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}