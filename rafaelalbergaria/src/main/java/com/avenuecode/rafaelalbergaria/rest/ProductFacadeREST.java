package com.avenuecode.rafaelalbergaria.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.avenuecode.rafaelalbergaria.model.Product;
import com.avenuecode.rafaelalbergaria.service.ProductFacade;
import com.avenuecode.rafaelalbergaria.service.ProductServiceIf;
import com.avenuecode.rafaelalbergaria.service.anotacoes.ServicoPadraoRN;

/**
 * Classe utilizaded for call rest, all method consome and produce Json utilizing jackson.
 * @author Rafael Albergaria
 *
 */
@Stateless
@Path("/product")
public class ProductFacadeREST extends ProductFacade{
	
	public ProductFacadeREST() {}
	
	@Resource
	private SessionContext context;

	@Inject
	@ServicoPadraoRN
	private ProductServiceIf productRN;
	
	@POST
	@Path("/create")
    public void createEntity(Product entity) {
        productRN.save(entity);
    }

	@POST
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON})
    public void editEntity(Product entity) {
        productRN.merge(entity);
    }

	@POST
    @Path("/remove")
    public void removeEntity(Product product) {
        productRN.delete(product);
    }

	@POST
    @Path("/find/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
    public Product find(@PathParam("id") Integer id) {
        return productRN.load(id);
    }

	@POST
    @Path("/findAll")
	@Produces({MediaType.APPLICATION_JSON})
    public List<Product> findAll() {
        return productRN.findAll();
    }

	@POST
    @Path("/findRange")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Product> findManyById(List<Integer> ids) {
        return productRN.findManyById(ids);
    }

	@POST
    @Path("/count")
    public int count() {
        return productRN.countAll();
    }
}
