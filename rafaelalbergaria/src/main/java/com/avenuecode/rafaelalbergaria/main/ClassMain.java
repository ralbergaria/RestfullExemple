package com.avenuecode.rafaelalbergaria.main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.avenuecode.rafaelalbergaria.model.Image;
import com.avenuecode.rafaelalbergaria.model.Product;
import com.avenuecode.rafaelalbergaria.util.WebUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Rafael Albergaria
 * Classe utilizada para realizar as solicitações:
 * Build API classes to perform these operations
 * 
 */
public class ClassMain {
	
	private String url = "http://localhost:21957/rafaelalbergaria/rest/product";
	
	/**
	 * Get all products excluding relationships (child products, images)
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void letterA() throws IOException{
		
	    String ret = WebUtil.callRestService(url + "/findAll", MediaType.APPLICATION_JSON);
	    ObjectMapper mapper = new ObjectMapper();
	
		List<Product> list = (List<Product>) mapper.readValue(ret, new TypeReference<List<Product>>(){});
		
		for (Iterator<Product> iterator = list.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getProducts() != null || product.getImages() != null) {
				product.setProducts(null);
				product.setImages(null);
			  	WebUtil.callRestService(mapper.writeValueAsString(product), url + "/edit", MediaType.APPLICATION_JSON);
			}  	
		}
	}
	
	/**
	 * Get all products including specified relationships (child product and/or images)
	 * @throws IOException
	 */
	public void letterB() throws IOException{
	    ObjectMapper mapper = new ObjectMapper();
	    
		String ret1 = WebUtil.callRestService(url + "/find/1", MediaType.APPLICATION_JSON);
		Product product = mapper.readValue(ret1, Product.class);
	    
		String ret = WebUtil.callRestService(url + "/findAll", MediaType.APPLICATION_JSON);
	    
	    @SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) mapper.readValue(ret, new TypeReference<List<Product>>() {});    
		

	    
	    for (Iterator<Product> iterator = list.iterator(); iterator.hasNext();) {
	    	Product productIt = (Product) iterator.next();
	    	if (!productIt.getId().equals(1)) {
	    		product.addProduct(productIt);
	    	} else {
	    		Image image1 = new Image();
	    		image1.setType("teste");
	    		product.addImage(image1);
	    		
	    		Product prod = new Product();
	    		prod.setDescription("Teste");
	    		prod.setName("teste2");
	    		product.addProduct(prod);
	    	}
	    }
	    WebUtil.callRestService(mapper.writeValueAsString(product), url + "/edit", MediaType.APPLICATION_JSON);
	}
	
	/**
	 * Same as 1 using specific product identity
	 * @throws IOException 
	 */
	public Product letterC(Long id) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(url + "/find/" + id, MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("")){
			Product product = mapper.readValue(ret1, Product.class);
			return product;
		}
		
		return null;
	}
	
	/**
	 * Same as 2 using specific product identity
	 * @param ids
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public List<Product> letterD(int[] ids) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(mapper.writeValueAsString(ids), url + "/findRange", MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("[]")){
			List<Product> products = mapper.readValue(ret1,  new TypeReference<List<Product>>() {});
			return products;
		}

		return null;
	}
	
	/**
	 * Get set of child products for specific product
	 * @param productParent
	 * @return
	 * @throws IOException 
	 */
	public List<Product> getletterE(Product productParent) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(url + "/find/" + productParent.getId(), MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("")){
			Product product = mapper.readValue(ret1, Product.class);
			return product.getProducts();
		}
		return null;
	}
	
	/**
	 * Get set of child products for specific product
	 * @param productParent
	 * @return
	 * @throws IOException 
	 */
	public void setletterE(Product productParent, Product productChild) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(url + "/find/" + productParent.getId(), MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("")){
			Product product = mapper.readValue(ret1, Product.class);
			product.addProduct(productChild);
			WebUtil.callRestService(mapper.writeValueAsString(product), url + "/edit", MediaType.APPLICATION_JSON);
		}
	}
	
	/**
	 * Get set of images for specific product
	 * @param productParent
	 * @return
	 * @throws IOException 
	 */
	public List<Image> getletterF(Product productParent) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(url + "/find/" + productParent.getId(), MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("")){
			Product product = mapper.readValue(ret1, Product.class);
			return product.getImages();
		}
		return null;
	}
	
	/**
	 * Get set of images for specific product
	 * @param productParent
	 * @return
	 * @throws IOException 
	 */
	public void setletterF(Product productParent, Image imageChild) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String ret1 = WebUtil.callRestService(url + "/find/" + productParent.getId(), MediaType.APPLICATION_JSON);
		if(ret1 != null && !ret1.equals("")){
			Product product = mapper.readValue(ret1, Product.class);
			product.addImage(imageChild);
			WebUtil.callRestService(mapper.writeValueAsString(product), url + "/edit", MediaType.APPLICATION_JSON);
		}
	}
}