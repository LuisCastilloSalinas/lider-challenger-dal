package com.lider.dal.controller;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
import com.lider.dal.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductRepository productRepository;

	@Before
	public void init() {
		Product product = new Product();
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
	}

	@Test
	public void findProductByIdTestOk() {

		ResponseEntity<Product> response = productController.findProductsById(1L);
		assertTrue("product find ", HttpStatus.OK.equals(response.getStatusCode()));
	}
}
