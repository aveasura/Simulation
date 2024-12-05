package simulation.entity.landscape;

import simulation.entity.Entity;

public class Tree extends Entity {
    private static final char TREE_SYMBOL = 'T';

    public Tree(int x, int y) {
        super(x, y);
    }

    @Override
    public char getEntitySymbol() {
        return TREE_SYMBOL;
    }
}