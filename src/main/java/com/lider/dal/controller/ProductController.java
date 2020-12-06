package com.lider.dal.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lider.dal.model.Product;
import com.lider.dal.service.ProductService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Method find product by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("products/find-by-id/{id}")
	public ResponseEntity<Product> findProductsById(@PathVariable Long id) {
		log.info("find product by id" + id);
		Product product = productService.findById(id);
		if (Objects.nonNull(product)) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Find products by brand or description 
	 * @param brand
	 * @param description
	 * @return
	 */
	@GetMapping("products/find-by-brand-or-description")
	public ResponseEntity<List<Product>> findByBrandOrDescription(
			@RequestParam(name = "brand", required = false) String brand,
			@RequestParam(name = "description", required = false) String description) {

		List<Product> products = null;
		if (Objects.nonNull(brand)) {
			log.info("find product by brand " + brand);
			products = productService.findByBrandLike(brand);
		} else {
			log.info("find product by description " + description);
			products = productService.findByDescriptionLike(description);
		}
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
