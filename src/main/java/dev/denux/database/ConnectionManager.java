package dev.denux.database;

import dev.denux.utils.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {

    private final Scanner stdIn = new Scanner(System.in);
    private final int CONNECTION_TIMEOUT = 5;

    private final String connectionURL;
    private Credentials credentials;
    private Connection connection;

    public ConnectionManager(DatabaseManager databaseManager) {
        this.connectionURL = databaseManager.getConnectionURL();
    }

    private void askForCredentials() {
        System.out.println("Enter your Database credentials");
        System.out.print("Username: ");
        String username = stdIn.nextLine();
        System.out.print("Password: ");
        String password = stdIn.nextLine();

        this.credentials = new Credentials(username, password);
    }

    private Connection connect() {
        Credentials credentials = this.getCredentials();


        try {
            Connection connection = DriverManager.getConnection(this.connectionURL, credentials.username(), credentials.password());
            if (!connection.isValid(CONNECTION_TIMEOUT)) {
                throw new SQLException("Connection timeout");
            }

            return connection;
        } catch (SQLException exception) {
            System.err.println("Failed to connect to the database.");
            System.err.println("Check your username and password.");
        }

        askForCredentials();

        return connect();
    }

    public Credentials getCredentials() {
        if (credentials == null) {
            askForCredentials();
        }

        return credentials;
    }

    public Connection getConnection() {
        try {
            if (connection == null || !connection.isValid(CONNECTION_TIMEOUT)) {
                connection = connect();
            }
        } catch (Throwable _) {
            connection = connect();
        }

        return connection;
    }
}
