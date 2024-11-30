package simulation.entity.resource;

import simulation.entity.Entity;
import simulation.map.Position;

public class Grass extends Entity {

    public Grass(Position position) {
        super(position);
    }

    @Override
    public char getSymbol() {
        return 'G';
    }
}
