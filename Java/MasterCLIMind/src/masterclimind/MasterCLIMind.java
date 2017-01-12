/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

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
        while (true) {
            String soluce = rand(4, 1, 8);
            int i = 10;
            while (i > 0) {
                int good = 0;
                int wrong = 0;
                System.out.println("------------------------------------------------");
                System.out.println("------ Entrez 'exit' pour quitter");
                System.out.println("------ Possibilité de 1 à 8");
                System.out.println("Nombre de chiffre attendue : " + soluce.length());
                System.out.println(Color.GREEN + "* les bonnes positions" + Color.RESET);
                System.out.println(Color.RED + "* les mauvaises positions" + Color.RESET);
                System.out.println("Tentez de trouver la solution (" + i + "):");
                String str = sc.nextLine();
                if (str.length() != 4) {
                    continue;
                }
                if (str.equals("exit")) {
                    break mainloop;
                }
                String treated = "";
                for (int j = 0; j < 4; j++) {
                    char car = str.charAt(j);
                    if (count(treated, car) >= count(soluce, car)) {
                        continue;
                    }
                    if (soluce.charAt(j) == car) {
                        good++;
                        treated += soluce.charAt(j);
                        continue;
                    }
                    if (contains(soluce, car)) {
                        wrong++;
                        treated += car;
                    }
                }
                String goodStr = new String(new char[good]).replace("\0", "*");
                String wrongStr = new String(new char[wrong]).replace("\0", "*");
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

    private static boolean contains(String str, char car) {
        char[] cars = str.toCharArray();
        for (char c : cars) {
            if (car == c) {
                return true;
            }
        }
        return false;
    }

    private static int count(String str, char car) {
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == car) {
                j++;
            }
        }
        return j;
    }

    private static String rand(int i, int min, int max) {
        String str = new String();
        for (int j = 0; j < i; j++) {
            str += ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return str;
    }

}
