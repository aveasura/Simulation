package simulation.factory;

import simulation.entity.landscape.Tree;

public class TreeFactory {
    public static Tree createTree(int x, int y) {

        return new Tree(x, y);
    }
}
