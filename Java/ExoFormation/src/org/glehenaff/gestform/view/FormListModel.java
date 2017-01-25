/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.List;
import javax.swing.AbstractListModel;
import org.glehenaff.gestform.model.Formation;

/**
 *
 * @author gwenole
 */
public class FormListModel extends AbstractListModel {

    private final List<Formation> formations;

    public FormListModel(List<Formation> formations) {
        this.formations = formations;
    }

    @Override
    public int getSize() {
        return formations.size();
    }

    @Override
    public Object getElementAt(int index) {
        return formations.get(index);
    }

    public Formation getFormation(int index) {
        return formations.get(index);
    }
 
}
