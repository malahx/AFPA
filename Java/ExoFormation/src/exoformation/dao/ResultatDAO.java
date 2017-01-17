/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.dao;

import exoformation.model.ECF;
import exoformation.model.Resultat;
import exoformation.model.Stagiaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}
