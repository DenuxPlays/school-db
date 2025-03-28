package dev.denux.database;

import org.jetbrains.annotations.NotNull;

public class DatabaseManager {
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "school";

    @NotNull
    public String getConnectionURL() {
        return String.format("jdbc:mariadb://%s:%d/%s", HOST, PORT, DATABASE);
    }
}
