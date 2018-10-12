package com.sellit.util;

import java.io.IOException;

import com.sellit.SellitApplication;
import com.sellit.container.ApplicationContainer;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.controller.LoginController;
import com.sellit.controller.PopupWindowController;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppUtil {

	public static FXMLLoader createFxmlLoader(String fxmlPath) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(SellitApplication.class.getResource(fxmlPath));
		fxmlLoader.setControllerFactory(SellitApplication.getSpringContext()::getBean);
		return fxmlLoader;
	}

	public static PaneContainer createPaneContainer(String fxmlPath, Controller parentController) throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader(fxmlPath);
		Pane pane = fxmlLoader.load();

		PaneContainer paneContainer = new PaneContainer();
		paneContainer.setPane(pane);
		paneContainer.setController(fxmlLoader.getController());
		paneContainer.setParentController(parentController);

		return paneContainer;
	}

	public static void showPopupWindow(String title) {
		showPopupWindow(title, "");
	}

	public static void showPopupWindow(String title, String message) {
		showPopupWindow(SellitApplication.getApplicationContainer().getPrimaryStage(), title, message);
	}

	public static void showPopupWindow(Stage ownerStage, String title, String message) {
		try {
			FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/PopupWindow.fxml");
			Pane pane = fxmlLoader.load();
			PopupWindowController controller = fxmlLoader.getController();
			controller.setText(title, message);

			// TODO: need to show in center
			Stage dialogStage = new Stage();
			dialogStage.initOwner(ownerStage);
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(ApplicationContainer.CSS_BOOTSTRAP3_CSS);
			dialogStage.setScene(scene);

			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			dialogStage.setX((screenBounds.getWidth() - 1920) / 2);
			dialogStage.setY((screenBounds.getHeight() - 300) / 2);

			dialogStage.initStyle(StageStyle.UNDECORATED);

			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showLoginWindow() {

		try {
			SellitApplication.getApplicationContainer().clearCenterPaneStack();
			SellitApplication.getApplicationContainer().setLoggedInEmployee(null);

			FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/LoginView.fxml");
			Pane pane = fxmlLoader.load();
			LoginController controller = fxmlLoader.getController();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(SellitApplication.getApplicationContainer().getPrimaryStage());
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(ApplicationContainer.CSS_BOOTSTRAP3_CSS);
			dialogStage.setScene(scene);

			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			dialogStage.setX((screenBounds.getWidth() - 1920) / 2);
			dialogStage.setY((screenBounds.getHeight() - 800) / 2);

			dialogStage.initStyle(StageStyle.UNDECORATED);

			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
