/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avenuecode.rafaelalbergaria.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface DaoIf<Entity,Key extends Serializable> {
    public List<Entity> findAll();
    public int countAll();
    public List<Entity> findAll( int firstResult, int maxResults, String orderByProperty , boolean ascending);
    public Entity load(Key ids);
    public List<Entity> findManyById(Key[] ids);
    public List<Entity> findManyById(Collection<? extends Key> ids);
    public void detach(Entity entity);
    public void refresh(Entity entity);
    public void update(Entity entity);
    public void save(Entity entity);
    public void saveOrUpdate(Entity entity);
    public void delete(Key id);
    public void delete(Entity entity);
    public Entity merge(Entity entity);
}//end interface
