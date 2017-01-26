/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.glehenaff.gestform.dao.AlreadyExistsException;
import org.glehenaff.gestform.dao.ResultatDAO;
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
    private final AddResultatToECF parent;

    public ResTableModel(AddResultatToECF parent, List<Stagiaire> stagiaires, ECF ecf) {
        this.stagiaires = stagiaires;
        this.ecf = ecf;
        this.parent = parent;
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
                try {
                    r = new Resultat(false, ecf, s);
                    r = ResultatDAO.Instance().insert(r);
                    ecf.addResultat(r);
                    parent.getParent().RefreshData();
                } catch (AlreadyExistsException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (ResultatDAO.Instance().delete(r)) {
                    ecf.remResultat(r);
                    parent.getParent().RefreshData();
                }
            }
        }
        if (columnIndex == 4) {
            if (r != null) {
                r.setObtenu(((Boolean) aValue).booleanValue());
                ResultatDAO.Instance().update(r);
                parent.getParent().RefreshData();
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 3) || (columnIndex == 4);
    }
}
