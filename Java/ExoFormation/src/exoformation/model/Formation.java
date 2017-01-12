/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * @author gwenole
 */
public class Formation implements Serializable {

    private List<Stagiaire> stagiaires = new ArrayList<>();
    private String nom;

    public Formation(String nom) {
        this.nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
    }

    public Formation(String nom, List<Stagiaire> stagiaires) {
        this.nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
        this.stagiaires = stagiaires;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void addStagiaire(Stagiaire stagiaire) {
        this.stagiaires.add(stagiaire);
    }

    public void remStagiaire(Stagiaire stagiaire) {
        this.stagiaires.remove(stagiaire);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}
