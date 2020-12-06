package com.lider.dal.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "products")
public class Product {
	
	@Id
	private ObjectId  databaseId;
	
	private Long id;
	
	private String brand;
	
	private String description;
	
	private String image;
	
	private Double price;

}
