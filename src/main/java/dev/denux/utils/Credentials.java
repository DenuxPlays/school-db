package dev.denux.utils;

import java.util.Scanner;

public record Credentials(String username, String password) {

    public static Credentials getFromStdIn(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new Credentials(username, password);
    }
}
