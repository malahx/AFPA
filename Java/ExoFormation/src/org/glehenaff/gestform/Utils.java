/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation;

import exoformation.dao.EcfDAO;
import exoformation.dao.FormationDAO;
import exoformation.dao.ResultatDAO;
import exoformation.dao.StagiaireDAO;
import exoformation.model.ECF;
import exoformation.model.Formation;
import exoformation.model.Resultat;
import exoformation.model.Stagiaire;
import java.util.List;

/**
 *
 * @author gwenole
 */
public class Utils {

    public static List[] Load() {
        FormationDAO formationDAO = new FormationDAO();
        StagiaireDAO stagiaireDAO = new StagiaireDAO();
        EcfDAO ecfeDAO = new EcfDAO();
        ResultatDAO resDAO = new ResultatDAO();

        List<Formation> formations = formationDAO.findAll();
        for (Formation formation : formations) {
            formation.setStagiaires(stagiaireDAO.findBy(formation));
            List<ECF> ecfs = ecfeDAO.findBy(formation);
            formation.setECFs(ecfs);
            for (ECF ecf : ecfs) {
                ecf.setResultats(resDAO.findBy(ecf));
            }
        }

        return new List[]{formations, stagiaireDAO.findAll()};
    }

    public static Stagiaire addToDB(Stagiaire stagiaire) {
        StagiaireDAO dao = new StagiaireDAO();
        return dao.insert(stagiaire);
    }

    public static Formation addToDB(Formation formation) {
        FormationDAO dao = new FormationDAO();
        return dao.insert(formation);
    }
    
    public static boolean addToDB(Formation formation, Stagiaire stagiaire) {
        FormationDAO dao = new FormationDAO();
        return dao.insert(formation, stagiaire);
    }

    public static ECF addToDB(ECF ecf) {
        EcfDAO dao = new EcfDAO();
        return dao.insert(ecf);
    }

    public static Resultat addToDB(Resultat res) {
        ResultatDAO dao = new ResultatDAO();
        return dao.insert(res);
    }
}
