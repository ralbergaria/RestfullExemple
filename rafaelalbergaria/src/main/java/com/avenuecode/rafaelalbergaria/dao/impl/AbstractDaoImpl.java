package com.avenuecode.rafaelalbergaria.dao.impl;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.avenuecode.rafaelalbergaria.dao.DaoIf;

/**
 *
 * @author Rafael Albergaria Carmo
 * 
 * @version 0.1
 */

public abstract class AbstractDaoImpl<Entity,Key extends Serializable> extends AbstractDao<Entity,Key> implements DaoIf<Entity,Key>{

    public AbstractDaoImpl(){
        super();
    }//end method
    
    protected abstract DaoIf<Entity,Key> getDao();
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e ");
        return (List<Entity>)query.getResultList();
    }//end method

    @Override
    public int countAll() {
        Query query = getEntityManager().createQuery("SELECT COUNT(e) FROM " + getEntityClassName() + " e ");
        Number num = (Number)query.getSingleResult();
        return num != null ? num.intValue() : 0 ;
    }//end method

    @SuppressWarnings("unchecked")    
    @Override
    public List<Entity> findAll(int firstResult, int maxResults, String orderByProperty, boolean ascending) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e ORDER BY :pOrderBy " + (ascending?"ASC":"DESC"));
        query.setParameter("pOrderBy",orderByProperty);
        return (List<Entity>)query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity load(Key id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")    
    @Override
    public List<Entity> findManyById(Key[] ids) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " IN :pIds ");
        query.setParameter("pIds", ids);
        return (List<Entity>)query.getResultList();
    }

    
    @SuppressWarnings("unchecked")    
    @Override
    public List<Entity> findManyById(Collection<? extends Key> ids) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " IN :pIds ");
        query.setParameter("pIds", ids);
        return (List<Entity>)query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean exists( Key key ){
    	Query query = getEntityManager().createQuery("SELECT COUNT(e) FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " = :pId ");
    	query.setParameter("pId", key );
    	List<Number> result = (List<Number>)query.getResultList();
    	return result != null && result.size() > 0 ? result.get(0).intValue() > 0 : false ;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Entity findById(Key id) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " = :pId ");
        query.setParameter("pId",id);
       List<Entity> resultado = (List<Entity>) query.getResultList();
       return ( resultado != null && resultado.size() > 0 ) ? resultado.get(0) : null ;
    }

    @Override
    public void detach(Entity entity) {
        getEntityManager().detach(entity);
    }

    @Override
    public void refresh(Entity entity) {
        getEntityManager().refresh(entity);
    }
    @Override
    public Entity merge(Entity entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public void update(Entity entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void save(Entity entity) {
    	getEntityManager().persist(entity);
    }

    @Override
    public void saveOrUpdate(Entity entity) {
    	Key value = null ;
    	
    	try{
    		value = (Key) getEntityKeyIdValue( entity ) ;
    	}catch( Exception ex ){
    		//IGNORE: when id is null api throws exception ...
    	}

    	//case for database generated key
    	if( value == null ){
    		save( entity );
    	}else{
    		if( getEntityManager().contains( entity ) ){
    			update( entity );
    		}else{
        		if( exists( value ) ){
        			update( entity );
        		}else{
        			save( entity );
        		}
    		}
    	}
    }

   @Override
    public void delete(Key id) {
        getEntityManager().remove(getEntityManager().find(getEntityClass(), id));
        
    }//end method

    @Override
    public void delete(Entity entity) {
        getEntityManager().remove(entity);
    }//end method
            
    
}
