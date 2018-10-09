package com.sellit.service;

import java.util.List;
import java.util.Optional;

import com.sellit.persistence.Product;

public interface ProductService {

	Product save(Product product);

	Optional<Product> findById(Long productId);

	List<Product> findByBarcode(String barcode);

	List<Product> findByProductNameInEnglishContaining(String productName);

}