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
import exoformation.model.Stagiaire;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author gwenole
 */
public class Utils {

    // Sérializer un object
    public static void serialize(String path, Object o) {
        File f = new File(path);
        if (f.isDirectory() || (f.exists() && !f.canWrite())) {
            return;
        }
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(path);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(o);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Déserializer un objet
    public static Object deSerialize(String path) {
        File f = new File(path);
        if (!f.exists() || f.isDirectory() || !f.canRead()) {
            return null;
        }
        ObjectInputStream ois = null;
        Object o = null;
        try {
            final FileInputStream fichier = new FileInputStream(path);
            ois = new ObjectInputStream(fichier);
            o = (List<Stagiaire>) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return o;
    }

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
}
