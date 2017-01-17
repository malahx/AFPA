/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gwenole
 */
public class Resultat implements Serializable {

    private boolean value;
    private ECF ecf;
    private Stagiaire stagiaire;

    public Resultat(boolean value, ECF ecf, Stagiaire stagiaire) {
        this.value = value;
        this.ecf = ecf;
        this.stagiaire = stagiaire;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public ECF getECF() {
        return ecf;
    }

    public void setECF(ECF ecf) {
        this.ecf = ecf;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.ecf);
        hash = 53 * hash + Objects.hashCode(this.stagiaire);
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
        final Resultat other = (Resultat) obj;
        if (!Objects.equals(this.ecf, other.ecf)) {
            return false;
        }
        if (!Objects.equals(this.stagiaire, other.stagiaire)) {
            return false;
        }
        return true;
    }

}
