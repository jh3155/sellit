package com.sellit.controller;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
public class FooterController {

	@FXML
	private Label homeLabel;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public FooterController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

}
