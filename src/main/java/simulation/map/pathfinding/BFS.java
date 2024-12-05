package simulation.map.pathfinding;

import simulation.entity.Entity;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.*;

public class BFS {
    private final GameMap gameMap;

    public BFS(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Position> bfs(Position start, Class<? extends Entity> targetClass) {
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        Map<Position, Position> parentMap = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (isTarget(current, targetClass)) {
                return reconstructPath(parentMap, current);
            }

            for (Position neighbor : getValidNeighbors(gameMap, current, start)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Position> getValidNeighbors(GameMap gameMap, Position current, Position start) {
        Entity startEntity = gameMap.getEntityAt(start);
        NeighborsFinder nf = new NeighborsFinder();

        return nf.getNeighbors(gameMap, current, startEntity);
    }

    private boolean isTarget(Position position, Class<? extends Entity> targetClass) {
        Entity entity = gameMap.getEntityAt(position);

        return targetClass.isInstance(entity);
    }

    private List<Position> reconstructPath(Map<Position, Position> map, Position end) {
        List<Position> path = new ArrayList<>();

        Position currentKey = end;
        while (currentKey != null) {
            path.add(currentKey);
            currentKey = map.get(currentKey);
        }
        Collections.reverse(path);

        return path;
    }
}
