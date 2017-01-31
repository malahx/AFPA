/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.sql.Connection;
import java.util.List;
import org.glehenaff.gestform.model.Formation;

/**
 *
 * @author gwenole
 */
public class ConnectDAO extends DAO {

    private static ConnectDAO instance = null;

    public static ConnectDAO Instance() {
        if (instance == null) {
            instance = new ConnectDAO();
        }
        return instance;
    }
    
    public Connection get() {
        return getConnection();
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findBy(Formation formation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object insert(Object o) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
