package simulation.map;

import simulation.entity.Entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private final Map<Position, Entity> grid = new HashMap<>();

    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Entity getEntityAt(Position position) {
        return grid.get(position);
    }

    public boolean addEntity(Position position, Entity entity) {
        if (!grid.containsKey(position)) {
            grid.put(position, entity);
            return true;
        }
        return false;
    }

    public void removeEntity(Position position) {
        grid.remove(position);
    }

    public void updateEntityPosition(Entity entity, Position newPosition) {
        grid.remove(entity.getPosition());
        grid.put(newPosition, entity);
        entity.setPosition(newPosition);
    }

    public boolean isValidPosition(Position newPosition) {
        return newPosition.getX() >= 0 && newPosition.getX() < width &&
                newPosition.getY() >= 0 && newPosition.getY() < height;
    }

    public void displayMap() {
        Position position = new Position(0, 0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                position.setX(x);
                position.setY(y);
                if (grid.containsKey(position)) {
                    Entity entity = grid.get(position);
                    System.out.print(entity.getSymbol() + "  ");
                } else {
                    System.out.print("•  ");
                }
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Position, Entity> getGrid() {
        return Collections.unmodifiableMap(grid);
    }
}
