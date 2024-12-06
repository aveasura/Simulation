package simulation.controller;

import simulation.entity.Entity;
import simulation.map.GameMap;
import simulation.map.Position;

import java.io.IOException;

public class DisplayController {
    private static final String CLEAR_CONSOLE = "\033[H\033[2J";
    private static final String BORDER_SYMBOL = "===";

    public void displayMap(GameMap gameMap) {
        printBorder(gameMap.getWidth());

        Position position = new Position(0, 0);
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                position.setCoordinates(x, y);

                Entity entity = gameMap.getEntityAt(position);

                if (entity != null) {
                    System.out.print(entity.getEntitySymbol() + "  ");
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }

        printBorder(gameMap.getWidth());
    }

    private void printBorder(int width) {
        System.out.println(BORDER_SYMBOL.repeat(Math.max(0, width)));
    }

    // Метод для очистки консоли
    public void cleanConsole() {
        if (System.getProperty("os.name").contains("Windows")) {
            clearWindowsConsole();
        } else {
            clearUnixConsole();
        }
    }

    private void clearWindowsConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearUnixConsole() {
        System.out.print(CLEAR_CONSOLE);
        System.out.flush();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    public void printMissingEntities(boolean hasHerbivores, boolean hasPredators, boolean hasGrass) {
        if (!hasHerbivores) printMessage("No herbivores on the map");
        if (!hasPredators) printMessage("No predators on the map ");
        if (!hasGrass) printMessage("No grass on the map");
    }

    public void printNoSpaceAvailable() {
        printMessage("The maximum number of entities cannot be more than the number of cells on the map.");
    }

    public void printTargetUnavailable() {
        printMessage("Animals cannot reach their target - an insurmountable obstacle stands in their way.");
    }

    public void printSimulationIsOver(int stepCounter) {
        printMessage("Game is over. Number of steps: " + stepCounter);
    }
}
