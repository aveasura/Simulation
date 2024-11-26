package simulation.map.pathfinding;

import simulation.entity.Entity;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.*;

public class BFS {
//    private List<Position> visitedCells = new ArrayList<>(); // посещенные клетки [для отслеживания пути]

    private final GameMap gameMap;

    public BFS(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Position> bfs(Position start, Class<? extends Entity> targetClass) {
        Map<Position, Position> parentMap = new HashMap<>();

        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        NeighborsFinder nf = new NeighborsFinder(gameMap);
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            Entity entity = gameMap.getEntityAt(current);
            if (entity != null && targetClass.isInstance(entity)) {
                return reconstructPath(parentMap, entity);
            }

            for (Position neighbor : nf.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return null;
    }

    // Метод для воспроизведения пути
    public List<Position> reconstructPath(Map<Position, Position> map, Entity entity) {
        List<Position> path = new ArrayList<>();
        Position currentKey = entity.getPosition();

        while (map.containsKey(currentKey)) {
            path.add(0, currentKey);
            currentKey = map.get(currentKey);
        }

        path.add(0, currentKey);

        return path;
    }
}
