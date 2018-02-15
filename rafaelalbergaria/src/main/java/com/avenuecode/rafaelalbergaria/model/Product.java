package com.avenuecode.rafaelalbergaria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the PRODUCT database table.
 * @author Rafael Albergaria
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@JsonInclude(Include.NON_EMPTY)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Image
	@JsonManagedReference 
	@OneToMany(mappedBy="product", orphanRemoval=true, fetch = FetchType.EAGER)
	private List<Image> images;

	//bi-directional many-to-one association to Product
	@JsonBackReference
	@ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="PARENT_PRODUCT_ID")
	private Product product;

	//bi-directional many-to-one association to Product
	@JsonManagedReference
	@OneToMany(mappedBy="product", orphanRemoval=true, cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Product> products;

	public Product() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		if (getImages() == null) {
			List<Image> images = new ArrayList<>();
			setImages(images);
		}
		getImages().add(image);
		image.setProduct(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setProduct(null);

		return image;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		if(getProducts() == null){
			List<Product> products = new ArrayList<>();
			setProducts(products);
		}
		getProducts().add(product);
		product.setProduct(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProduct(null);

		return product;
	}
	
	@Override
    public boolean equals(Object obj) {
		Product other = (Product) obj;
		
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
	
}