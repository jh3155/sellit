package com.sellit.controller;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Component
public class PopupWindowController {

	@FXML
	private Label titleLabel;

	@FXML
	private Label messageLabel;

	private Stage dialogStage;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public PopupWindowController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	public void setText(String title, String message) {
		titleLabel.setText(title);
		messageLabel.setText(message);
	}

	@FXML
	private void close() {
		dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
