package com.lider.dal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lider.dal.model.Product;
import com.lider.dal.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Method find product by id 
     * @param id
     * @return
     */
    @GetMapping("products/find-by-id/{id}")
    public ResponseEntity<Product> findProductsById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }


    @GetMapping("products/find-by-brand-or-description")
    public ResponseEntity<List<Product>> findByBrandOrDescription(@RequestParam(name ="brand" , required = false) String brand , @RequestParam(name ="description" , required = false) String description) {
        List<Product> products = productRepository.findByBrandOrDescription(brand,description);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
