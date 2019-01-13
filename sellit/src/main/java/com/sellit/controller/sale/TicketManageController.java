package com.sellit.controller.sale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.controller.Controller;
import com.sellit.persistence.Product;
import com.sellit.persistence.Ticket;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class TicketManageController extends Controller {

	@Autowired
	private ProductService productService;

	@FXML
	private TextField barcodeField;

	private RetailSaleController parent;

	private Ticket ticket;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public TicketManageController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		Platform.runLater(() -> barcodeField.requestFocus());
	}

	@FXML
	private void searchProduct() {
		final Product product = productService.findByBarcode(barcodeField.getText());

		barcodeField.setText(StringUtils.EMPTY);

		if (product == null) {
			AppUtil.showPopupWindow("Product not found!");
			return;
		}

		parent.addProduct(product);

		barcodeField.requestFocus();
	}

	@Override
	public void refresh() {
	}

	public void setParent(RetailSaleController parent) {
		this.parent = parent;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
