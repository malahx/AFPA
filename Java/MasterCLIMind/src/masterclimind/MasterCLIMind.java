/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

import java.util.Scanner;
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
            
            int i = 10; // Nombre d'essai possible
            while (i > 0) {

                System.out.println("------------------------------------------------");
                System.out.println("------ Entrez 'exit' pour quitter");
                System.out.println("------ Possibilité de 1 à 8");
                System.out.println("Nombre de chiffre attendue : " + masterMind.getSoluce().length());
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

                System.out.println(masterMind.display(str));

                if (masterMind.getLastGood() == 4) {
                    System.out.println(Color.YELLOW + "BRAVO, vous avez gagné !!!");
                    break;
                }

                i--;
            }
            System.out.println(Color.YELLOW + "La solution était : " + masterMind.getSoluce());
        }
    }





}
