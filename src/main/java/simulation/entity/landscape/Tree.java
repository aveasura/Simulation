package simulation.entity.landscape;

import simulation.entity.Entity;
import simulation.map.Position;

public class Tree extends Entity {

    public Tree(Position position) {
        super(position);
    }

    @Override
    public char getSymbol() {
        return 'T';
    }
}
