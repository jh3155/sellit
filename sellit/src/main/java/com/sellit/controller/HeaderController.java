package com.sellit.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.util.AppUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@Component
public class HeaderController {

	@FXML
	private Label homeLabel;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public HeaderController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	@FXML
	private void showHome() throws IOException {
		// TODO:
	}

	@FXML
	private void showPrevious() throws IOException {
	}

}
