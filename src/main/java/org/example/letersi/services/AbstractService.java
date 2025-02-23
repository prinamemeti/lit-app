package org.example.letersi.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractService {

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/letersidb", "root", "teknollogjia");
    }
}
