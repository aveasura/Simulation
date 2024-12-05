package simulation.factory;

import simulation.entity.creature.animal.Predator;

public class PredatorFactory {
    public static Predator createPredator(int x, int y) {

        // default values
        int hp = 1;
        int speed = 1;
        int attackPower = 1;

        return new Predator(hp, speed, attackPower, x, y);
    }
}
