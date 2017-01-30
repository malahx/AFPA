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

    private static EcfDAO instance = null;

    public static EcfDAO Instance() {
        if (instance == null) {
            instance = new EcfDAO();
        }
        return instance;
    }

    @Override
    public List<ECF> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ECF> findBy(Formation formation) {
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
            prepare.setInt(1, formation.getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                ecf.add(new ECF(result.getInt("e.id"), formation, result.getString("e.nom")));
            }
            result.close();
            prepare.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ecf;
    }

    @Override
    public ECF insert(ECF ecf) throws AlreadyExistsException {
        PreparedStatement prepare = null;
        Connection conn = null;
        ResultSet generatedKeys = null;

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

            generatedKeys = prepare.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            if (row == 0 || id < 0) {
                throw new SQLException("Erreur à l'insertion de l'ECF.");
            }

            ecf.setId(id);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new AlreadyExistsException(e);
            }
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare, generatedKeys);
        }
        return ecf;
    }

    @Override
    public boolean delete(ECF ecf) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "DELETE FROM ecf WHERE id = ?";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, ecf.getId());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à la suppression de l'ECF.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return true;
    }

    @Override
    public boolean update(ECF ecf) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "UPDATE ecf SET nom = ? WHERE id = ?";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, ecf.getNom());
            prepare.setInt(2, ecf.getId());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à l'update de l'ecf.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return true;
    }

}
