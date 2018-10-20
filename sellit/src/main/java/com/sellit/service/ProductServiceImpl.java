package com.sellit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.SellitApplication;
import com.sellit.dao.ProductDao;
import com.sellit.persistence.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product save(Product product) {

		if (product.getCreatedBy() == null) {
			product.setCreatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
			product.setCreatedDatetime(LocalDateTime.now());
		}

		product.setUpdatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
		product.setUpdatedDatetime(LocalDateTime.now());

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
	public List<Product> findByProductNameContaining(String productName) {
		return productDao.findByProductNameContaining(productName);
	}

}
