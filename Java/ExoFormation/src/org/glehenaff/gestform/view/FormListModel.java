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

    private List<Formation> formations;

    public FormListModel(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Formation> getFormations() {
        return formations;
    }
    
    public Formation getFormation(int index) {
        return formations.get(index);
    }

    public void set(List<Formation> formations) {
        this.formations = formations;
        this.fireContentsChanged(this, getSize() -1, getSize() -1);
    }

    public void add(Formation f) {
        formations.add(f);
        this.fireContentsChanged(this, getSize() -1, getSize() -1);
    }

    public void remove(Formation f) {
        formations.remove(f);
        this.fireContentsChanged(this, getSize() -1, getSize() -1);
    }

    @Override
    public int getSize() {
        return formations.size();
    }

    @Override
    public Object getElementAt(int index) {
        return formations.get(index);
    }

}
