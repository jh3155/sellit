package com.sellit.controller.inventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.StatusConstants;
import com.sellit.controller.Controller;
import com.sellit.persistence.Department;
import com.sellit.persistence.Product;
import com.sellit.service.DepartmentService;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;
import com.sellit.util.ValidateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

@Component
public class ProductManageViewController extends Controller {

	@Autowired
	private ProductService productService;

	@Autowired
	private DepartmentService departmentService;

	@FXML
	private ChoiceBox<Department> departmentChoiceBox;

	@FXML
	private TextField barcodeField;

	@FXML
	private TextField productNameField;

	@FXML
	private TextField productMenuNameField;

	@FXML
	private TextField inventoryOnHandField;

	@FXML
	private TextField safetyInventoryOnHandField;

	@FXML
	private TextField unitPriceField;

	@FXML
	private CheckBox taxable1Checkbox;

	@FXML
	private CheckBox taxable2Checkbox;

	@FXML
	private CheckBox taxable3Checkbox;

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
		productNameField.setText(product.getProductName());
		productMenuNameField.setText(product.getProductMenuName());
		inventoryOnHandField.setText(Objects.toString(product.getInventoryOnHand(), "0"));
		safetyInventoryOnHandField.setText(Objects.toString(product.getSafetyInventoryOnHand(), "0"));
		unitPriceField.setText(Objects.toString(product.getUnitPrice(), "0.00"));
		taxable1Checkbox.setSelected(product.getTaxable1() == null ? Boolean.FALSE : product.getTaxable1());
		taxable2Checkbox.setSelected(product.getTaxable2() == null ? Boolean.FALSE : product.getTaxable2());
		taxable3Checkbox.setSelected(product.getTaxable3() == null ? Boolean.FALSE : product.getTaxable3());
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
		product.setProductName(productNameField.getText());
		product.setProductMenuName(productMenuNameField.getText());
		product.setInventoryOnHand(Integer.valueOf(inventoryOnHandField.getText()));
		product.setSafetyInventoryOnHand(Integer.valueOf(safetyInventoryOnHandField.getText()));
		product.setUnitPrice(new BigDecimal(unitPriceField.getText()));
		product.setTaxable1(taxable1Checkbox.isSelected());
		product.setTaxable2(taxable2Checkbox.isSelected());
		product.setTaxable3(taxable3Checkbox.isSelected());

		productService.save(product);

		AppUtil.showPopupWindow("Product [" + product.getProductName() + "] has been saved", "");

		SellitApplication.getApplicationContainer().popCenterPaneStack();
	}

	@FXML
	private void close() {
		SellitApplication.getApplicationContainer().popCenterPaneStack();
	}

	private boolean validate() {

		if (ValidateUtil.invalidLength(barcodeField, 50)) {
			AppUtil.showPopupWindow("Barcode has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(productNameField, 50)) {
			AppUtil.showPopupWindow("Product name is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(productMenuNameField, 50)) {
			AppUtil.showPopupWindow("Product menu name is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.invalidInteger(inventoryOnHandField)) {
			AppUtil.showPopupWindow("Inventory on hand is not a valid number", "");
			return false;
		} else if (ValidateUtil.invalidInteger(safetyInventoryOnHandField)) {
			AppUtil.showPopupWindow("Safety inventory on hand is not a valid number", "");
			return false;
		} else if (ValidateUtil.invalidBigDecimal(unitPriceField)) {
			AppUtil.showPopupWindow("Unit price is not a valid decimal number", "");
			return false;
		} else if (departmentChoiceBox.getSelectionModel().getSelectedItem() == null) {
			AppUtil.showPopupWindow("Invalid department", "");
			return false;
		}

		return true;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
