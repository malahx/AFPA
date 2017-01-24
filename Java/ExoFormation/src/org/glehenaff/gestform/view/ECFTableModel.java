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
    private final List<ECF> ecf;

    public ECFTableModel() {
        this.ecf = new ArrayList<>();
    }

    public ECFTableModel(List<ECF> ecf) {
        this.ecf = ecf;
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return ecf.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    public ECF getEcf(int rowIndex) {
        return ecf.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return ecf.get(rowIndex).getNom();

            case 1:
                return ecf.get(rowIndex).getResultats().size();

            case 2:
                Integer i = 0;
                for (Resultat r : ecf.get(rowIndex).getResultats()) {
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
