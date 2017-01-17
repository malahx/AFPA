/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

/**
 *
 * @author gwenole
 */
public class Stagiaire extends Personne {

    int code;

    public Stagiaire(String nom, String prenom, int code) {
        super(nom, prenom);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.getNom() + " " + this.getPrenom() + "(" + this.code + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.code;
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
        final Stagiaire other = (Stagiaire) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }

}
