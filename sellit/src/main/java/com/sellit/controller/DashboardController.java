package com.sellit.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.util.AppUtil;

import javafx.fxml.FXML;

@Component
public class DashboardController extends Controller {

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public DashboardController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 *
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {

	}

	@FXML
	private void logout() throws IOException {
		AppUtil.showLoginWindow();
	}

	@Override
	public void refresh() {

	}

}
