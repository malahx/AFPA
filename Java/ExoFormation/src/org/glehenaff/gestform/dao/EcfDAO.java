/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.glehenaff.gestform.model.ECF;
import org.glehenaff.gestform.model.Formation;

/**
 *
 * @author gwenole
 */
public class EcfDAO extends DAO<ECF> {

    @Override
    public List<ECF> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ECF> findBy(Object o) {
        List<ECF> ecf = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            String query = "SELECT * FROM ecf e "
                    + "INNER JOIN formation f ON e.formation_id = f.id "
                    + "WHERE f.id = ?";
            PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            prepare.setInt(1, ((Formation) o).getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                ecf.add(new ECF(result.getInt("e.id"), (Formation) o, result.getString("e.nom")));
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ecf;
    }

    @Override
    public ECF insert(Object o) {
        PreparedStatement prepare = null;
        Connection conn = null;

        ECF ecf = (ECF) o;
        String query = "INSERT INTO `ecf` (`id`, `nom`, `formation_id`) VALUES (0, ?, ?)";

        try {
            int id = -1;
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            
            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, ecf.getNom());
            prepare.setInt(2, ecf.getFormation().getId());

            int row = prepare.executeUpdate();

            ResultSet generatedKeys = prepare.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            if (row == 0 || id < 0) {
                throw new SQLException("Erreur à l'insertion de l'ECF.");
            }

            ecf.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ecf;
    }
    
}
