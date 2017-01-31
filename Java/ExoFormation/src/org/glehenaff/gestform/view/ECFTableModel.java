/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.glehenaff.gestform.model.ECF;
import org.glehenaff.gestform.model.Resultat;

/**
 *
 * @author gwenole
 */
public class ECFTableModel extends AbstractTableModel {

    private final String[] entetes = {"Nom", "N° Résultats", "N° Obtenu"};
    private List<ECF> ecfs;

    List<Listener> listeners = new ArrayList<>();

    public interface Listener {

        public void onUpdatedEcf(ECF e);
    }

    public ECFTableModel() {
        this.ecfs = new ArrayList<>();
    }

    public ECFTableModel(List<ECF> ecfs) {
        this.ecfs = ecfs;
    }

    public void addEventListener(Listener listener) {
        this.listeners.add(listener);
    }

    private void fireUpdatedEcf(ECF e) {
        for (Listener listener : this.listeners) {
            listener.onUpdatedEcf(e);
        }
    }

    public void add(ECF e) {
        ecfs.add(e);
        this.fireTableDataChanged();
    }

    public void remove(ECF e) {
        ecfs.remove(e);
        this.fireTableDataChanged();
    }

    public void reset() {
        ecfs = new ArrayList<>();
        this.fireTableDataChanged();
    }

    public ECF getEcf(int rowIndex) {
        return ecfs.get(rowIndex);
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
        return ecfs.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return ecfs.get(rowIndex).getNom();

            case 1:
                return ecfs.get(rowIndex).getResultats().size();

            case 2:
                Integer i = 0;
                for (Resultat r : ecfs.get(rowIndex).getResultats()) {
                    if (r.isObtenu()) {
                        i++;
                    }
                }
                return i;

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String value = (String) aValue;
        ECF ecf = ecfs.get(rowIndex);
        if (columnIndex == 0) {
            if (!value.isEmpty()) {
                ecf.setNom(value);
                fireUpdatedEcf(ecf);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 0);
    }
}
