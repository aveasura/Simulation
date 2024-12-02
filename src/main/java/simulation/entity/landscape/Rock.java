package simulation.entity.landscape;

import simulation.entity.Entity;

public class Rock extends Entity {

    public Rock(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }
}
