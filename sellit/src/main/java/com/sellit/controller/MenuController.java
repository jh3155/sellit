package com.sellit.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.util.AppUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

@Component
public class MenuController {

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public MenuController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 * 
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {
		AppUtil.showDashboard();
	}

	@FXML
	private void showSale() throws IOException {
		// TODO:
	}

	@FXML
	private void showProduct() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/ProductMainView.fxml");
		Pane productDashboard = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(productDashboard, true);
	}

	@FXML
	private void showInventory() throws IOException {
		// TODO:
	}

	@FXML
	private void showEmployee() throws IOException {
		// TODO:
	}

	@FXML
	private void showReport() throws IOException {
		// TODO:
	}

	@FXML
	private void showOptions() throws IOException {
		// TODO:
	}

	@FXML
	private void showNotice() throws IOException {
		// TODO:
	}

}
