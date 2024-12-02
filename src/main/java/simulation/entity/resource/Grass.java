package simulation.entity.resource;

import simulation.entity.Entity;

public class Grass extends Entity {

    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return 'G';
    }
}
