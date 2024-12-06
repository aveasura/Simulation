package simulation.entity.resource;

import simulation.entity.Entity;

public class Grass extends Entity {
    private static final char GRASS_SYMBOL = 'G';

    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public char getEntitySymbol() {
        return GRASS_SYMBOL;
    }
}