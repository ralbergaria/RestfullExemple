package com.avenuecode.rafaelalbergaria.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.avenuecode.rafaelalbergaria.dao.DaoIf;
import com.avenuecode.rafaelalbergaria.dao.ProductDao;
import com.avenuecode.rafaelalbergaria.dao.anotacoes.DaoPadrao;
import com.avenuecode.rafaelalbergaria.model.Product;

@DaoPadrao
public class ProductDaoImpl extends AbstractDaoImpl<Product, Integer> implements ProductDao {

	@PersistenceContext(unitName="restinPU")
	private EntityManager em;

	@Override
	protected DaoIf<Product, Integer> getDao() {
		return this;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
