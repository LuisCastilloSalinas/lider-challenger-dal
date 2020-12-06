package com.lider.dal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lider.dal.model.Product;
import com.lider.dal.repository.ProductRepository;
import com.lider.dal.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MongoTemplate mongoTemplate; 

	@Override
	public List<Product> findByBrandLike(String brand) {
		Criteria regex = Criteria.where("brand").regex(".*" + brand + ".*");
		return mongoTemplate.find(new Query().addCriteria(regex), Product.class);
	}

	@Override
	public List<Product> findByDescriptionLike(String description) {
		Criteria regex = Criteria.where("description").regex(".*" + description + ".*");
		return mongoTemplate.find(new Query().addCriteria(regex), Product.class);
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			return null;
		}
		return product.get();
	}

}
