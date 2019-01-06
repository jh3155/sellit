package com.sellit.controller.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.controller.Controller;
import com.sellit.persistence.Product;
import com.sellit.service.ProductService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class SaleViewController extends Controller {

	@Autowired
	private ProductService productService;

	@FXML
	private TextField barcodeField;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public SaleViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		Platform.runLater(() -> barcodeField.requestFocus());
	}

	@FXML
	private void searchProduct() {
		final Product product = productService.findByBarcode(barcodeField.getText());

		if (product == null) {
			System.out.println("null");
		} else {
			System.out.println(product.getProductName());
		}

	}

	@Override
	public void refresh() {
	}

}
