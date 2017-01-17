/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.dao;

import exoformation.model.ECF;
import exoformation.model.Formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}
