package utils;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, enter a number: ");
        }
        return scanner.nextInt();
    }

    public static String getString() {
        return scanner.nextLine();
    }
}
