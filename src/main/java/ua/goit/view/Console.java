package ua.goit.view;

import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public String read() {
        return scanner.nextLine();
    }

    public void write(String message) {
        System.out.println(message);
    }
}
