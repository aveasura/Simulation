package simulation.main;

import simulation.controller.MovementController;
import simulation.entity.Entity;
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
        Position predatorPos1 = new Position(3, 4);
        Position predatorPos2 = new Position(3, 2);
        Position predatorPos3 = new Position(7, 9);
        Predator predator1 = new Predator(1, 1, 1, predatorPos1);
        Predator predator2 = new Predator(1, 1, 1, predatorPos2);
        Predator predator3 = new Predator(1, 1, 1, predatorPos3);

        Position herbivorePos1 = new Position(8, 5);
        Position herbivorePos2 = new Position(1, 5);
        Herbivore herbivore1 = new Herbivore(10, 1, herbivorePos1);
        Herbivore herbivore2 = new Herbivore(10, 1, herbivorePos2);

        Position treePos = new Position(4, 6);
        Entity tree = new Tree(treePos);

        Position grassPos1 = new Position(0, 9);
        Position grassPos2 = new Position(9, 3);
        Position grassPos3 = new Position(4, 8);
        Entity grass1 = new Grass(grassPos1);
        Entity grass2 = new Grass(grassPos2);
        Entity grass3 = new Grass(grassPos3);

        Position rockPos1 = new Position(5, 7);
        Position rockPos2 = new Position(1, 4);
        Position rockPos3 = new Position(0, 0);
        Entity rock1 = new Rock(rockPos1);
        Entity rock2 = new Rock(rockPos2);
        Entity rock3 = new Rock(rockPos3);

        gameMap.addEntity(predatorPos1, predator1);
        gameMap.addEntity(predatorPos2, predator2);
        gameMap.addEntity(predatorPos3, predator3);

        gameMap.addEntity(herbivorePos1, herbivore1);
        gameMap.addEntity(herbivorePos2, herbivore2);

        gameMap.addEntity(treePos, tree);

        gameMap.addEntity(rockPos1, rock1);
        gameMap.addEntity(rockPos2, rock2);
        gameMap.addEntity(rockPos3, rock3);

        gameMap.addEntity(grassPos1, grass1);
        gameMap.addEntity(grassPos2, grass2);
        gameMap.addEntity(grassPos3, grass3);

        gameMap.displayMap();

        for (int i = 0; i < step; i++) {

            List<Position> predatorList1 = predator1.searchTarget(movementController);
            List<Position> predatorList2 = predator2.searchTarget(movementController);
            List<Position> predatorList3 = predator3.searchTarget(movementController);

            List<Position> herbivoreList1 = herbivore1.searchTarget(movementController);
            List<Position> herbivoreList2 = herbivore2.searchTarget(movementController);

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
            }
        }
    }
}
