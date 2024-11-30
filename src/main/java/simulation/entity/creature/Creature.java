package simulation.entity.creature;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.map.Position;

import java.util.List;

public abstract class Creature extends Entity {
    private static final int MIN_PATH_LENGTH = 1;

    private final int speed;
    private int hp;


    public Creature(int hp, int speed, Position position) {
        super(position);
        this.hp = hp;
        this.speed = speed;
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

    public boolean isAlive() {
        return getHp() > 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
