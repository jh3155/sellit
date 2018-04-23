package com.sellit.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.sellit.persistence.Product;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	}

	// TODO: replace the close button with the global back button
	@FXML
	private void close() {
		AppUtil.popCenterPaneStack();
	}

}
