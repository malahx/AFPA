/*
 * Unlisense
 */
package exoformation;

import exoformation.model.ECF;
import exoformation.model.Formation;
import exoformation.model.Resultat;
import exoformation.model.Stagiaire;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gwenole
 */
public class ExoFormation {

    // Liste de toutes les formations saisies
    private static List<Formation> formations;

    // Liste de tous les stagiaires saisies
    private static List<Stagiaire> stagiaires;

    // Le scanner de touche
    private static Scanner sc;

    // Fichier des serialisations
    private static final String PATH_STAGIAIRE = "stagiaires.ser";
    private static final String PATH_FORMATION = "formations.ser";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Initialisation des variables globales
        formations = new ArrayList<>();
        stagiaires = new ArrayList<>();
        sc = new Scanner(System.in);

        Object o = Utils.deSerialize(PATH_STAGIAIRE);
        if (o != null) {
            stagiaires = (List<Stagiaire>) o;
        }

        o = Utils.deSerialize(PATH_FORMATION);
        if (o != null) {
            formations = (List<Formation>) o;
        }

        // Fonctionnement de l'application en continue
        while (true) {
            // Initialisation des variables pour les différentes options
            int i = 1, lf = 0, ls = 0, f, s, sf = 0, secf = 0, recf = 0, info = 0;

            // Options possibles
            System.out.println("------------------------------------------------");
            System.out.println(Color.GREEN + "Que voulez vous faire :");
            System.out.println(Color.RED + "0 - Quitter");
            if (formations.size() > 0) {
                System.out.println(Color.YELLOW + i + " - Lister les formations");
                lf = i;
                i++;
                System.out.println(Color.YELLOW + i + " - Information sur une formation");
                info = i;
                i++;
            }
            System.out.println(Color.YELLOW + i + " - Saisir une formation");
            f = i;
            i++;
            if (stagiaires.size() > 0) {
                System.out.println(Color.YELLOW + i + " - Lister les stagiaires");
                ls = i;
                i++;
            }
            System.out.println(Color.YELLOW + i + " - Saisir un stagiaire");
            s = i;
            i++;
            if (formations.size() > 0) {
                System.out.println(Color.YELLOW + i + " - Saisir un ECF");
                secf = i;
                i++;
                System.out.println(Color.YELLOW + i + " - Saisir un Résultat d'ECF");
                recf = i;
                i++;
                if (stagiaires.size() > 0 && stagiaireDispo() > 0) {
                    System.out.println(Color.YELLOW + i + " - Ajouter un stagiaire à une formation");
                    sf = i;
                    i++;
                }
            }
            String str = sc.nextLine();
            System.out.println("------------------------------------------------");
            if (str.equals("")) {
                continue;
            }
            int j = Integer.parseInt(str);
            // Fonctions en fonction des options
            if (j == 0) {
                break;
            } else if (j == lf) {
                listFormations();
            } else if (j == ls) {
                listStagiaires();
            } else if (j == info) {
                infoFormation(selectFormation());
            } else if (j == f) {
                addFormation();
            } else if (j == s) {
                addStagiaire();
            } else if (j == sf) {
                addStagiairesTo(selectFormation());
            } else if (j == secf) {
                addECFTo(selectFormation());
            } else if (j == recf) {
                Formation formation = selectFormation();
                ECF ecf = selectECFFrom(formation);
                Stagiaire stagiaire = selectStagiaireFrom(formation);
                addRECFTo(formation, ecf, stagiaire);
            }
        }

        Utils.serialize(PATH_STAGIAIRE, (Object) stagiaires);
        Utils.serialize(PATH_FORMATION, (Object) formations);

