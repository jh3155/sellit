package com.sellit;

import java.io.IOException;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sellit.container.ApplicationContainer;
import com.sellit.container.PaneContainer;
import com.sellit.persistence.Employee;
import com.sellit.service.EmployeeService;
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

	private static ApplicationContainer applicationContainer;

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/***
	 * The application initialization method. This method is called immediately
	 * after the Application class is loaded and constructed. An application may
	 * override this method to perform initialization prior to the actual starting
	 * of the application.
	 */
	@Override
	public void init() throws Exception {
		// spring initialization
		springContext = SpringApplication.run(SellitApplication.class);
	}

	/***
	 * The main entry point for all JavaFX applications. The start method is called
	 * after the init method has returned, and after the system is ready for the
	 * application to begin running.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Sellit");

		applicationContainer = springContext.getBean(ApplicationContainer.class);

		applicationContainer.setPrimaryStage(primaryStage);

		applicationContainer.initialize();

	}

	public static ConfigurableApplicationContext getSpringContext() {
		return springContext;
	}

	public static ApplicationContainer getApplicationContainer() {
		return applicationContainer;
	}

}
