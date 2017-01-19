/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform;

import java.util.List;

import org.glehenaff.gestform.dao.EcfDAO;
import org.glehenaff.gestform.dao.FormationDAO;
import org.glehenaff.gestform.dao.ResultatDAO;
import org.glehenaff.gestform.dao.StagiaireDAO;
import org.glehenaff.gestform.model.ECF;
import org.glehenaff.gestform.model.Formation;
import org.glehenaff.gestform.model.Resultat;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class Utils {

    public static Object[] Load() {
        List<Formation> formations = FormationDAO.Instance().findAll();
        for (Formation formation : formations) {
            formation.setStagiaires(StagiaireDAO.Instance().findBy(formation));
            List<ECF> ecfs = EcfDAO.Instance().findBy(formation);
            formation.setECFs(ecfs);
            for (ECF ecf : ecfs) {
                ecf.setResultats(ResultatDAO.Instance().findBy(ecf));
            }
        }

        return new Object[]{formations, StagiaireDAO.Instance().findAll()};
    }

    public static Stagiaire addToDB(Stagiaire stagiaire) {
        return StagiaireDAO.Instance().insert(stagiaire);
    }

    public static Formation addToDB(Formation formation) {
        return FormationDAO.Instance().insert(formation);
    }
    
    public static boolean addToDB(Formation formation, Stagiaire stagiaire) {
        return FormationDAO.Instance().insert(formation, stagiaire);
    }

    public static ECF addToDB(ECF ecf) {
        return EcfDAO.Instance().insert(ecf);
    }

    public static Resultat addToDB(Resultat res) {
        return ResultatDAO.Instance().insert(res);
    }
}
