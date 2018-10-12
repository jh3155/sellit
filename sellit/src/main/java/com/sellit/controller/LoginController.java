package com.sellit.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.container.PaneContainer;
import com.sellit.persistence.Employee;
import com.sellit.service.EmployeeService;
import com.sellit.util.AppUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class LoginController extends Controller {

	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	private static final String SIX = "6";
	private static final String SEVEN = "7";
	private static final String EIGHT = "8";
	private static final String NINE = "9";
	private static final String EMPTY_STRING = "";

	@Autowired
	private EmployeeService employeeService;

	@FXML
	private TextField pinField;

	private Stage dialogStage;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public LoginController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 *
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {
		Platform.runLater(() -> pinField.requestFocus());
	}

	@FXML
	private void clearPin() throws IOException {
		pinField.setText(EMPTY_STRING);
		pinField.requestFocus();
	}

	@FXML
	private void appendPinText0() throws IOException {
		appendPinText(ZERO);
	}

	@FXML
	private void appendPinText1() throws IOException {
		appendPinText(ONE);
	}

	@FXML
	private void appendPinText2() throws IOException {
		appendPinText(TWO);
	}

	@FXML
	private void appendPinText3() throws IOException {
		appendPinText(THREE);
	}

	@FXML
	private void appendPinText4() throws IOException {
		appendPinText(FOUR);
	}

	@FXML
	private void appendPinText5() throws IOException {
		appendPinText(FIVE);
	}

	@FXML
	private void appendPinText6() throws IOException {
		appendPinText(SIX);
	}

	@FXML
	private void appendPinText7() throws IOException {
		appendPinText(SEVEN);
	}

	@FXML
	private void appendPinText8() throws IOException {
		appendPinText(EIGHT);
	}

	@FXML
	private void appendPinText9() throws IOException {
		appendPinText(NINE);
	}

	private void appendPinText(String text) throws IOException {
		pinField.appendText(text);
		pinField.requestFocus();
	}

	@FXML
	private void login() throws IOException {

		if (StringUtils.isBlank(pinField.getText())) {
			clearPin();
			AppUtil.showPopupWindow(dialogStage, "Enter PIN", EMPTY_STRING);
			return;
		}

		Employee employee = employeeService.findByPin(pinField.getText());

		if (employee == null) {
			clearPin();
			AppUtil.showPopupWindow(dialogStage, "Invalid PIN", EMPTY_STRING);
			return;
		}

		SellitApplication.getApplicationContainer().setLoggedInEmployee(employee);

		showDashboard();

		dialogStage.close();

	}

	private void showDashboard() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/Dashboard.fxml", this);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@Override
	public void refresh() {

	}
}
