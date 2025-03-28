package dev.denux;

import dev.denux.account.AccountManager;
import dev.denux.database.ConnectionManager;
import dev.denux.database.DatabaseManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager(new DatabaseManager());
        AccountManager accountManager = new AccountManager(connectionManager);

        accountManager.login();
    }
}