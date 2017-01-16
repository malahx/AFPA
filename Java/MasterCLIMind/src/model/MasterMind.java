/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.nio.CharBuffer;
import java.util.concurrent.ThreadLocalRandom;
import masterclimind.Color;

/**
 *
 * @author gwenole
 */
public class MasterMind {

    private final String soluce;

    private int lastGood = -1;
    private int lastWrong = -1;

    public MasterMind() {
        this.soluce = rand(4, 1, 8);
    }

    public MasterMind(String soluce) {
        this.soluce = soluce;
    }

    // Fonction créant une suite de lettre aléatoire
    private static String rand(int i, int min, int max) {
        String str = new String();
        for (int j = 0; j < i; j++) {
            str += ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return str;
    }

    // Afficher la réponse et peupler les variables lastGood et lastWrong.
    public String display(String str) {
        return conv(soluceCount(str));
    }

    // Convertir un tableau de deux nombres en liste de caractère de : *
    private String conv(int[] indexTest) {
        String str = Color.GREEN + new String(new char[indexTest[0]]).replace("\0", "*");
        str += Color.RED + new String(new char[indexTest[1]]).replace("\0", "*");
        return str;
    }

    // Compter le nombre de caractère à la bonne place et le nombre de caractère à la mauvaise place
    // return int[] {good, wrong}
    private int[] soluceCount(String test) {
        String treated = ""; // Caractère déjà traité

        lastGood = 0; // Nombre de caractère trouvé à la baonne position
        lastWrong = 0; // Nombre de caractère trouvé à la mauvaise position

        for (int i = soluce.length() - 1; i >= 0; i--) { // Essai de vérification de chaque caractère à la bonne place
            char car = test.charAt(i);
            if (soluce.charAt(i) == car) { // Vérification des caractères se trouvant à la bonne place
                lastGood++;
                treated += soluce.charAt(i);
            }
        }

        for (int i = soluce.length() - 1; i >= 0; i--) { // Essai de vérification de chaque caractère à la mauvaise

            char car = test.charAt(i);
            if (soluce.charAt(i) == car || count(treated, car) >= count(soluce, car)) { // Vérification si tous les caractères ont déjà été traités
                continue;
            }

            if (soluce.contains(CharBuffer.wrap(new char[]{car}))) { // Vérification des caractères se trouvant à la mauvaise place
                lastWrong++;
                treated += car;
            }
        }
        return new int[]{lastGood, lastWrong};
    }

    // Compteur d'un caractère dans une chaine de caractère
    private int count(String str, char car) {
        int j = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == car) {
                j++;
            }
        }
        return j;
    }

    public String getSoluce() {
        return soluce;
    }

    public int getLastGood() {
        return lastGood;
    }

    public int getLastWrong() {
        return lastWrong;
    }

}
