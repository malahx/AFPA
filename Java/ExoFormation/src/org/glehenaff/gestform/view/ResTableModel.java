/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.glehenaff.gestform.Utils;
import org.glehenaff.gestform.model.ECF;
import org.glehenaff.gestform.model.Resultat;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class ResTableModel extends AbstractTableModel {

    private final String[] entetes = {"Code", "Nom", "Prénom", "Passé", "Obtenu"};
    private final List<Stagiaire> stagiaires;
    private final ECF ecf;

    List<Listener> listeners = new ArrayList<>();

    public interface Listener {

        public void onUpdatedResultat(ECF ecf, Resultat res);

        public void onAddResultat(ECF ecf, Resultat res);

        public void onRemResultat(ECF ecf, Resultat res);
    }

    public ResTableModel(List<Stagiaire> stagiaires, ECF ecf) {
        this.stagiaires = stagiaires;
        this.ecf = ecf;
    }

    public void addEventListener(Listener listener) {
        this.listeners.add(listener);
    }

    private void fireUpdatedResultat(ECF ecf, Resultat res) {
        for (Listener listener : this.listeners) {
            listener.onUpdatedResultat(ecf, res);
        }
    }

    private void fireRemResultat(ECF ecf, Resultat res) {
        for (Listener listener : this.listeners) {
            listener.onRemResultat(ecf, res);
        }
    }

    private void fireAddResultat(ECF ecf, Resultat res) {
        for (Listener listener : this.listeners) {
            listener.onAddResultat(ecf, res);
        }
    }

    public Stagiaire getStagiaire(int rowIndex) {
        return stagiaires.get(rowIndex);
    }

    public Resultat getResultat(Stagiaire s) {
        for (Resultat r : ecf.getResultats()) {
            if (r.getStagiaire().equals(s)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return stagiaires.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stagiaire s = stagiaires.get(rowIndex);
        Resultat r = getResultat(s);
        switch (columnIndex) {
            case 0:
                return s.getCode();
            case 1:
                return s.getNom();
            case 2:
                return s.getPrenom();
            case 3:
                return r != null;
            case 4:
                return r != null && r.isObtenu();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Stagiaire s = stagiaires.get(rowIndex);
        Resultat r = getResultat(s);
        if (columnIndex == 3) {
            if (r == null) {
                r = new Resultat(false, ecf, s);
                fireAddResultat(ecf, r);
            } else {
                fireRemResultat(ecf, r);
            }
        }
        if (columnIndex == 4) {
            if (r != null) {
                boolean value = (Boolean) aValue;
                r.setObtenu(value);
                fireUpdatedResultat(ecf, r);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 3) || (columnIndex == 4);
    }
}
