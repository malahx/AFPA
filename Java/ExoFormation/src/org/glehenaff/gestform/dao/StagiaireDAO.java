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
public class StagiaireDAO extends DAO<Stagiaire> {
	
	private static StagiaireDAO instance = null;
	
	public static StagiaireDAO Instance() {
		if (instance == null) {
			instance = new StagiaireDAO();
		}
		return instance;
	}

    @Override
    public List<Stagiaire> findAll() {
        List<Stagiaire> stagiaires = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            Statement state = conn.createStatement();
            String query = "SELECT * FROM stagiaire s INNER JOIN personne p ON s.personne_id = id";
            ResultSet result = state.executeQuery(query);
            while (result.next()) {
                stagiaires.add(new Stagiaire(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("code")));
            }
            result.close();
            state.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stagiaires;
    }

    @Override
    public List<Stagiaire> findBy(Object o) { // o = Formation
        List<Stagiaire> stagiaires = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            String query = "SELECT * FROM stagiaire s "
                    + "INNER JOIN personne pe ON s.personne_id = pe.id "
                    + "INNER JOIN promo pr ON s.code = pr.stagiaire_code "
                    + "INNER JOIN formation f ON pr.formation_id = f.id "
                    + "WHERE f.id = ?";
            PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            prepare.setInt(1, ((Formation) o).getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                stagiaires.add(new Stagiaire(result.getInt("pe.id"), result.getString("nom"), result.getString("prenom"), result.getString("code")));
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stagiaires;
    }

    @Override
    public Stagiaire insert(Object o) { // o = Stagiaire
        PreparedStatement prepareP = null;
        PreparedStatement prepareS = null;
        Connection conn = null;

        Stagiaire stagiaire = (Stagiaire) o;
        String queryP = "INSERT INTO `personne` (`id`, `nom`, `prenom`) VALUES (0, ?, ?)";
        String queryS = "INSERT INTO `stagiaire` (`code`, `personne_id`) VALUES (?, ?)";

        try {
            int id = -1;
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            conn.setAutoCommit(false);

            prepareP = conn.prepareStatement(queryP, Statement.RETURN_GENERATED_KEYS);
            prepareP.setString(1, stagiaire.getNom());
            prepareP.setString(2, stagiaire.getPrenom());

            int row = prepareP.executeUpdate();

            ResultSet generatedKeys = prepareP.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            if (row == 0 || id < 0) {
                throw new SQLException("Erreur à l'insertion du stagiaire.");
            }

            prepareS = conn.prepareStatement(queryS);
            prepareS.setString(1, stagiaire.getCode());
            prepareS.setInt(2, id);
            prepareS.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            stagiaire.setId(id);

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
                if (prepareP != null) {
                    prepareP.close();
                }
                if (prepareS != null) {
                    prepareP.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stagiaire;
    }

}
