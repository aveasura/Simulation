package simulation.entity.landscape;

import simulation.entity.Entity;
import simulation.map.Position;

public class Rock extends Entity {

    public Rock(Position position) {
        super(position);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }
}
