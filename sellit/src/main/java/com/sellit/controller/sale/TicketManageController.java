package com.sellit.controller.sale;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.container.PaneContainer;
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
		String barcode = barcodeField.getText();
		final Product product = productService.findByBarcode(barcode);

		barcodeField.setText(StringUtils.EMPTY);

		if (product == null) {
			AppUtil.showPopupWindow(String.format("Product not found with barcode=[%s]", barcode));
			return;
		}

		parent.addProduct(product);

		barcodeField.requestFocus();
	}

	@FXML
	private void makePayment() throws IOException {

		if (ticket.getTotalAmount() <= 0) {
			AppUtil.showPopupWindow(String.format("Ticket total is $0.00."));
			return;
		}

		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/sale/Payment.fxml", this);
		PaymentController paymentController = (PaymentController) paneContainer.getController();
		paymentController.setParent(parent);
		paymentController.setTicket(ticket);
		paymentController.refresh();

		AppUtil.showDialogWindow(paneContainer);
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
