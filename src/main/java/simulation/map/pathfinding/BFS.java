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

    public Entity bfs (Position start, Class<? extends Entity> targetClass) {
//        visitedCells.clear();

        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        NeighborsFinder nf = new NeighborsFinder(gameMap);
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            Entity entity = gameMap.getEntityAt(current);
            if (entity != null && targetClass.isInstance(entity)) {
                return entity;
            }

//            visitedCells.add(current);

            for (Position neighbor : nf.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }
}
