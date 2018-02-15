package com.avenuecode.rafaelalbergaria.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.avenuecode.rafaelalbergaria.model.Product;

/**
 * 
 * @author Rafael Albergaria
 *
 */
public abstract class ProductFacade {
	
    public abstract void createEntity(Product entity);

    public  abstract void editEntity(Product entity);

    public abstract void removeEntity(Product entity);

    public abstract Product find(Integer id);
    
    public abstract List<Product> findAll();

    public abstract List<Product> findManyById(List<Integer> ids);

    public abstract int count();
}