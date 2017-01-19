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

import org.glehenaff.gestform.model.Formation;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class FormationDAO extends DAO<Formation> {

    @Override
    public List<Formation> findAll() {
        List<Formation> formations = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            Statement state = conn.createStatement();
            String query = "SELECT * FROM formation";
            ResultSet result = state.executeQuery(query);
            while (result.next()) {
                formations.add(new Formation(result.getInt("id"), result.getString("nom")));
            }
            result.close();
            state.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formations;
    }

    @Override
    public List<Formation> findBy(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Formation insert(Object o) {
        PreparedStatement prepare = null;
        Connection conn = null;

        Formation formation = (Formation) o;
        String query = "INSERT INTO `formation` (`id`, `nom`) VALUES (0, ?)";

        try {
            int id = -1;
            conn = getConnection();
            if (conn == null) {
                return null;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, formation.getNom());

            int row = prepare.executeUpdate();

            ResultSet generatedKeys = prepare.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            if (row == 0 || id < 0) {
                throw new SQLException("Erreur à l'insertion de la formation.");
            }

            formation.setId(id);

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
        return formation;
    }

    public boolean insert(Formation formation, Stagiaire stagiaire) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "INSERT INTO `promo` (`formation_id`, `stagiaire_code`) VALUES (?, ?)";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, formation.getId());
            prepare.setString(2, stagiaire.getCode());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à l'insertion de la formation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
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
        return true;
    }
}