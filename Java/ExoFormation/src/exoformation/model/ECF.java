/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gwenole
 */
public class ECF implements Serializable {

    private Formation formation;
    private String nom;
    private final List<Resultat> resultats = new ArrayList<>();

    public ECF(Formation formation, String nom) {
        this.nom = nom;
        this.formation = formation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void addResultat(Resultat res) {
        this.resultats.add(res);
    }

    public void remResultat(Resultat res) {
        this.resultats.remove(res);
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.formation);
        hash = 59 * hash + Objects.hashCode(this.nom);
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
        final ECF other = (ECF) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.formation, other.formation)) {
            return false;
        }
        return true;
    }

}
