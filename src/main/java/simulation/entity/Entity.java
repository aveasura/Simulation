package simulation.entity;

import simulation.map.Position;

public abstract class Entity {
    protected Position position;
    protected boolean isExist;

    public Entity(int x, int y) {
        this.position = new Position(x, y);
        this.isExist = true;
    }

    public abstract char getSymbol();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
