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
    private final List<Stagiaire> stagiaires;

    public StagTableModel() {
        this.stagiaires = new ArrayList<>();
    }
    
    public StagTableModel(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
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

    public Stagiaire getFormation(int rowIndex) {
        return stagiaires.get(rowIndex);
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
}
