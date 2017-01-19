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
import org.glehenaff.gestform.model.Resultat;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class ResultatDAO extends DAO<Resultat> {

    @Override
    public List<Resultat> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Resultat> findBy(Object o) { // o = ECF
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
            prepare.setInt(1, ((ECF) o).getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                Stagiaire stagiaire = new Stagiaire(result.getInt("pe.id"), result.getString("pe.nom"), result.getString("pe.prenom"), result.getString("code"));
                res.add(new Resultat(result.getBoolean("r.obtenu"), (ECF)o, stagiaire));
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Resultat insert(Object o) {
        PreparedStatement prepare = null;
        Connection conn = null;

        Resultat res = (Resultat) o;
        String query = "INSERT INTO `resultat` (`ecf_id`, `stagiaire_code`, `obtenu`) VALUES (?, ?, ?)";

        try {
            int id = -1;
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            
            prepare = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, res.getECF().getId());
            prepare.setString(2, res.getStagiaire().getCode());
            prepare.setBoolean(2, res.isObtenu());

            int row = prepare.executeUpdate();

            if (row == 0) {
                throw new SQLException("Erreur à l'insertion de l'ECF.");
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
        return res;
    }
    
}
