package simulation.map.pathfinding;

import simulation.entity.Entity;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.ArrayList;
import java.util.List;

public class NeighborsFinder {

    List<Position> getNeighbors(GameMap gameMap, Position position, Entity entity) {
        return findNeighbors(gameMap, position, entity);
    }

    private List<Position> findNeighbors(GameMap gameMap, Position current, Entity entity) {
        List<Position> neighbors = new ArrayList<>();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {1, 1}, {-1, 1}};

        int x = current.getX();
        int y = current.getY();

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            Position newPosition = new Position(newX, newY);
            Entity neighborEntity = gameMap.getGrid().get(newPosition);

            if (isValid(gameMap, newX, newY)) {

                // Если это травоядное, и соседняя клетка содержит траву
                if (entity instanceof Herbivore && neighborEntity instanceof Grass) {
                    neighbors.add(newPosition);
                }

                // Если это хищник, и соседняя клетка содержит травоядное
                else if (entity instanceof Predator && neighborEntity instanceof Herbivore) {
                    neighbors.add(newPosition);
                }

                // Если на клетке нет сущности, добавляем её в список соседей
                else if (neighborEntity == null) {
                    neighbors.add(newPosition);
                }
            }
        }

        return neighbors;
    }

    private boolean isValid(GameMap gameMap, int x, int y) {
        return x >= 0 && x < gameMap.getWidth() &&
                y >= 0 && y < gameMap.getHeight();
    }
}
