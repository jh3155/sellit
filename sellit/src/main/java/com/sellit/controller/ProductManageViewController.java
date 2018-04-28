package com.sellit.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.persistence.Product;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;
import com.sellit.util.ValidateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class ProductManageViewController {

	@Autowired
	private ProductService productService;

	@FXML
	private TextField barcodeField;

	@FXML
	private TextField fullNameField;

	@FXML
	private TextField shortNameInEnglishField;

	@FXML
	private TextField shortNameInOtherLanguageField;

	@FXML
	private TextField inventoryOnHandField;

	@FXML
	private TextField safetyInventoryOnHandField;

	@FXML
	private TextField costAmountField;

	@FXML
	private TextField salesAmountField;

	private Product product;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public ProductManageViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	public void clearFields() {
		barcodeField.setText(product.getBarcode());
		fullNameField.setText(product.getFullName());
		shortNameInEnglishField.setText(product.getShortNameInEnglish());
		shortNameInOtherLanguageField.setText(product.getShortNameInOtherLanguage());
		inventoryOnHandField.setText(Objects.toString(product.getInventoryOnHand(), "0"));
		safetyInventoryOnHandField.setText(Objects.toString(product.getSafetyInventoryOnHand(), "0"));
		costAmountField.setText(Objects.toString(product.getCostAmount(), "0.00"));
		salesAmountField.setText(Objects.toString(product.getSalesAmount(), "0.00"));
	}

	public void setProduct(Product product) {
		this.product = product;

		clearFields();
	}

	@FXML
	private void save() throws IOException {

		if (validate() == false) {
			return;
		}

		product.setBarcode(barcodeField.getText());
		product.setFullName(fullNameField.getText());
		product.setShortNameInEnglish(shortNameInEnglishField.getText());
		product.setShortNameInOtherLanguage(shortNameInOtherLanguageField.getText());
		product.setInventoryOnHand(Integer.valueOf(inventoryOnHandField.getText()));
		product.setSafetyInventoryOnHand(Integer.valueOf(safetyInventoryOnHandField.getText()));
		product.setCostAmount(new BigDecimal(costAmountField.getText()));
		product.setSalesAmount(new BigDecimal(salesAmountField.getText()));

		productService.save(product);

		AppUtil.showPopupWindow("Product [" + product.getFullName() + "] has been saved", "");
		
		AppUtil.popCenterPaneStack();
	}

	// TODO: replace the close button with the global back button
	@FXML
	private void close() {
		AppUtil.popCenterPaneStack();
	}

	private boolean validate() {

		if (ValidateUtil.invalidLength(barcodeField, 50)) {
			AppUtil.showPopupWindow("Barcode has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(fullNameField, 50)) {
			AppUtil.showPopupWindow("Full name is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(shortNameInEnglishField, 50)) {
			AppUtil.showPopupWindow("Short name in English is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(shortNameInOtherLanguageField, 50)) {
			AppUtil.showPopupWindow("Short name in other language is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.invalidInteger(inventoryOnHandField)) {
			AppUtil.showPopupWindow("Inventory on hand is not a valid number", "");
			return false;
		} else if (ValidateUtil.invalidInteger(safetyInventoryOnHandField)) {
			AppUtil.showPopupWindow("Safety inventory on hand is not a valid number", "");
			return false;
		} else if (ValidateUtil.invalidBigDecimal(costAmountField)) {
			AppUtil.showPopupWindow("Cost amount is not a valid decimal number", "");
			return false;
		} else if (ValidateUtil.invalidBigDecimal(salesAmountField)) {
			AppUtil.showPopupWindow("Sales amount is not a valid decimal number", "");
			return false;
		}

		return true;
	}

}
