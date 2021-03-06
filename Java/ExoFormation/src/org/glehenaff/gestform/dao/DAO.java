/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

/**
 *
 * @author gwenole
 */
public abstract class DAO<T> implements IDAO<T> {

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost/exoform?noAccessToProcedureBodies=true";
    private final String USER = "root";
    private final String PWD = "admin";
    private static Connection connect = null;

    protected Connection getConnection() {
        if (connect == null || isClosed()) {
            try {
                try {
                    Class.forName(DRIVER).newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                connect = DriverManager.getConnection(URL, USER, PWD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connect;
    }

    private boolean isClosed() {
        if (connect != null) {
            try {
                return connect.isClosed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    protected void close(Connection connection, PreparedStatement[] prepares, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            for (PreparedStatement p : prepares) {
                if (p != null) {
                    p.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void close(Connection connection, PreparedStatement prepare, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        close(connection, prepare);
    }

    protected void close(Connection connection, PreparedStatement prepare) {
        try {
            if (prepare != null) {
                prepare.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
