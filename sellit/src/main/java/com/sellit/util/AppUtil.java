package com.sellit.util;

import java.io.IOException;

import com.sellit.SellitApplication;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
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

	public static void pushCenterPaneStack(Pane pane, Controller controller) {
		PaneContainer paneContainer = new PaneContainer();
		paneContainer.setPane(pane);
		paneContainer.setParentController(controller);
		pushCenterPaneStack(paneContainer);
	}

	public static void pushCenterPaneStack(PaneContainer paneContainer) {
		Pane pane = SellitApplication.getCenterPanes().push(paneContainer).getPane();
		SellitApplication.getRootLayout().setCenter(pane);
	}

	public static void pushCenterPaneStack(Pane pane, Controller controller, boolean clearStackBeforePush) {
		if (clearStackBeforePush) {
			SellitApplication.getCenterPanes().clear();
		}
		pushCenterPaneStack(pane, controller);
	}

	public static void popCenterPaneStack() {

		if (SellitApplication.getCenterPanes().size() == 1) {
			return;
		}

		PaneContainer paneContainer = SellitApplication.getCenterPanes().pop();
		paneContainer.getParentController().refresh();

		PaneContainer prevPaneContainer = SellitApplication.getCenterPanes().peek();
		Pane previousPane = prevPaneContainer.getPane();
		SellitApplication.getRootLayout().setCenter(previousPane);
	}

	public static void showPopupWindow(String title, String message) {

		try {
			FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/PopupWindow.fxml");
			Pane pane = fxmlLoader.load();
			PopupWindowController controller = fxmlLoader.getController();
			controller.setText(title, message);

			// TODO: need to show in center
			Stage dialogStage = new Stage();
			dialogStage.initOwner(SellitApplication.getPrimaryStage());
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/css/bootstrap3.css");
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

}
