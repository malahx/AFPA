/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

import java.util.Arrays;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gwenole
 */
public class MasterCLIMind {

    private static Scanner sc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sc = new Scanner(System.in);

        mainloop:
        while (true) { // Partie à l'infinie

            String soluce = rand(4, 1, 8); // Création de la solution
            
            int i = 10; // Nombre d'essai possible
            while (i > 0) {

                System.out.println("------------------------------------------------");
                System.out.println("------ Entrez 'exit' pour quitter");
                System.out.println("------ Possibilité de 1 à 8");
                System.out.println("Nombre de chiffre attendue : " + soluce.length());
                System.out.println(Color.GREEN + "* les bonnes positions" + Color.RESET);
                System.out.println(Color.RED + "* les mauvaises positions" + Color.RESET);
                System.out.println("Tentez de trouver la solution (" + i + "):");

                String str = sc.nextLine();
                if (str.length() != 4) { // Vérification que l'essai fait 4 caractères
                    continue;
                }
                if (str.equals("exit")) { // Vérification si l'utilisateur essai de sortir
                    break mainloop;
                }

                String treated = ""; // Caractère déjà traité
                int good = 0; // Nombre caractère trouvé à la bonne position
                int wrong = 0; // Nombre de caractère  trouvé à la mauvaise position

                for (int j = 0; j < 4; j++) { // Essai de vérification de chaque bon caractères
                    char car = str.charAt(j);
                    if (soluce.charAt(j) == car) { // Vérification des caractères se trouvant à la bonne place
                        good++;
                        treated += soluce.charAt(j);
                    }
                }

                for (int j = 0; j < 4; j++) { // Essai de vérification de chaque mauvais caractères

                    char car = str.charAt(j);
                    if (soluce.charAt(j) == car || count(treated, car) >= count(soluce, car)) { // Vérification si tous les caractères ont déjà été traités
                        continue;
                    }

                    if (soluce.contains(CharBuffer.wrap(new char[]{car}))) { // Vérification des caractères se trouvant à la mauvase place
                        wrong++;
                        treated += car;
                    }
                }

                // Modifier le nombre de bonnes/mauvaises positions en chaine de caractère de *
                String goodStr = conv(good);
                String wrongStr = conv(wrong);

                System.out.println(Color.GREEN + goodStr + Color.RED + wrongStr + Color.RESET);

                if (good == 4) {
                    System.out.println(Color.YELLOW + "BRAVO, vous avez gagné !!!");
                    break;
                }

                i--;
            }
            System.out.println(Color.YELLOW + "La solution était : " + soluce);
        }
    }

    // Convertir un nombre en liste de *
    private static String conv(int i) {
        return new String(new char[i]).replace("\0", "*");
    }

    // Compteur d'un caractère dans une chaine de caractère
    private static int count(String str, char car) {
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == car) {
                j++;
            }
        }
        return j;
    }

    // Fonction créant une suite de lettre aléatoire
    private static String rand(int i, int min, int max) {
        String str = new String();
        for (int j = 0; j < i; j++) {
            str += ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return str;
    }

}
