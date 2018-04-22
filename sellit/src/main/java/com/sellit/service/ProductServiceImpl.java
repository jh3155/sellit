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
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product save(Product product) {
		return productDao.save(product);
	}

	@Override
	public Optional<Product> findById(Long productId) {
		return productDao.findById(productId);
	}

	@Override
	public List<Product> findByBarcode(String barcode) {
		return productDao.findByBarcode(barcode);
	}

	@Override
	public List<Product> findByFullNameContaining(String fullName) {
		return productDao.findByFullNameContaining(fullName);
	}

}
