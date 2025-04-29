package dev.denux;

import dev.denux.account.AccountManager;
import dev.denux.database.ConnectionManager;
import dev.denux.database.DatabaseManager;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        configureLogger();

        ConnectionManager connectionManager = new ConnectionManager(new DatabaseManager());
        AccountManager accountManager = new AccountManager(connectionManager);

        accountManager.login();
    }

    private static void configureLogger() {
        LogManager.getLogManager().reset();
        Logger.getLogger("").setLevel(Level.OFF);
    }
}