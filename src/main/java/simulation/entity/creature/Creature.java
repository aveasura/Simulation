package simulation.entity.creature;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.Collections;
import java.util.List;

public abstract class Creature extends Entity {
    private static final int MIN_PATH_LENGTH = 1;

    protected final int speed;
    protected int hp;

    public Creature(int hp, int speed, int x, int y) {
        super(x, y);
        this.hp = hp;
        this.speed = speed;
    }

    protected abstract Class<? extends Entity> getTargetClass();

    public List<Position> findTarget(GameMap gameMap) {
        BFS search = new BFS(gameMap);
        List<Position> path = search.bfs(this.getPosition(), this.getTargetClass());

        if (path != null && !path.isEmpty() && this.isExist()) {
            return path;
        }

        return Collections.emptyList();
    }

    public void makeMove(List<Position> path, MovementController movementController) {
        if (!this.isExist()) return;

        // no path found to move
        if (path == null || path.isEmpty()) {
            return;
        }

        int maxSteps = Math.min(speed, path.size() - MIN_PATH_LENGTH);
        Position nextPosition = path.get(maxSteps);

        movementController.moveEntity(this, nextPosition);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "isExist=" + isExist() +
                ", speed=" + speed +
                ", hp=" + hp +
                ", position=" + position +
                '}';
    }
}