package simulation.entity.creature;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.map.Position;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int hp, int speed, Position position) {
        super(position);
        this.hp = hp;
        this.speed = speed;
    }

    abstract protected void makeMove(MovementController movementController);

    @Override
    public String toString() {
        return "Creature{" +
                "speed=" + speed +
                ", hp=" + hp +
                '}';
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
