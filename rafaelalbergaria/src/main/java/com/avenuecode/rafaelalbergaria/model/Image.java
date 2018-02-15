package com.avenuecode.rafaelalbergaria.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * The persistent class for the IMAGE database table.
 * @author Rafael Albergaria
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
@JsonInclude(Include.NON_EMPTY)
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String type;

	//bi-directional many-to-one association to Product
	//JsonBackreferenc utilized for no has cicle call ocorr stackoverflow exception
	@JsonBackReference
	@ManyToOne (cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	public Image() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}