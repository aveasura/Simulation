package simulation.entity;

import simulation.map.Position;

public abstract class Entity {
    private Position position;
    private boolean isExist;

    public Entity(Position position) {
        this.position = position;
        this.isExist = true;
    }

    public abstract char getSymbol(); // Символ для отображения

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
