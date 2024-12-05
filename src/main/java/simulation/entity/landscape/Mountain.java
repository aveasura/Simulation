package simulation.entity.landscape;

import simulation.entity.Entity;

public class Mountain extends Entity {
    private static final char MOUNTAIN_SYMBOL = 'M';

    public Mountain(int x, int y) {
        super(x, y);
    }

    @Override
    public char getEntitySymbol() {
        return MOUNTAIN_SYMBOL;
    }
}