/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.dao;

import java.util.List;
import org.glehenaff.gestform.model.Formation;

/**
 *
 * @author gwenole
 */
public interface IDAO<T> {
    public List<T> findAll();
    public List<T> findBy(Formation formation);
    public T insert(T o) throws AlreadyExistsException;
    public boolean delete(T o) throws AlreadyExistsException;
}
