package com.sellit.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.persistence.Product;
import com.sellit.persistence.ProductImpl;
import com.sellit.service.ProductService;
import com.sellit.util.AppUtil;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

@Component
public class ProductListViewController {

	@Autowired
	private ProductService productService;

	@FXML
	private TableView<Product> productTable;

	@FXML
	private TableColumn<Product, Long> productIdColumn;

	@FXML
	private TableColumn<Product, String> barcodeColumn;

	@FXML
	private TableColumn<Product, String> productNameColumn;

	@FXML
	private TableColumn<Product, Integer> onHandColumn;

	@FXML
	private TableColumn<Product, BigDecimal> costAmountColumn;

	@FXML
	private TableColumn<Product, BigDecimal> salesAmountColumn;

	@FXML
	private TextField barcodeField;

	@FXML
	private TextField productNameField;

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

		productIdColumn
				.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getProductId()).asObject());
		barcodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBarcode()));
		productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
		onHandColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getInventoryOnHand()).asObject());
		costAmountColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(cellData.getValue().getCostAmount()));
		salesAmountColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(cellData.getValue().getSalesAmount()));

		productTable.setItems(null);
	}

	@FXML
	private void searchProductNameContaining() {
		List<Product> products = productService.findByFullNameContaining(productNameField.getText());
		productTable.setItems(FXCollections.observableArrayList(products));
	}

	@FXML
	private void searchBarcode() {
		List<Product> products = productService.findByBarcode(barcodeField.getText());
		productTable.setItems(FXCollections.observableArrayList(products));
	}

	@FXML
	private void addNewProduct() throws IOException {
		Product product = new ProductImpl();

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/ProductManageView.fxml");
		Pane pane = fxmlLoader.load();
		ProductManageViewController controller = fxmlLoader.getController();
		controller.setProduct(product);

		AppUtil.pushCenterPaneStack(pane);
	}

	@FXML
	private void modifyProduct() throws IOException {
		Product product = productTable.getSelectionModel().getSelectedItem();

		if (product == null) {
			AppUtil.showPopupWindow("Select a product to modify", "");
			return;
		}

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/ProductManageView.fxml");
		Pane pane = fxmlLoader.load();
		ProductManageViewController controller = fxmlLoader.getController();
		controller.setProduct(product);

		AppUtil.pushCenterPaneStack(pane);
	}

}
