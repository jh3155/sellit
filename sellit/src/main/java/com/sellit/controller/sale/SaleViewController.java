package com.sellit.controller.sale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.controller.Controller;
import com.sellit.enums.ConfigEnum;
import com.sellit.persistence.Product;
import com.sellit.persistence.Ticket;
import com.sellit.service.ConfigService;
import com.sellit.service.ProductService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class SaleViewController extends Controller implements InitializingBean {

	@Autowired
	private ProductService productService;

	@Autowired
	private ConfigService configService;

	@FXML
	private TextField barcodeField;

	private Ticket currentTransaction;

	private Double tax1Rate;
	private Double tax2Rate;
	private Double tax3Rate;

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

	private void addProduct(final Product product) {
		if (currentTransaction == null) {
			currentTransaction = new Ticket(tax1Rate, tax2Rate, tax3Rate);
		}

		currentTransaction.createTicketData(product);
	}

	@FXML
	private void searchProduct() {
		final Product product = productService.findByBarcode(barcodeField.getText());

		barcodeField.setText(StringUtils.EMPTY);

		if (product == null) {
			System.out.println("product not found");
			return;
		}

		addProduct(product);
		System.out.println(currentTransaction.getTotalAmount());

		barcodeField.requestFocus();
	}

	@Override
	public void refresh() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final String tax1RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX1_RATE.getCategory(), ConfigEnum.TAX1_RATE.getValue());

		if (tax1RateConfig != null) {
			tax1Rate = Double.valueOf(tax1RateConfig);
		}

		final String tax2RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX2_RATE.getCategory(), ConfigEnum.TAX2_RATE.getValue());

		if (tax2RateConfig != null) {
			tax2Rate = Double.valueOf(tax2RateConfig);
		}

		final String tax3RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX3_RATE.getCategory(), ConfigEnum.TAX3_RATE.getValue());

		if (tax3RateConfig != null) {
			tax3Rate = Double.valueOf(tax3RateConfig);
		}

	}

}
