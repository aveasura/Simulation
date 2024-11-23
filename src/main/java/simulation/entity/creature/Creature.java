package simulation.entity.creature;

import simulation.entity.Entity;
import simulation.map.Position;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    public Creature(int hp, int speed, Position position) {
        super(position);
        this.hp = hp;
        this.speed = speed;
    }

    abstract protected void makeMove();

    @Override
    public String toString() {
        return "Creature{" +
                "speed=" + speed +
                ", hp=" + hp +
                '}';
    }
}
