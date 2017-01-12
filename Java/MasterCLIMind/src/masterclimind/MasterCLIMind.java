/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

import java.util.Scanner;

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

        while (true) {
            char[] soluce = {'1', '2', '3', '4'};
            int i = 10;
            while (i > 0) {
                int good = 0;
                int wrong = 0;
                System.out.println("------------------------------------------------");
                System.out.println(Color.GREEN + "* les bonnes positions" + Color.RESET);
                System.out.println(Color.RED + "* les mauvaises positions" + Color.RESET);
                System.out.println("Tentez de trouver la solution (" + i + "):");
                String str = sc.nextLine();
                if (str.length() != 4) {
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    char car = str.charAt(j);
                    if (soluce[j] == car) {
                        good++;
                        continue;
                    }
                    if (contains(soluce, car)) {
                        wrong++;
                    }
                }
                String goodStr = new String(new char[good]).replace("\0", "*");
                String wrongStr = new String(new char[wrong]).replace("\0", "*");
                System.out.println(Color.GREEN + goodStr + Color.RED + wrongStr + Color.RESET);
                i--;
            }
        }
    }

    public static boolean contains(char[] cars, char car) {
        for (char c : cars) {
            if (car == c) {
                return true;
            }
        }
        return false;
    }

}
