package simulation.entity.creature;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.Collections;
import java.util.List;

public abstract class Creature extends Entity {
    private boolean isAlive = true;

    private static final int MIN_PATH_LENGTH = 1;

    private final int speed;
    private int hp;

    public Creature(int hp, int speed, int x, int y) {
        super(x, y);
        this.hp = hp;
        this.speed = speed;
    }

    protected abstract Class<? extends Entity> getTargetClass();

    public List<Position> findTarget(MovementController movementController) {
        BFS search = new BFS(movementController.getGameMap());
        List<Position> path = search.bfs(this.getPosition(), this.getTargetClass());

        if (path != null && !path.isEmpty() && this.isAlive()) {
            return path;
        }

        return Collections.emptyList();
    }

    public void makeMove(List<Position> path, MovementController movementController) {
        if (!this.isAlive()) return;

        if (path == null || path.isEmpty()) {
            System.out.println(this.getSymbol() + ": no path found to move");
            return;
        }

        int maxSteps = Math.min(speed, path.size() - MIN_PATH_LENGTH);
        Position nextPosition = path.get(maxSteps);

        movementController.moveEntity(this, nextPosition);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "speed=" + speed +
                ", hp=" + hp +
                '}';
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
        if (!alive) {
            System.out.println(this.getSymbol() + ": has died.");
        }
    }
}
