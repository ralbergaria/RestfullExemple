/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avenuecode.rafaelalbergaria.service.rn.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.avenuecode.rafaelalbergaria.dao.DaoIf;
import com.avenuecode.rafaelalbergaria.service.ServicoRNIf;

public abstract class AbstractServicoRN<Entity,Key extends Serializable> implements ServicoRNIf<Entity,Key>{

		    
    public AbstractServicoRN(){
    
    }
    
    protected abstract DaoIf<Entity,Key> getDao();
    
    @Override
    public List<Entity> findAll() {
        return getDao().findAll();
    }

    @Override
    public int countAll() {
        return getDao().countAll();
    }

    @Override
    public List<Entity> findAll(int firstResult, int maxResults, String orderByProperty, boolean ascending) {
        return getDao().findAll(firstResult, maxResults, orderByProperty, ascending);
    }

    @Override
    public Entity load(Key ids) {
        return getDao().load(ids);
    }

    @Override
    public List<Entity> findManyById(Key[] ids) {
        return getDao().findManyById(ids);
    }

    @Override
    public List<Entity> findManyById(Collection<? extends Key> ids) {
        return getDao().findManyById(ids);
    }
    

    @Override
    public void update(Entity entity) {
        getDao().update(entity);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Entity entity) {
        getDao().save(entity);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(Entity entity) {
        getDao().save(entity);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Key id) {
        getDao().delete(id);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Entity entity) {
        getDao().delete(entity);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void merge(Entity entity){
    	  getDao().merge(entity);
    }
    
   
}
