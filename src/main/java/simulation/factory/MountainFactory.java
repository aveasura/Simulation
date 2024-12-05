package simulation.factory;

import simulation.entity.landscape.Mountain;

public class MountainFactory {
    public static Mountain createRock(int x, int y) {

        return new Mountain(x, y);
    }
}
