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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@Component
public class ApplicationContainer {

	public static final String CSS_BOOTSTRAP3_CSS = "/css/bootstrap3.css";

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Stack<PaneContainer> centerPanes = new Stack<>();

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
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/RootLayout.fxml", null);
		rootLayout = (BorderPane) paneContainer.getPane();

		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		scene.getStylesheets().add(CSS_BOOTSTRAP3_CSS);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((screenBounds.getWidth() - 1920) / 2);
		primaryStage.setY((screenBounds.getHeight() - 1080) / 2);

		// Hide the window bar
		primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public void showHeader() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Header.fxml", null);
		rootLayout.setTop(paneContainer.getPane());
	}

	public void showMenu() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Menu.fxml", null);
		rootLayout.setLeft(paneContainer.getPane());
	}

	public void showFooter() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Footer.fxml", null);
		rootLayout.setBottom(paneContainer.getPane());
	}

	public void pushCenterPaneStack(PaneContainer paneContainer) {
		Pane pane = centerPanes.push(paneContainer).getPane();
		rootLayout.setCenter(pane);
	}

	public void pushCenterPaneStack(PaneContainer paneContainer, boolean clearStackBeforePush) {
		if (clearStackBeforePush) {
			clearCenterPaneStack();
		}
		pushCenterPaneStack(paneContainer);
	}

	public void popCenterPaneStack() {

		if (centerPanes.size() == 1) {
			return;
		}

		PaneContainer paneContainer = centerPanes.pop();
		if (paneContainer.getParentController() != null) {
			paneContainer.getParentController().refresh();
		}

		PaneContainer prevPaneContainer = centerPanes.peek();
		Pane previousPane = prevPaneContainer.getPane();
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

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stack<PaneContainer> getCenterPanes() {
		return centerPanes;
	}

	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	public void setLoggedInEmployee(Employee loggedInEmployee) {
		this.loggedInEmployee = loggedInEmployee;
	}

}
