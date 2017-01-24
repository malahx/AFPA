/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.List;
import javax.swing.DefaultListModel;
import org.glehenaff.gestform.model.Formation;

/**
 *
 * @author gwenole
 */
public class FormListModel extends DefaultListModel {

    public FormListModel(List<Formation> formations) {
        for (Formation formation : formations) {
            this.addElement(formation);
        }
    }    
}
