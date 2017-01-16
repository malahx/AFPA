/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

import java.util.Scanner;
import java.util.regex.Pattern;
import model.MasterMind;

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

            MasterMind masterMind = new MasterMind(); // Création de la solution

            System.out.println("------------------------------------------------");
            System.out.println("------ Entrez 'exit' pour quitter");
            System.out.println("------ Possibilité de 1 à 8");
            System.out.println("Nombre de chiffre attendue : " + masterMind.getSoluce().length());
            //System.out.println("Solution : " + masterMind.getSoluce());

            System.out.println(Color.GREEN + "* les bonnes positions" + Color.RESET);
            System.out.println(Color.RED + "* les mauvaises positions" + Color.RESET);

            int i = 10; // Nombre d'essai possible
            while (i > 0) {
                
                System.out.println("------------------------------------------------");
                System.out.println("Tentez de trouver la solution (" + i + "):");

                String str = sc.nextLine();
                if (str.length() != masterMind.getSoluce().length()) { // Vérification que l'essai fait 4 caractères
                    System.out.println("Vous devez saisir " + masterMind.getSoluce().length() + " chiffres.");
                    continue;
                } else if (!Pattern.matches("^[1-8]{4}$", str)) {
                    System.out.println("Vous devez saisir des chiffres compris entre 1 et 8.");
                    continue;
                }
                if (str.equals("exit")) { // Vérification si l'utilisateur essai de sortir
                    break mainloop;
                }

                System.out.println(masterMind.display(str));

                if (masterMind.getLastGood() == 4) {
                    System.out.println(Color.YELLOW + "BRAVO, vous avez gagné !!!");
                    break;
                }

                i--;
            }
            System.out.println(Color.YELLOW + "La solution était : " + masterMind.getSoluce());
            System.out.println("------------------------------------------------");
            System.out.println("Voulez vous continuer [o/N] ?");
            String str = sc.nextLine();
            if (!str.toLowerCase().equals("o")) {
                break mainloop;
            }
        }
        System.out.println("Merci d'avoir joué, bye ;)");
    }

}
