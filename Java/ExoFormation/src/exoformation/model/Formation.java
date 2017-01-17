/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author gwenole
 */
public class Formation implements Serializable {

    private final List<Stagiaire> stagiaires = new ArrayList<>();
    private final List<ECF> ecfs = new ArrayList<>();
    private String nom;

    public Formation(String nom) {
        this.nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
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

    public List<ECF> getECFs() {
        return ecfs;
    }
    
    public void addECF(ECF ecf) {
        this.ecfs.add(ecf);
    }

    public void remECF(ECF ecf) {
        this.ecfs.remove(ecf);
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formation other = (Formation) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

}
