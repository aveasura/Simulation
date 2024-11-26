package simulation.main;

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

        // Predators
        Position predatorPos = new Position(3,4);
        Predator predator = new Predator(1,1,1, predatorPos);
        //=========================================================================

        // Herbivores
        Position herbivorePos1 = new Position(8, 5);
        Herbivore herbivore1 = new Herbivore(3,1, herbivorePos1);
        //=========================================================================

        // Tree
        Position treePos = new Position(3, 6);
        Tree tree = new Tree(treePos);
        //=========================================================================

        // Grass
        Position grassPos1 = new Position(4, 9);
        Position grassPos2 = new Position(8, 1);
        Grass grass1 = new Grass(grassPos1);
        Grass grass2 = new Grass(grassPos2);
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
        gameMap.spawn(predatorPos, predator);
        gameMap.spawn(herbivorePos1, herbivore1);

        gameMap.spawn(treePos, tree);

        gameMap.spawn(rockPos1, rock1);
        gameMap.spawn(rockPos2, rock2);

        gameMap.spawn(grassPos1, grass1);
        gameMap.spawn(grassPos2, grass2);

        gameMap.spawn(rockPos3, rock3);
        //=========================================================================

        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        gameMap.displayMap();

        predator.searchTarget(gameMap);
        herbivore1.searchTarget(gameMap);

        gameMap.displayMap();

        herbivore1.searchTarget(gameMap);
        gameMap.showMap();


    }
}
