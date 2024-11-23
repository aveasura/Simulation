package simulation.entity.landscape;

import simulation.entity.Entity;
import simulation.map.Position;

public class Rock extends Entity {
    private final String name;

    public Rock(Position position) {
        super(position);
        this.name = "Rock";
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

    @Override
    public String toString() {
        return name;
    }
}
