/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.io.Serializable;

/**
 *
 * @author gwenole
 */
public class Personne implements Serializable {

    private int id;
    private String nom;
    private String prenom;

    public Personne() {
    }

    public Personne(int id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom.toUpperCase();
        this.prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final Personne other = (Personne) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
