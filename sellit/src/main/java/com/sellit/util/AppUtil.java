package com.sellit.util;

import java.io.IOException;

import com.sellit.SellitApplication;
import com.sellit.controller.PopupWindowController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppUtil {

	public static FXMLLoader createFxmlLoader(String fxmlPath) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(SellitApplication.class.getResource(fxmlPath));
		fxmlLoader.setControllerFactory(SellitApplication.getSpringContext()::getBean);
		return fxmlLoader;
	}

	public static void pushCenterPaneStack(Pane pane) {
		SellitApplication.getRootLayout().setCenter(SellitApplication.getCenterPanes().push(pane));
	}

	public static void pushCenterPaneStack(Pane pane, boolean clearStackBeforePush) {
		if (clearStackBeforePush) {
			SellitApplication.getCenterPanes().clear();
		}
		SellitApplication.getRootLayout().setCenter(SellitApplication.getCenterPanes().push(pane));
	}

	public static void popCenterPaneStack() {

		if (SellitApplication.getCenterPanes().size() == 1) {
			return;
		}

		SellitApplication.getCenterPanes().pop();
		Pane previousPane = SellitApplication.getCenterPanes().peek();
		SellitApplication.getRootLayout().setCenter(previousPane);
	}

	public static void showPopupWindow(String title, String message) throws IOException {

		FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/PopupWindow.fxml");
		Pane pane = fxmlLoader.load();
		PopupWindowController controller = fxmlLoader.getController();
		controller.setText(title, message);

		// TODO: need to show in center
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Person");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();

	}

}
