package com.sellit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sellit.container.ApplicationContainer;

import javafx.application.Application;
import javafx.stage.Stage;

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
