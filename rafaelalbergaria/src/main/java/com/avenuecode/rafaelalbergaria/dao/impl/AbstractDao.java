package com.avenuecode.rafaelalbergaria.dao.impl;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.beans.Expression;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.text.WordUtils;


public abstract class AbstractDao<Entity,Key extends Serializable>{ 
    
    //Workaround, because Java Gerenics use erasable type.
    //Think how to implement solution to work with extended classes.
    
	protected static final String DEFAULT_NAME_ENTITY_KEY_ID = "id" ;
	protected static final String PREFIX_GET_METHOD = "get" ;
	protected static final String PREXIF_SET_METHOD = "set" ;
	
	private Class<Entity> entityClass;    
    protected abstract EntityManager getEntityManager();
    private String nameOfEntityKeyId;
    
    public AbstractDao(){
        this.entityClass = getTypeParameterEntityClazz();
    }//end method  
    
    private Class<Entity> getTypeParameterEntityClazz(){
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
    }//end method
    
    protected final String getEntityClassName(){
        return this.entityClass.getSimpleName();
    }//end method
        
    protected final Class<Entity> getEntityClass(){
        return this.entityClass;
    }//end mehtod
    
    /**
     * Buscar no nome do m�todo que � uma chave prim�ria da entidade. 
     * Isto �, aquele que est� com anotacao @Id.
     * 
     * @return
     */
    protected final String getNameOfEntityKeyId(){
    	if( this.nameOfEntityKeyId == null ){
	    	String id = DEFAULT_NAME_ENTITY_KEY_ID ;
	    	for( SingularAttribute<?,?> sa : this.getEntityManager().getMetamodel().managedType( getEntityClass() ).getSingularAttributes() ){
	    		if( sa.isId() ){
	    			id = sa.getName();
	    	    	this.nameOfEntityKeyId = id;
	    			return id ;
	    		}
	    	}
	    	this.nameOfEntityKeyId = id;
	    	return id;
    	}else{
    		return this.nameOfEntityKeyId;
    	}
    }//end method

    @SuppressWarnings("unchecked")
    protected final Key getEntityKeyIdValue( Entity entity ) throws Exception {
    	java.beans.Expression expression = new Expression( entity , getNameOfEntityKeyIdAsGetMethod() , new Object[0] );
    	expression.execute();
    	Key value = (Key)expression.getValue();
    	return value;
    }//end method
    
    protected final String getNameOfEntityKeyIdAsGetMethod(){
    	String name = getNameOfEntityKeyId();
    	name = WordUtils.capitalize(name);
    	return PREFIX_GET_METHOD + name ;
    }//end method
    
	public abstract boolean exists(Key key);

	public abstract Entity findById(Key id);
     
}
