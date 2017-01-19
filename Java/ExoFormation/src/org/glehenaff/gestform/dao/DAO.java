/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connect = DriverManager.getConnection(URL, USER, PWD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connect;
    }
}
