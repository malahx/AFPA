/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform;

import java.util.List;

import org.glehenaff.gestform.dao.AlreadyExistsException;
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

    // Charger les données
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

    // Ajouter un stagiaire à la base de donnée
    public static Stagiaire addToDB(Stagiaire stagiaire) {
        try {
            return StagiaireDAO.Instance().insert(stagiaire);
        } catch (AlreadyExistsException e) {
            System.out.println("AlreadyExistsException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Ajouter une formation à la base de donnée
    public static Formation addToDB(Formation formation) {
        try {
            return FormationDAO.Instance().insert(formation);
        } catch (AlreadyExistsException e) {
            System.out.println("AlreadyExistsException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Ajouter un stagiaire à une formation à la base de donnée
    public static boolean addToDB(Formation formation, Stagiaire stagiaire) {
        try {
            return FormationDAO.Instance().insert(formation, stagiaire);
        } catch (AlreadyExistsException e) {
            System.out.println("AlreadyExistsException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Ajouter un ecf à la base de donnée
    public static ECF addToDB(ECF ecf) {
        try {
            return EcfDAO.Instance().insert(ecf);
        } catch (AlreadyExistsException e) {
            System.out.println("AlreadyExistsException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Ajouter un résultat à la base de donnée
    public static Resultat addToDB(Resultat res) {
        try {
            return ResultatDAO.Instance().insert(res);
        } catch (AlreadyExistsException e) {
            System.out.println("AlreadyExistsException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Supprimer un stagiaire à la base de donnée
    public static boolean delToDB(Stagiaire stagiaire) {
        return StagiaireDAO.Instance().delete(stagiaire);
    }

    // Supprimer une formation à la base de donnée
    public static boolean delToDB(Formation formation) {
        return FormationDAO.Instance().delete(formation);
    }

    // Supprimer un stagiaire à une formation à la base de donnée
    public static boolean delToDB(Formation formation, Stagiaire stagiaire) {
        return FormationDAO.Instance().delete(formation, stagiaire);
    }

    // Supprimer un ecf à la base de donnée
    public static boolean delToDB(ECF ecf) {
        return EcfDAO.Instance().delete(ecf);
    }

    // Supprimer un résultat à la base de donnée
    public static boolean delToDB(Resultat res) {
        return ResultatDAO.Instance().delete(res);
    }
}
