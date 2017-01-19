/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
        if (connect == null) {
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
}
