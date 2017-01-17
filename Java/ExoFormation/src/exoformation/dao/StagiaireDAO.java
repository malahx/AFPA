/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.dao;

import exoformation.model.Formation;
import exoformation.model.Stagiaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gwenole
 */
public class StagiaireDAO extends DAO<Stagiaire> {

    @Override
    public List<Stagiaire> findAll() {
        List<Stagiaire> stagiaires = new ArrayList<>();
        try {
            Connection conn = getConnection();
            if (conn == null) {
                return null;
            }
            Statement state = conn.createStatement();
            String query = "SELECT * FROM stagiaire INNER JOIN personne p ON personne_id = p.id";
            ResultSet result = state.executeQuery(query);
            while (result.next()) {
                stagiaires.add(new Stagiaire(result.getInt("p.id"), result.getString("nom"), result.getString("prenom"), result.getString("code")));
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
                    + "WHERE f.nom = ?";
            PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            prepare.setInt(1, ((Formation)o).getId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                stagiaires.add(new Stagiaire(result.getInt("p.id"), result.getString("nom"), result.getString("prenom"), result.getString("code")));
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stagiaires;
    }

}
