package com.sellit.controller.inventory;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.util.AppUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

@Component
public class InventoryMainViewController {

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public InventoryMainViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 * 
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {

	}

	@FXML
	private void showDepartmentList() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/inventory/DepartmentListView.fxml");
		Pane pane = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(pane);
	}

	@FXML
	private void showProductList() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/inventory/ProductListView.fxml");
		Pane pane = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(pane);
	}

}
