package com.sellit.controller;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@Component
public class MenuController {

	@FXML
	private TreeView<String> menuTree;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public MenuController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		TreeItem<String> rootNode = new TreeItem<String>("Sellit");

		TreeItem<String> dashboard = new TreeItem<String>("Dashboard");
		TreeItem<String> product = new TreeItem<String>("Product");

		rootNode.getChildren().add(dashboard);
		rootNode.getChildren().add(product);

		menuTree.setRoot(rootNode);
	}

}
