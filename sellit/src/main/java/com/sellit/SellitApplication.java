package com.sellit;

import java.io.IOException;
import java.util.Stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sellit.container.PaneContainer;
import com.sellit.persistence.Employee;
import com.sellit.util.AppUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SpringBootApplication
public class SellitApplication extends Application {

	private static ConfigurableApplicationContext springContext;
	private Parent root;
	private static Stage primaryStage;
	private static BorderPane rootLayout;
	private static Stack<PaneContainer> centerPanes;

	private static Employee loggedInEmployee;

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(SellitApplication.class);
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		SellitApplication.primaryStage = primaryStage;
		SellitApplication.primaryStage.setTitle("Sellit");

		initializeRootLayout();

		// showHeader();
		showMenu();
		// showFooter();
		
		AppUtil.showLoginWindow();

	}

	public static ConfigurableApplicationContext getSpringContext() {
		return springContext;
	}

	public static BorderPane getRootLayout() {
		return rootLayout;
	}

	public static Stack<PaneContainer> getCenterPanes() {
		return centerPanes;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	public static void setLoggedInEmployee(Employee loggedInEmployee) {
		SellitApplication.loggedInEmployee = loggedInEmployee;
	}

	/**
	 * Initializes the root layout.
	 * 
	 * @throws IOException
	 */
	public void initializeRootLayout() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("RootLayout.fxml");
		rootLayout = (BorderPane) fxmlLoader.load();

		centerPanes = new Stack<>();

		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		scene.getStylesheets().add("/css/bootstrap3.css");

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((screenBounds.getWidth() - 1920) / 2);
		primaryStage.setY((screenBounds.getHeight() - 1080) / 2);

		primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public void showHeader() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/Header.fxml");
		Pane pane = fxmlLoader.load();

		rootLayout.setTop(pane);
	}

	public void showMenu() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/Menu.fxml");
		Pane pane = fxmlLoader.load();

		rootLayout.setLeft(pane);
	}

	public void showFooter() throws IOException {
		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/Footer.fxml");
		Pane pane = fxmlLoader.load();

		rootLayout.setBottom(pane);
	}

}
