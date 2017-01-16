/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
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
        return conv(count(str));
    }

    // Convertir un tableau de deux nombres en liste de caractère de : *
    private String conv(int[] indexTest) {
        String str = Color.GREEN + new String(new char[indexTest[0]]).replace("\0", "*");
        str += Color.RED + new String(new char[indexTest[1]]).replace("\0", "*");
        return str;
    }

    // Compter le nombre de caractère à la bonne place et le nombre de caractère à la mauvaise place
    // return int[] {good, wrong}
    private int[] count(String test) {

        lastGood = 0; // Nombre de caractère trouvé à la bonne position
        lastWrong = 0; // Nombre de caractère trouvé à la mauvaise position

        // Nombre de caractère unique
        Map<Character, Integer> soluceCount = new HashMap<>();
        Map<Character, Integer> treatedCount = new HashMap<>();

        for (int i = soluce.length() - 1; i >= 0; i--) { // Essai de vérification de chaque caractère à la bonne place
            char car = soluce.charAt(i);

            // Prétraitement des doubles
            soluceCount = addTo(soluceCount, car);

            car = test.charAt(i);

            if (soluce.charAt(i) == car) { // Vérification des caractères se trouvant à la bonne place
                lastGood++;

                // Prétraitement des doubles
                treatedCount = addTo(treatedCount, car);
            }
        }

        for (int i = soluce.length() - 1; i >= 0; i--) { // Essai de vérification de chaque caractère à la mauvaise

            char car = test.charAt(i);
            if (soluce.charAt(i) == car || isTreated(soluceCount, treatedCount, car)) { // Vérification si tous les caractères ont déjà été traités
                continue;
            }

            if (soluce.contains(CharBuffer.wrap(new char[]{car}))) { // Vérification des caractères se trouvant à la mauvaise place
                lastWrong++;
                
                // Traitement des doubles
                treatedCount = addTo(treatedCount, car);
            }
        }
        return new int[]{lastGood, lastWrong};
    }
    
    // Vérifier si le caractère à déjà été traité
    private boolean isTreated(Map<Character, Integer> soluce, Map<Character, Integer> treated, char car) {
        if (!soluce.containsKey(car) || !treated.containsKey(car)) {
            return false;
        }
        return treated.get(car) >= soluce.get(car);
    }

    // Incrémenter le nombre de caractère
    private Map<Character, Integer> addTo(Map<Character, Integer> hMap, char car) {
        if (!hMap.containsKey(car)) {
            hMap.put(car, 1);
        } else {
            hMap.put(car, hMap.get(car) + 1);
        }
        return hMap;
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
