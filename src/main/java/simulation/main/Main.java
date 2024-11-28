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

public class Main {
    public static void main(String[] args) {

        // tmp main

        GameMap gameMap = new GameMap(10, 10);
        MovementController movementController = new MovementController(gameMap);

        // Predators
        Position predatorPos = new Position(3,4);
        Predator predator = new Predator(1,1,1, predatorPos);
        //=========================================================================

        // Herbivores
        Position herbivorePos1 = new Position(8, 5);
        Herbivore herbivore1 = new Herbivore(3,1, herbivorePos1);
        //=========================================================================

        // Tree
        Position treePos = new Position(4, 6);
        Tree tree = new Tree(treePos);
        //=========================================================================

        // Grass
        Position grassPos1 = new Position(0, 9);
        Grass grass1 = new Grass(grassPos1);
        //=========================================================================

        // Rock
        Position rockPos1 = new Position(5, 7);
        Position rockPos2 = new Position(1, 4);

        Rock rock1 = new Rock(rockPos1);
        Rock rock2 = new Rock(rockPos2);

        Position rockPos3 = new Position(0, 0);
        Entity rock3 = new Rock(rockPos3);
        //=========================================================================

        // Spawner
        gameMap.addEntity(predatorPos, predator);
        gameMap.addEntity(herbivorePos1, herbivore1);

        gameMap.addEntity(treePos, tree);

        gameMap.addEntity(rockPos1, rock1);
        gameMap.addEntity(rockPos2, rock2);

        gameMap.addEntity(grassPos1, grass1);

        gameMap.addEntity(rockPos3, rock3);
        //=========================================================================



        // test
        gameMap.displayMap();
        for (int i = 0; i < 15 ; i++) {
            predator.searchTarget(movementController);
            herbivore1.searchTarget(movementController);
            gameMap.displayMap();
        }

        predator.searchTarget(movementController);
        herbivore1.searchTarget(movementController);

    }
}
