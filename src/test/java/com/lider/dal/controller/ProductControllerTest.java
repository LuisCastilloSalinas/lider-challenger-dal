package com.lider.dal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lider.dal.model.Product;
import com.lider.dal.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	private static final long EXPECTED_SIZE_LIST = 1L;

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@Before
	public void init() {
		List<Product> listProducts = new ArrayList<>();
		Product product = new Product();
		product.setId(2413L);
		product.setBrand("dsasd");
		product.setDescription("zymart xqisc");
		product.setImage("www.lider.cl/catalogo/images/homeIcon.svg");
		product.setPrice(141477.0);
		listProducts.add(product);
		Mockito.when(productService.findById(1L)).thenReturn(product);
		Mockito.when(productService.findByBrandLike("sas")).thenReturn(listProducts);
		Mockito.when(productService.findByDescriptionLike("mart")).thenReturn(listProducts);
	}

	@Test
	public void findProductByIdTestOk() {
		ResponseEntity<Product> response = productController.findProductsById(1L);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void findProductByIdTestNotFound() {
		ResponseEntity<Product> response = productController.findProductsById(3002L);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void findProductByBrand() {
		ResponseEntity<List<Product>> response = productController.findByBrandOrDescription("sas", null);
		assertEquals(EXPECTED_SIZE_LIST, response.getBody().size());
	}
	
	@Test
	public void findProductByBrandNotFound() {
		ResponseEntity<List<Product>> response = productController.findByBrandOrDescription("sasds", null);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void findProductByDescription() {
		ResponseEntity<List<Product>> response = productController.findByBrandOrDescription(null, "mart");
		assertEquals(EXPECTED_SIZE_LIST, response.getBody().size());
	}
	
	@Test
	public void findProductByDescriptionNotFound() {
		ResponseEntity<List<Product>> response = productController.findByBrandOrDescription(null, "alllasd");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
