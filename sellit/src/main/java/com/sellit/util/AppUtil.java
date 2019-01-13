package com.sellit.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

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

	public static FXMLLoader createFxmlLoader(final String fxmlPath) {
		final FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(SellitApplication.class.getResource(fxmlPath));
		fxmlLoader.setControllerFactory(SellitApplication.getSpringContext()::getBean);
		return fxmlLoader;
	}

	public static PaneContainer createPaneContainer(final String fxmlPath, final Controller parentController)
			throws IOException {
		final FXMLLoader fxmlLoader = AppUtil.createFxmlLoader(fxmlPath);
		final Pane pane = fxmlLoader.load();

		final PaneContainer paneContainer = new PaneContainer();
		paneContainer.setPane(pane);
		paneContainer.setController(fxmlLoader.getController());
		paneContainer.setParentController(parentController);

		return paneContainer;
	}

	public static void showPopupWindow(final String title) {
		showPopupWindow(title, "");
	}

	public static void showPopupWindow(final String title, final String message) {
		showPopupWindow(SellitApplication.getApplicationContainer().getPrimaryStage(), title, message);
	}

	public static void showPopupWindow(final Stage ownerStage, final String title, final String message) {
		try {
			final FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/PopupWindow.fxml");
			final Pane pane = fxmlLoader.load();
			final PopupWindowController controller = fxmlLoader.getController();
			controller.setText(title, message);

			// TODO: need to show in center
			final Stage dialogStage = new Stage();
			dialogStage.initOwner(ownerStage);
			dialogStage.setTitle(StringUtils.EMPTY);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			final Scene scene = new Scene(pane);
			scene.getStylesheets().add(ApplicationContainer.CSS_BOOTSTRAP3_CSS);
			dialogStage.setScene(scene);

			// TODO this should come from config
			final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			dialogStage.setX((screenBounds.getWidth() - 1920) / 2);
			dialogStage.setY((screenBounds.getHeight() - 500) / 2);

			dialogStage.initStyle(StageStyle.UNDECORATED);

			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void showLoginWindow() {

		try {
			SellitApplication.getApplicationContainer().clearCenterPaneStack();
			SellitApplication.getApplicationContainer().setLoggedInEmployee(null);

			final FXMLLoader fxmlLoader = createFxmlLoader("/com/sellit/controller/LoginView.fxml");
			final Pane pane = fxmlLoader.load();
			final LoginController controller = fxmlLoader.getController();

			final Stage dialogStage = new Stage();
			dialogStage.initOwner(SellitApplication.getApplicationContainer().getPrimaryStage());
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			final Scene scene = new Scene(pane);
			scene.getStylesheets().add(ApplicationContainer.CSS_BOOTSTRAP3_CSS);
			dialogStage.setScene(scene);

			final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			dialogStage.setX((screenBounds.getWidth() - 1920) / 2);
			dialogStage.setY((screenBounds.getHeight() - 800) / 2);

			dialogStage.initStyle(StageStyle.UNDECORATED);

			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	public static void showDialogWindow(PaneContainer paneContainer) {
		Pane pane = paneContainer.getPane();
		Controller controller = paneContainer.getController();

		final Stage dialogStage = new Stage();
		dialogStage.initOwner(SellitApplication.getApplicationContainer().getPrimaryStage());
		dialogStage.setTitle("");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		final Scene scene = new Scene(pane);
		scene.getStylesheets().add(ApplicationContainer.CSS_BOOTSTRAP3_CSS);
		dialogStage.setScene(scene);

		final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		dialogStage.setX((screenBounds.getWidth() - 1920) / 2);
		dialogStage.setY((screenBounds.getHeight() - 1080) / 2);

		dialogStage.initStyle(StageStyle.UNDECORATED);

		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();

	}

}
