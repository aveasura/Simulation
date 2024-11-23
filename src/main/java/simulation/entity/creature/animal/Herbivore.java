package simulation.entity.creature.animal;

import simulation.entity.creature.Creature;
import simulation.map.Position;

public class Herbivore extends Creature {

    public Herbivore(int hp, int speed, Position position) {
        super(hp, speed, position);
    }

    @Override
    public void makeMove() {
        System.out.println("herbivore move");
    }

    @Override
    public char getSymbol() {
        return 'H';
    }
}
