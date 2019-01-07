package com.sellit.container;

import java.io.IOException;
import java.util.Stack;

import org.springframework.stereotype.Component;

import com.sellit.persistence.Employee;
import com.sellit.util.AppUtil;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@Component
public class ApplicationContainer {

	public static final String CSS_BOOTSTRAP3_CSS = "/css/bootstrap3.css";

	public static final int DEFAULT_SCREEN_WIDTH = 1366;
	public static final int DEFAULT_SCREEN_HEIGHT = 768;

	private Stage primaryStage;
	private BorderPane rootLayout;
	private final Stack<PaneContainer> centerPanes = new Stack<>();

	private Employee loggedInEmployee;

	public void initialize() throws IOException {

		initializeRootLayout();

		showMenu();

		AppUtil.showLoginWindow();

	}

	/**
	 * Initializes the root layout.
	 *
	 * @throws IOException
	 */
	public void initializeRootLayout() throws IOException {
		final PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/RootLayout.fxml", null);
		rootLayout = (BorderPane) paneContainer.getPane();

		// Show the scene containing the root layout.
		final Scene scene = new Scene(rootLayout);
		scene.getStylesheets().add(CSS_BOOTSTRAP3_CSS);

		primaryStage.setX(0);
		primaryStage.setY(0);

		// Hide the window bar
		primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.setScene(scene);

		primaryStage.show();

		// use VisualBounds to show taskbar
		final Rectangle2D screenBounds = Screen.getPrimary().getBounds();

		final double widthScaleFactor = screenBounds.getWidth() / DEFAULT_SCREEN_WIDTH;
		final double heightScaleFactor = screenBounds.getHeight() / DEFAULT_SCREEN_HEIGHT;
		final Scale scale = new Scale(widthScaleFactor, heightScaleFactor);
		scale.setPivotX(0);
		scale.setPivotY(0);
		rootLayout.getTransforms().setAll(scale);
		primaryStage.setWidth(screenBounds.getWidth());
		primaryStage.setHeight(screenBounds.getHeight());
	}

	public void showHeader() throws IOException {
		final PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Header.fxml", null);
		rootLayout.setTop(paneContainer.getPane());
	}

	public void showMenu() throws IOException {
		final PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Menu.fxml", null);
		rootLayout.setLeft(paneContainer.getPane());
	}

	public void showFooter() throws IOException {
		final PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Footer.fxml", null);
		rootLayout.setBottom(paneContainer.getPane());
	}

	public void pushCenterPaneStack(final PaneContainer paneContainer) {
		final Pane pane = centerPanes.push(paneContainer).getPane();
		rootLayout.setCenter(pane);
	}

	public void pushCenterPaneStack(final PaneContainer paneContainer, final boolean clearStackBeforePush) {
		if (clearStackBeforePush) {
			clearCenterPaneStack();
		}
		pushCenterPaneStack(paneContainer);
	}

	public void popCenterPaneStack() {

		if (centerPanes.size() == 1) {
			return;
		}

		final PaneContainer paneContainer = centerPanes.pop();
		if (paneContainer.getParentController() != null) {
			paneContainer.getParentController().refresh();
		}

		final PaneContainer prevPaneContainer = centerPanes.peek();
		final Pane previousPane = prevPaneContainer.getPane();
		rootLayout.setCenter(previousPane);
	}

	public void clearCenterPaneStack() {
		centerPanes.clear();
		rootLayout.setCenter(null);
	}

	// Setters ------------------------------------------------------------

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(final Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stack<PaneContainer> getCenterPanes() {
		return centerPanes;
	}

	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	public void setLoggedInEmployee(final Employee loggedInEmployee) {
		this.loggedInEmployee = loggedInEmployee;
	}

}
