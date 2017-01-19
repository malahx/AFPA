/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gwenole
 */
public class ECF implements Serializable {

    private int id;
    private Formation formation;
    private String nom;
    private List<Resultat> resultats = new ArrayList<>();

    public ECF(int id, Formation formation, String nom) {
        this.id = id;
        this.nom = nom;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setResultats(List<Resultat> res) {
        this.resultats = res;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
