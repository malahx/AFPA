/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation;

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
}
