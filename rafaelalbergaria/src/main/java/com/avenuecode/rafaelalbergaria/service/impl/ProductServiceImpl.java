package com.avenuecode.rafaelalbergaria.service.impl;

import javax.inject.Inject;

import com.avenuecode.rafaelalbergaria.dao.DaoIf;
import com.avenuecode.rafaelalbergaria.dao.ProductDao;
import com.avenuecode.rafaelalbergaria.dao.anotacoes.DaoPadrao;
import com.avenuecode.rafaelalbergaria.model.Product;
import com.avenuecode.rafaelalbergaria.service.ProductServiceIf;
import com.avenuecode.rafaelalbergaria.service.anotacoes.ServicoPadraoRN;
import com.avenuecode.rafaelalbergaria.service.rn.impl.AbstractServicoRN;

@ServicoPadraoRN
public class ProductServiceImpl extends AbstractServicoRN<Product, Integer> implements ProductServiceIf {
	
	@Inject
	@DaoPadrao
	private ProductDao dao;
	
	@Override
	protected DaoIf<Product, Integer> getDao() {
		return dao;
	}

}
