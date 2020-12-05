package com.lider.dal.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.lider.dal.model.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
	
	Optional<Product> findById(Long id);
	
	List<Product> findByBrand (String brand);

	List<Product> findByBrandOrDescription(String brand, String description);


}
