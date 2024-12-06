package simulation.manager;

import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");

            // Потребляем неверный ввод
            scanner.next();
        }
        int value = scanner.nextInt();

        // Съедаем символ новой строки
        scanner.nextLine();
        return value;
    }
}