package simulation.entity.creature;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.map.Position;

import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int hp, int speed, Position position) {
        super(position);
        this.hp = hp;
        this.speed = speed;
    }

    public void makeMove(List<Position> path, MovementController movementController) {
        if (path == null || path.isEmpty()) {
            System.out.println("Нет пути для перемещения.");
            return;
        }

        int speed = this.getSpeed();
        int nextPositionIndex = Math.min(speed, path.size() - 1);
        Position nextPosition = path.get(nextPositionIndex);
        Entity target = movementController.getGameMap().getEntityAt(path.get(path.size() - 1));

        movementController.moveEntity(this, nextPosition, target);
    }

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
