package simulation.factory;

import simulation.entity.creature.animal.Herbivore;

public class HerbivoreFactory {
    public static Herbivore createHerbivore(int x, int y) {

        // default values
        int hp = 1;
        int speed = 1;

        return new Herbivore(hp, speed, x, y);
    }
}
