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

    public ECFTableModel() {
        this.ecfs = new ArrayList<>();
    }

    public ECFTableModel(List<ECF> ecfs) {
        this.ecfs = ecfs;
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
}
