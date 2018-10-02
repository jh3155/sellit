package com.sellit.controller.inventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.constants.StatusConstants;
import com.sellit.persistence.Department;
import com.sellit.persistence.Product;
import com.sellit.service.DepartmentService;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;
import com.sellit.util.ValidateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

@Component
public class ProductManageViewController {

	@Autowired
	private ProductService productService;

	@Autowired
	private DepartmentService departmentService;

	@FXML
	private ChoiceBox<Department> departmentChoiceBox;

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

	private List<Department> departments;

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
		departments = departmentService.findByStatusOrderByDepartmentNameAsc(StatusConstants.ACTIVE);
		departmentChoiceBox.getItems().addAll(departments);

		departmentChoiceBox.setConverter(new StringConverter<Department>() {

			@Override
			public String toString(Department object) {
				return object.getDepartmentName();
			}

			@Override
			public Department fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	public void clearFields() {

		if (product.getDepartment() != null) {
			departmentChoiceBox.getSelectionModel().select(product.getDepartment());
		}

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

		product.setDepartment(departmentChoiceBox.getSelectionModel().getSelectedItem());

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
		} else if (departmentChoiceBox.getSelectionModel().getSelectedItem() == null) {
			AppUtil.showPopupWindow("Invalid department", "");
			return false;
		}

		return true;
	}

}