package simulation.map.pathfinding;

import simulation.map.GameMap;
import simulation.map.Position;

import java.util.ArrayList;
import java.util.List;

public class NeighborsFinder {
    private final GameMap gameMap;

    public NeighborsFinder(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    List<Position> getNeighbors(Position position) {
        return findNeighbors(position);
    }

    private List<Position> findNeighbors(Position current) {
        List<Position> neighbors = new ArrayList<>();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int x = current.getX();
        int y = current.getY();

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isValid(newX, newY)) {
                neighbors.add(new Position(newX, newY));
            }
        }

        return neighbors;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x <= gameMap.getWidth() && y >= 0 && y <= gameMap.getHeight();
    }
}
