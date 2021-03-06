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
import org.glehenaff.gestform.model.Resultat;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class ResultatDAO extends DAO<Resultat> {

    private static ResultatDAO instance = null;

    public static ResultatDAO Instance() {
        if (instance == null) {
            instance = new ResultatDAO();
        }
        return instance;
    }

    @Override
    public List<Resultat> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Resultat> findBy(ECF ecf) { // o = ECF
        List<Resultat> res = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            String query = "SELECT * FROM resultat r "
                    + "INNER JOIN ecf e ON r.ecf_id = e.id "
                    + "INNER JOIN stagiaire s ON r.stagiaire_code like s.code "
                    + "INNER JOIN personne pe ON s.personne_id = pe.id "
                    + "WHERE e.id = ?";
            PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            prepare.setInt(1, ecf.getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                Stagiaire stagiaire = new Stagiaire(result.getInt("pe.id"), result.getString("pe.nom"), result.getString("pe.prenom"), result.getString("code"));
                res.add(new Resultat(result.getBoolean("r.obtenu"), ecf, stagiaire));
            }
            result.close();
            prepare.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Resultat insert(Resultat res) throws AlreadyExistsException {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "INSERT INTO `resultat` (`ecf_id`, `stagiaire_code`, `obtenu`) VALUES (?, ?, ?)";

        try {
            conn = getConnection();
            if (conn == null) {
                return null;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, res.getECF().getId());
            prepare.setString(2, res.getStagiaire().getCode());
            prepare.setBoolean(3, res.isObtenu());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à l'insertion du résultat.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new AlreadyExistsException(e);
            }
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return res;
    }

    @Override
    public List<Resultat> findBy(Formation formation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Resultat resultat) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "DELETE FROM resultat WHERE ecf_id = ? AND stagiaire_code LIKE ?";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, resultat.getECF().getId());
            prepare.setString(2, resultat.getStagiaire().getCode());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à la suppression du résultat.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return true;
    }

    public boolean delete(Formation f, Stagiaire s) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "DELETE FROM resultat WHERE ecf_id IN (SELECT e.id FROM ecf e INNER JOIN formation f ON f.id = e.formation_id WHERE f.id = ?) AND stagiaire_code LIKE ?";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, f.getId());
            prepare.setString(2, s.getCode());

            int row = prepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return true;
    }

    @Override
    public boolean update(Resultat res) {
        PreparedStatement prepare = null;
        Connection conn = null;

        String query = "UPDATE resultat SET obtenu = ? WHERE ecf_id = ? AND stagiaire_code LIKE ?";

        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setBoolean(1, res.isObtenu());
            prepare.setInt(2, res.getECF().getId());
            prepare.setString(3, res.getStagiaire().getCode());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à l'update du résultat.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, prepare);
        }
        return true;
    }

}
