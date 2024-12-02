package simulation.main;

import simulation.controller.MovementController;
import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;
import simulation.entity.landscape.Rock;
import simulation.entity.landscape.Tree;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.List;

// Temporary class
public class TempMain {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10);
        MovementController movementController = new MovementController(gameMap);

        run(gameMap, movementController, 20);
    }

    // todo разбить по классам/методам, переделать логику.
    private static void run(GameMap gameMap, MovementController movementController, int step) {
        Creature predator1 = new Predator(1, 1, 1, 3, 4);
        Creature predator2 = new Predator(1, 1, 1, 3,2);
        Creature predator3 = new Predator(1, 1, 1, 7,9);

        Creature herbivore1 = new Herbivore(1, 1, 8,5);
        Creature herbivore2 = new Herbivore(1, 1, 1,5);

        Entity tree = new Tree(4,6);

        Entity grass1 = new Grass(1,7);
        Entity grass2 = new Grass(9,3);
        Entity grass3 = new Grass(4,8);

        Entity rock1 = new Rock(5,7);
        Entity rock2 = new Rock(1,4);
        Entity rock3 = new Rock(0,0);

        gameMap.addEntity(predator1.getPosition(), predator1);
        gameMap.addEntity(predator2.getPosition(), predator2);
        gameMap.addEntity(predator3.getPosition(), predator3);

        gameMap.addEntity(herbivore1.getPosition(), herbivore1);
        gameMap.addEntity(herbivore2.getPosition(), herbivore2);

        gameMap.addEntity(tree.getPosition(), tree);

        gameMap.addEntity(rock1.getPosition(), rock1);
        gameMap.addEntity(rock2.getPosition(), rock2);
        gameMap.addEntity(rock3.getPosition(), rock3);

        gameMap.addEntity(grass1.getPosition(), grass1);
        gameMap.addEntity(grass2.getPosition(), grass2);
        gameMap.addEntity(grass3.getPosition(), grass3);

        gameMap.displayMap();
        System.out.println("==============================");

        for (int i = 0; i < step; i++) {

            List<Position> predatorList1 = predator1.findTarget(movementController);
            List<Position> predatorList2 = predator2.findTarget(movementController);
            List<Position> predatorList3 = predator3.findTarget(movementController);

            List<Position> herbivoreList1 = herbivore1.findTarget(movementController);
            List<Position> herbivoreList2 = herbivore2.findTarget(movementController);

            if ((predatorList1 != null && !predatorList1.isEmpty()
                    || herbivoreList1 != null && !herbivoreList1.isEmpty())

                    || herbivoreList2 != null && !herbivoreList2.isEmpty()
                    && predatorList2 != null && !predatorList2.isEmpty()

                    && predatorList3 != null && !predatorList3.isEmpty()) {

                predator1.makeMove(predatorList1, movementController);
                herbivore1.makeMove(herbivoreList1, movementController);

                predator2.makeMove(predatorList2, movementController);
                herbivore2.makeMove(herbivoreList2, movementController);

                predator3.makeMove(predatorList3, movementController);

                gameMap.displayMap();

                System.out.println("==============================");
            }
        }
    }
}
