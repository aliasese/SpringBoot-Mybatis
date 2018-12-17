package com.cnebula.ill;

import java.sql.*;

public class JDBCUtil {
    private static final String URL = "jdbc:postgresql://192.168.2.52:5432/folio_db?characterEncoding=utf-8";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USERNAME = "folio_admin";
    private static final String PASSWORD = "calis123";
    private static Connection connection = null;

    static {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //System.exit(1);
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
