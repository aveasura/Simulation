package simulation.entity.landscape;

import simulation.entity.Entity;
import simulation.map.Position;

public class Tree extends Entity {
    private final String name;

    public Tree(Position position) {
        super(position);
        this.name = "Tree";
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public char getSymbol() {
        return 'T';
    }
}
