package simulation.entity.resource;

import simulation.entity.Entity;
import simulation.map.Position;

public class Grass extends Entity {
    private final String name;

    public Grass(Position position) {
        super(position);
        this.name = "Grass";
    }

    @Override
    public char getSymbol() {
        return 'G';
    }

    @Override
    public String toString() {
        return name;
    }
}
