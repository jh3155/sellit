package com.sellit.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.util.AppUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

@Component
public class MenuController extends Controller {

	@FXML
	private Button btnLogo;

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

		// btnLogo.setStyle("-fx-background-image: url('../logo.png')");

		Image image = new Image(getClass().getResourceAsStream("/logo.png"));
		btnLogo.setGraphic(new ImageView(image));

		// BackgroundImage backgroundImage = new BackgroundImage(
		// new Image(getClass().getResource("/logo.png").toExternalForm()),
		// BackgroundRepeat.NO_REPEAT,
		// BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		// BackgroundSize.DEFAULT);
		// Background background = new Background(backgroundImage);
		//
		// btnLogo.setBackground(background);
	}

	@FXML
	private void showPrevious() throws IOException {
		AppUtil.popCenterPaneStack();
	}

	@FXML
	private void showHome() throws IOException {
		AppUtil.showLoginWindow();
	}

	@FXML
	private void showSale() throws IOException {
		// TODO:
	}

	@FXML
	private void showInventory() throws IOException {

		if (SellitApplication.getLoggedInEmployee() == null) {
			AppUtil.showLoginWindow();
			return;
		}

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/inventory/InventoryMainView.fxml");
		Pane productDashboard = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(productDashboard, this, true);
	}

	@FXML
	private void showEmployee() throws IOException {

		if (SellitApplication.getLoggedInEmployee() == null) {
			AppUtil.showLoginWindow();
			return;
		}

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/employee/EmployeeListView.fxml");
		Pane pane = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(pane, this, true);
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

	private void showDashboard() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/Dashboard.fxml");
		Pane pane = fxmlLoader.load();

		AppUtil.pushCenterPaneStack(pane, this, true);
	}

	@FXML
	private void exitProgram() {
		Platform.exit();
	}

	@Override
	public void refresh() {

	}
}
