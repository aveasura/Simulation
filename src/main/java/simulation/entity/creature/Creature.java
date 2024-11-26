package simulation.entity.creature;

import simulation.entity.Entity;
import simulation.map.GameMap;
import simulation.map.Position;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int hp, int speed, Position position) {
        super(position);
        this.hp = hp;
        this.speed = speed;
    }

    abstract protected void makeMove(Position currentPosition, GameMap gameMap);

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
