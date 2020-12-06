package com.lider.dal.service;

import java.util.List;

import com.lider.dal.model.Product;

public interface ProductService {
	
	List<Product> findByBrandLike(String brand); 
	
	List<Product> findByDescriptionLike(String description);
	
	Product findById(Long id);
	
}
