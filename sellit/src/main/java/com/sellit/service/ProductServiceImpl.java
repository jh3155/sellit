package com.sellit.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.dao.ProductDao;
import com.sellit.persistence.Product;

@Service
@Transactional
public class ProductServiceImpl {

	@Autowired
	private ProductDao productDao;

	public Product save(Product product) {
		return productDao.save(product);
	}

	public Optional<Product> findById(Long productId) {
		return productDao.findById(productId);
	}

	public List<Product> findByBarcode(String barcode) {
		return productDao.findByBarcode(barcode);
	}

}
