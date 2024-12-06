package simulation.entity;

import simulation.map.Position;

public abstract class Entity {
    protected Position position;
    private boolean isExist;

    public Entity(int x, int y) {
        this.position = new Position(x, y);
        isExist = true;
    }

    public abstract char getEntitySymbol();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}