        // Affichage des formations et des stagiaires
        listFormations();
        listStagiaires();
    }

    // Vérifier si un stagiaire est dans une formation
    private static boolean isInForm(Stagiaire stg) {
        for (Formation f : formations) {
            if (f.getStagiaires().contains(stg)) {
                return true;
            }
        }
        return false;
    }

    // Récupérer le nombre de stagiaires libres
    private static int stagiaireDispo() {
        int i = 0;
        for (Stagiaire s : stagiaires) {
            if (isInForm(s)) {
                continue;
            }
            i++;
        }
        return i;
    }

    // Ajouter un stagiaire
    private static Stagiaire addStagiaire() {
        System.out.println(Color.GREEN + "Nom du stagiaire :");
        String nom = sc.nextLine();
        System.out.println(Color.GREEN + "Prénom du stagiaire :");
        String prenom = sc.nextLine();
        Stagiaire stagiaire = new Stagiaire(nom, prenom, stagiaires.size());
        stagiaires.add(stagiaire);
        if (formations.size() > 0) {
            System.out.println(Color.GREEN + "Voulez vous affecter le stagiaire à une formation [o/N] ?");
            String c = sc.nextLine();
            if (c.toLowerCase().equals("o")) {
                Formation formation = selectFormation();
                if (formation != null) {
                    formation.addStagiaire(stagiaire);
                    infoFormation(formation);
                }
            }
        }
        return stagiaire;
    }

    // Ajouter une formation
    private static Formation addFormation() {
        System.out.println(Color.GREEN + "Nom de la formation :");
        String nom = sc.nextLine();
        Formation formation = new Formation(nom);
        formations.add(formation);
        if (stagiaires.size() > 0) {
            System.out.println(Color.GREEN + "Voulez vous affecter des stagiaires [o/N] ?");
            String c = sc.nextLine();
            if (c.toLowerCase().equals("o")) {
                addStagiairesTo(formation);
                infoFormation(formation);
            }
        }
        return formation;
    }

    // Sélectionner une formation
    private static Formation selectFormation() {
        System.out.println(Color.GREEN + "Choisissez la formation :");
        System.out.println(Color.RED + 0 + " - Sortir");
        int size = formations.size();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            System.out.println(Color.YELLOW + j + " - " + formations.get(i));
        }
        String str = sc.nextLine();
        if (str.equals("")) {
            return null;
        }
        int j = Integer.parseInt(str);
        if (j == 0 || j > formations.size()) {
            return null;
        }
        Formation formation = formations.get(j - 1);
        return formation;
    }

    // Sélectionner une formation
    private static Stagiaire selectStagiaireFrom(Formation formation) {
        if (formation == null) {
            return null;
        }
        System.out.println(Color.GREEN + "Choisissez la formation :");
        System.out.println(Color.RED + 0 + " - Sortir");
        int size = formation.getStagiaires().size();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            System.out.println(Color.YELLOW + j + " - " + formation.getStagiaires().get(i));
        }
        String str = sc.nextLine();
        if (str.equals("")) {
            return null;
        }
        int j = Integer.parseInt(str);
        if (j == 0 || j > size) {
            return null;
        }
        Stagiaire stagiaire = formation.getStagiaires().get(j - 1);
        return stagiaire;
    }

    // Sélectionner l'ECF
    private static ECF selectECFFrom(Formation formation) {
        if (formation == null) {
            return null;
        }
        System.out.println(Color.GREEN + "Choisissez l'ecf :");
        System.out.println(Color.RED + 0 + " - Sortir");
        int size = formation.getECFs().size();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            System.out.println(Color.YELLOW + j + " - " + formation.getECFs().get(i));
        }
        String str = sc.nextLine();
        if (str.equals("")) {
            return null;
        }
        int j = Integer.parseInt(str);
        if (j == 0 || j > size) {
            return null;
        }
        ECF ecf = formation.getECFs().get(j - 1);
        return ecf;
    }

    // Ajouter des stagiaires à une formation
    private static void addStagiairesTo(Formation formation) {
        if (formation == null) {
            return;
        }
        System.out.println(Color.GREEN + "Ajoutez le stagiaire :");
        System.out.println(Color.RED + 0 + " - Retour au menu");
        List<Stagiaire> stgs = new ArrayList<>();
        int i = 1;
        for (Stagiaire s : stagiaires) {
            if (isInForm(s)) {
                continue;
            }
            stgs.add(s);
            System.out.println(Color.YELLOW + i + " - " + s);
            i++;
        }
        String str = sc.nextLine();
        if (str.equals("")) {
            addStagiairesTo(formation);
            return;
        }
        int j = Integer.parseInt(str);
        if (j == 0 || j > i) {
            return;
        }
        formation.addStagiaire(stgs.get(j - 1));
        addStagiairesTo(formation);
    }

    // Ajouter un ECF à une formation
    private static void addECFTo(Formation formation) {
        System.out.println(Color.GREEN + "Nom de l'ECF :");
        String nom = sc.nextLine();
        formation.addECF(new ECF(formation, nom));
        infoFormation(formation);
    }

    // Ajouter un Résultat d'un stagiaire à un ECF
    private static void addRECFTo(Formation formation, ECF ecf, Stagiaire stagiaire) {
        System.out.println(Color.GREEN + "Résultat de l'ECF [o/N] ?");
        String str = sc.nextLine();
        boolean value;
        if (str.toLowerCase().equals("o")) {
            value = true;
        } else {
            value = false;
        }
        ecf.addResultat(new Resultat(value, ecf, stagiaire));
        infoFormation(formation);
    }

    // Afficher tous les stagiaires
    private static void listStagiaires() {
        if (stagiaires.size() > 0) {
            System.out.println(Color.CYAN + "Stagiaires saisies :");
            for (Stagiaire s : stagiaires) {
                System.out.println(s);
            }
        } else {
            System.out.println(Color.CYAN + "Aucun stagiaire saisie");
        }
    }

    // Afficher toutes les formations
    private static void listFormations() {
        if (formations.size() > 0) {
            System.out.println(Color.CYAN + "Formations saisies :");
            for (Formation f : formations) {
                System.out.println(Color.CYAN + f.getNom());
                for (Stagiaire s : f.getStagiaires()) {
                    System.out.println(Color.CYAN + " -- " + s);
                }
            }
        } else {
            System.out.println("\u001B[36mAucune formation saisie");
        }
    }

    // Afficher les stagiaires d'une formation
    private static void infoFormation(Formation formation) {
        System.out.println(Color.CYAN + "Information sur la formation :");
        System.out.println(Color.CYAN + "- Liste des stagiaires :");
        formation.getStagiaires().forEach((s) -> {
            System.out.println(Color.CYAN + " -- " + s);
        });
        System.out.println(Color.CYAN + "- Liste des ECF :");
        formation.getECFs().forEach((s) -> {
            System.out.println(Color.CYAN + " -- " + s.getNom());
            s.getResultats().forEach((r) -> {
                System.out.println(Color.CYAN + " -- -> " + r.getStagiaire() + " Obtenu : " + (r.isValue() ? "OUI" : "NON"));

            });
        });
    }
}
