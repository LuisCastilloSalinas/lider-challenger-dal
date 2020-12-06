package com.lider.dal.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.lider.dal.model.Product;
import com.lider.dal.repository.ProductRepository;
import com.lider.dal.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	private static final long EXPECTED_SIZE_LIST = 1L;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Mock
	private ProductRepository productRepository;

	@Mock
	private MongoTemplate mongoTemplate; 

	@Mock
	private ProductService productService;

	@Before
	public void init() {
		List<Object> listProducts = new ArrayList<>();
		Product product = new Product();
		product.setDatabaseId(new ObjectId());
		product.setId(2413L);
		product.setBrand("dsasd");
		product.setDescription("zymart xqisc");
		product.setImage("www.lider.cl/catalogo/images/homeIcon.svg");
		product.setPrice(141477.0);
		listProducts.add(product);
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		Mockito.when(mongoTemplate.find(Mockito.any(), Mockito.any())).thenReturn(listProducts);
//		Mockito.when(productRepository.findByDescriptionLike("mart")).thenReturn(listProducts);
	}

	@Test
	public void findProductByIdTestOk() {
		Product response = productServiceImpl.findById(1L);
		assertNotNull(response);
	}

	@Test
	public void findProductByIdTestNotFound() {
		Product response = productServiceImpl.findById(3002L);
		assertNull(response); 
	}

	@Test
	public void findProductByBrand() {
		List<Product> response = productServiceImpl.findByBrandLike("sas");
		assertEquals(EXPECTED_SIZE_LIST, response.size());
		 
	}
	
	@Test
	public void findProductByDescription() {
		List<Product> response = productServiceImpl.findByDescriptionLike("zymart xqisc");
		assertEquals(EXPECTED_SIZE_LIST, response.size());
		 
	}
	
	 
}
