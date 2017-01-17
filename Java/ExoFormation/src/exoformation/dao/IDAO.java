/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exoformation.dao;

import java.util.List;

/**
 *
 * @author gwenole
 */
public interface IDAO<T> {
    public List<T> findAll();
    public List<T> findBy(Object o);
}
