package simulation.factory;

import simulation.entity.resource.Grass;

public class GrassFactory {
    public static Grass createGrass(int x, int y) {

        return new Grass(x, y);
    }
}
