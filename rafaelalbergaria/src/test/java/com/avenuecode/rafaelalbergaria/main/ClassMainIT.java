package com.avenuecode.rafaelalbergaria.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.RuntimeDelegate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.avenuecode.rafaelalbergaria.model.Image;
import com.avenuecode.rafaelalbergaria.model.Product;
import com.avenuecode.rafaelalbergaria.rest.ProductFacadeREST;
/**
 * Classe Utilizada para realizar os testes
 * @author rafael.carmo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassMainIT {

	@Before
	public void setUp() {
		RuntimeDelegate.setInstance(runtimeDelegate);

		Connection con = null;
		Statement stm = null;
		try {
			con = getConnection();
			stm = con.createStatement();

			con.setAutoCommit(false);

			ResultSet rs = stm.executeQuery(" SELECT * FROM PRODUCT ");

			if (!rs.next()){
				//Insere itens na tabela Product
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (1, 'PROD1', 'PROD1', NULL)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (2, 'PROD1', 'PROD1', 1)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (3, 'PROD1', 'PROD1', 1)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (4, 'PROD1', 'PROD1', 1)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (5, 'PROD1', 'PROD1', 1)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (6, 'PROD1', 'PROD1', 1)");
				stm.executeUpdate("insert into PRODUCT (ID, DESCRIPTION, NAME, PARENT_PRODUCT_ID) VALUES (7, 'PROD1', 'PROD1', 1)");

				//Insere itens na tabela IMAGE 
				stm.executeUpdate("insert into IMAGE (ID, TYPE, PRODUCT_ID) VALUES (1, 'TESTE', 1)");
				stm.executeUpdate("insert into IMAGE (ID, TYPE, PRODUCT_ID) VALUES (2, 'TESTE', 1)");
				stm.executeUpdate("insert into IMAGE (ID, TYPE, PRODUCT_ID) VALUES (3, 'TESTE', 1)");
				stm.executeUpdate("insert into IMAGE (ID, TYPE, PRODUCT_ID) VALUES (4, 'TESTE', 1)");
				stm.executeUpdate("insert into IMAGE (ID, TYPE, PRODUCT_ID) VALUES (5, 'TESTE', 1)");
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}

			if(stm != null)
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
		}
	}

	@Test
	public void testLetterA() throws Exception {
		instance.letterA();


		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stm = con.createStatement();

			rs = stm.executeQuery(" SELECT PARENT_PRODUCT_ID FROM PRODUCT P ");

			while (rs.next()){
				Assert.assertNull(rs.getString("PARENT_PRODUCT_ID"));
			}

			rs = stm.executeQuery(" SELECT PRODUCT_ID FROM IMAGE ");

			Assert.assertFalse(rs.next());


		} finally {
			con.close();
			stm.close();
		}    

	}

	@Test
	public void testLetterB() throws Exception {
		instance.letterB();		

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null; 
		ResultSet rs2 = null;
		try {
			con = getConnection();
			stm = con.createStatement();

			rs = stm.executeQuery(" SELECT p.PARENT_PRODUCT_ID FROM PRODUCT P where p.PARENT_PRODUCT_ID is not null ");

			Assert.assertTrue("Esperado existir filhos produtos, mas não existe" , rs.next());


			rs2 = stm.executeQuery(" SELECT PRODUCT_ID FROM IMAGE ");

			Assert.assertTrue("Esperado existir filhos imagem, mas não existe", rs2.next());



		} finally {
			rs.close();
			rs2.close();
			con.close();
			stm.close();

		}

	}

	@Test
	public void testLetterC() throws Exception {
		Product product = instance.letterC(1L);
		Assert.assertNotNull(product);

		Product productNull = instance.letterC(20L);
		Assert.assertNull(productNull);
	}

	@Test
	public void testLetterD() throws Exception {
		List<Product> products = instance.letterD( new int[]{1,2});
		Assert.assertNotNull(products);

		List<Product> productNull = instance.letterD(new int[]{30,40});
		Assert.assertNull(productNull);
	}

	@Test
	public void testLetterE() throws Exception {
		Product productParent = new Product();
		productParent.setId(1);
		List<Product> productChilds = instance.getletterE(productParent);
		Assert.assertNotNull("getLetterE não funcionou, filhos retornados nulos", productChilds);

		productParent.setId(30);
		productChilds = instance.getletterE(productParent);
		Assert.assertNull("getLetterE não funcionou, filhos retornados NAO nulos", productChilds);


		Product productChild = new Product();
		productChild.setName("TesteLetterE");
		productChild.setDescription("TesteLetterE");

		productParent.setId(1);

		instance.setletterE(productParent, productChild);

		productChilds = instance.getletterE(productParent);

		Boolean ok = false;

		for(Product prd : productChilds){
			if(prd.getName().equals("TesteLetterE")){
				ok = true;
				break;
			}
		}

		Assert.assertTrue("setletterE não funcionou", ok);
	}

	@Test
	public void testLetterF() throws Exception {
		Product productParent = new Product();
		productParent.setId(1);
		List<Image> imageChilds = instance.getletterF(productParent);
		Assert.assertNotNull("getLetterF não funcionou, filhos retornados nulos", imageChilds);

		productParent.setId(30);
		imageChilds = instance.getletterF(productParent);
		Assert.assertNull("getLetterF não funcionou, filhos retornados NAO nulos", imageChilds);


		Image imageChild = new Image();
		imageChild.setType("TesteLetterF");

		productParent.setId(1);

		instance.setletterF(productParent, imageChild);

		imageChilds = instance.getletterF(productParent);

		Boolean ok = false;

		for(Image prd : imageChilds){
			if(prd.getType().equals("TesteLetterF")){
				ok = true;
				break;
			}
		}

		Assert.assertTrue("setletterF não funcionou", ok);
	}


	public Connection getConnection() throws SQLException {		


		Context ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		DataSource ds = null;
		try {
			ds = (DataSource)ctx.lookup("restin");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		Connection con = ds.getConnection();

		return con;
	}

	@Mock
	private EntityManager em;

	@Mock
	private UriInfo uriInfo;

	@Mock
	private RuntimeDelegate runtimeDelegate;

	@InjectMocks
	private ClassMain instance;

	@InjectMocks
	private ProductFacadeREST instanceRest;

}