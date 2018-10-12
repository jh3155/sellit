package com.sellit.controller.inventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.InventoryConstants;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.persistence.Product;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Component
public class ProductListViewController extends Controller {

	@Autowired
	private ProductService productService;

	@FXML
	private ComboBox<String> searchCombo;

	@FXML
	private TableView<Product> productTable;

	@FXML
	private TableColumn<Product, Long> productIdColumn;

	@FXML
	private TableColumn<Product, String> barcodeColumn;

	@FXML
	private TableColumn<Product, String> departmentColumn;

	@FXML
	private TableColumn<Product, String> productNameColumn;

	@FXML
	private TableColumn<Product, Integer> onHandColumn;

	@FXML
	private TableColumn<Product, BigDecimal> costAmountColumn;

	@FXML
	private TableColumn<Product, BigDecimal> salesAmountColumn;

	@FXML
	private TextField searchField;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public ProductListViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		searchCombo.getItems().addAll(InventoryConstants.SEARCH_METHOD);
		searchCombo.getSelectionModel().select(InventoryConstants.PRODUCT_NAME);

		productIdColumn
				.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getProductId()).asObject());
		departmentColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getDepartment().getDepartmentName()));
		barcodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBarcode()));
		productNameColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getProductNameInEnglish()));
		onHandColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getInventoryOnHand()).asObject());
		costAmountColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(cellData.getValue().getCostAmount()));
		salesAmountColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(cellData.getValue().getSalesAmount()));

		productTable.setItems(null);
	}

	@FXML
	private void search() {

		String method = searchCombo.getSelectionModel().getSelectedItem();

		if (InventoryConstants.BARCODE.equals(method)) {
			searchBarcode();
		} else if (InventoryConstants.PRODUCT_NAME.equals(method)) {
			searchProductName();
		}

	}

	@FXML
	private void searchProductName() {

		List<Product> products = productService.findByProductNameInEnglishContaining(searchField.getText());
		productTable.setItems(FXCollections.observableArrayList(products));

		searchField.requestFocus();
	}

	@FXML
	private void searchBarcode() {

		List<Product> products = productService.findByBarcode(searchField.getText());
		productTable.setItems(FXCollections.observableArrayList(products));

		searchField.requestFocus();
	}

	@FXML
	private void addNewProduct() throws IOException {
		Product product = new Product();

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/ProductManageView.fxml", this);
		((ProductManageViewController) paneContainer.getController()).setProduct(product);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@FXML
	private void modifyProduct() throws IOException {
		Product product = productTable.getSelectionModel().getSelectedItem();

		if (product == null) {
			AppUtil.showPopupWindow("Select a product to modify", "");
			return;
		}

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/ProductManageView.fxml", this);
		((ProductManageViewController) paneContainer.getController()).setProduct(product);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@Override
	public void refresh() {
		search();
	}

}
