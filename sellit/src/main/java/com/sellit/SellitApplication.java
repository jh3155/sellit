package com.sellit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sellit.dao.ProductDao;
import com.sellit.persistence.Product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SpringBootApplication
public class SellitApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent root;

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(SellitApplication.class);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		window.setTitle("Title!");

		CheckBox box1 = new CheckBox("Bacon");
		CheckBox box2 = new CheckBox("Tuna");
		box2.setSelected(true);

		Button button = new Button("Order now!");

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(box1, box2, button);

		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);

		window.show();
	}
}
