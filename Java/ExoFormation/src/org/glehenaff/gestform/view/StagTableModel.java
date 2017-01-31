/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class StagTableModel extends AbstractTableModel {

    private final String[] entetes = {"Code", "Nom", "Prénom"};
    private List<Stagiaire> stagiaires;

    List<Listener> listeners = new ArrayList<>();

    public interface Listener {

        public void onUpdatedStag(Stagiaire s);
    }

    public StagTableModel() {
        this.stagiaires = new ArrayList<>();
    }

    public StagTableModel(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public void addEventListener(Listener listener) {
        this.listeners.add(listener);
    }

    private void fireUpdatedStag(Stagiaire s) {
        for (Listener listener : this.listeners) {
            listener.onUpdatedStag(s);
        }
    }

    public void set(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
        this.fireTableDataChanged();
    }

    public void add(Stagiaire s) {
        stagiaires.add(s);
        this.fireTableDataChanged();
    }

    public void remove(Stagiaire s) {
        stagiaires.remove(s);
        this.fireTableDataChanged();
    }

    public void reset() {
        stagiaires = new ArrayList<>();
        this.fireTableDataChanged();
    }

    public Stagiaire getStagiaire(int rowIndex) {
        return stagiaires.get(rowIndex);
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
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
        switch (columnIndex) {

            case 0:
                return stagiaires.get(rowIndex).getCode();

            case 1:
                return stagiaires.get(rowIndex).getNom();

            case 2:
                return stagiaires.get(rowIndex).getPrenom();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String value = (String) aValue;
        if (value.isEmpty()) {
            return;
        }
        Stagiaire s = stagiaires.get(rowIndex);
        if (columnIndex == 0) {
            s.setCode(value);
            fireUpdatedStag(s);
        }
        if (columnIndex == 1) {
            s.setNom(value);
            fireUpdatedStag(s);
        }
        if (columnIndex == 2) {
            s.setPrenom(value);
            fireUpdatedStag(s);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
