package com.sellit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellit.persistence.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

	Product findByBarcode(String barcode);

	List<Product> findByProductNameContaining(String productName);

}
