package simulation.map;

import simulation.entity.Entity;

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

    // Получение сущности по клетке
    public Entity getEntityAt(Position position) {
        return grid.get(position);
    }

    // Добавление сущности на клетку
    public boolean addEntity(Position position, Entity entity) {
        // Клетка уже занята другим существом
        if (grid.containsKey(position)) {
            return false;
        } else {
            // Добавляем существо на клетку
            grid.put(position, entity);
            return true;
        }
    }

    // Удаление сущности с клетки
    public void removeEntity(Position position) {
        grid.remove(position);
    }

    public void updateEntityPosition(Entity entity, Position newPosition) {
        grid.remove(entity.getPosition());
        grid.put(newPosition, entity);
    }

    public boolean isValidPosition(Position newPosition) {
        return newPosition.getX() >= 0 && newPosition.getX() < width &&
                newPosition.getY() >= 0 && newPosition.getY() < height;
    }

    public boolean isOccupied(Position position) {
        return grid.containsKey(position);
    }

    public void displayMap() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                Position position = new Position(x, y);
                if (grid.containsKey(position)) {
                    Entity entity = grid.get(position);
                    System.out.print(entity.getSymbol() + "  "); // символ сущности
                } else {
                    System.out.print("•  "); // Пустая клетка
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
        return grid;
    }
}
