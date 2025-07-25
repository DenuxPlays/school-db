package dev.denux.account;

import dev.denux.database.ConnectionManager;
import dev.denux.utils.Credentials;
import dev.denux.utils.PasswordHasher;
import dev.denux.utils.RandomStringGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {

    private final Scanner stdIn = new Scanner(System.in);

    private final ConnectionManager connectionManager;

    public AccountManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void login() {
        Connection connection = connectionManager.getConnection();

        System.out.println("Login with our Account.");
        Credentials credentials = Credentials.getFromStdIn(this.stdIn);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM T_Accounts WHERE username = ?");
            preparedStatement.setString(1, credentials.username());

            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                System.out.println("Account not found! Please try again.");
                retryLoginOrRegister();
                return;
            }

            String hashedPassword = rs.getString("password");
            if (PasswordHasher.verifyPassword(credentials.password(), hashedPassword)) {
                System.out.println("Login successful!");
                return;
            }

            System.out.println("Invalid password! Please try again.");
            retryLoginOrRegister();
        } catch (SQLException exception) {
            System.err.println("A Database error occurred: " + exception.getMessage());
        }

    }

    public void retryLoginOrRegister() {
        System.out.println("Would you like to login or register? (login/register)");
        String response = stdIn.nextLine();

        if (response.equalsIgnoreCase("login")) {
            login();
        } else if (response.equalsIgnoreCase("register")) {
            register();
        } else {
            System.out.println("Invalid response. Please try again.");
            retryLoginOrRegister();
        }
    }

    public void register() {
        Credentials credentials = Credentials.getFromStdIn(this.stdIn);

        Connection connection = connectionManager.getConnection();
        try {
            final String hashedPassword = PasswordHasher.hashPassword(credentials.password());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO T_Accounts (p_account_id, username, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, RandomStringGenerator.generateRandomString(10));
            preparedStatement.setString(2, credentials.username());
            preparedStatement.setString(3, hashedPassword);

            preparedStatement.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException exception) {
            System.err.println("A Database error occurred: " + exception.getMessage());
        }
    }
}
