package simulation.main;

import simulation.controller.DisplayController;
import simulation.controller.MovementController;
import simulation.manager.EntityManager;
import simulation.manager.SimulationManager;
import simulation.map.GameMap;

import java.util.Scanner;


public class Simulation {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(20, 10);
        MovementController movementController = new MovementController(gameMap);
        DisplayController displayController = new DisplayController();
        EntityManager entityManager = new EntityManager();

        SimulationManager simulationManager =
                new SimulationManager(gameMap, movementController, displayController, entityManager);

        simulationManager.startSimulation(20);

        // Ожидание нажатия клавиши перед закрытием
        System.out.println("Press Enter to exit...");
        new Scanner(System.in).nextLine();
    }
}