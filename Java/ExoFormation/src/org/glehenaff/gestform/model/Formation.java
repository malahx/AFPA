/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * @author gwenole
 */
public class Formation implements Serializable {

    private int id;
    private List<Stagiaire> stagiaires = new ArrayList<>();
    private List<ECF> ecfs = new ArrayList<>();
    private String nom;

    public Formation(int id, String nom) {
        this.id = id;
        this.nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void addStagiaire(Stagiaire stagiaire) {
        this.stagiaires.add(stagiaire);
    }

    public void remStagiaire(Stagiaire stagiaire) {
        this.stagiaires.remove(stagiaire);
        for (ECF e : ecfs) {
            for (Resultat r: e.getResultats()) {
                if (r.getStagiaire().equals(stagiaire)) {
                    e.remResultat(r);
                }
            }
        }
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
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

    public void setECFs(List<ECF> ecfs) {
        this.ecfs = ecfs;
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
        int hash = 7;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
