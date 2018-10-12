package com.sellit.controller.employee;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.EmployeeConstants;
import com.sellit.constants.StatusConstants;
import com.sellit.controller.Controller;
import com.sellit.persistence.Employee;
import com.sellit.service.EmployeeService;
import com.sellit.util.AppUtil;
import com.sellit.util.ValidateUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Component
public class EmployeeManageViewController extends Controller {

	@Autowired
	private EmployeeService employeeService;

	@FXML
	private TextField employeeNameField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField pinField;

	@FXML
	private ComboBox<String> employeeLevelCombo;

	private Employee employee;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public EmployeeManageViewController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		employeeLevelCombo.getItems().addAll(EmployeeConstants.EMPLOYEE_LEVELS);

		Platform.runLater(() -> employeeNameField.requestFocus());
	}

	public void updateFields() {
		employeeNameField.setText(employee.getEmployeeName());
		emailField.setText(employee.getEmailAddress());
		phoneField.setText(employee.getPhoneNumber());
		pinField.setText(employee.getPin());
		employeeLevelCombo.getSelectionModel().select(employee.getEmployeeLevel());
	}

	public void setEmployee(Employee employee) {

		this.employee = employee;

		updateFields();
	}

	@FXML
	private void save() throws IOException {

		if (validate() == false) {
			return;
		}

		employee.setEmployeeName(employeeNameField.getText());
		employee.setEmailAddress(emailField.getText());
		employee.setPhoneNumber(phoneField.getText());
		employee.setPin(pinField.getText());
		employee.setEmployeeLevel(employeeLevelCombo.getSelectionModel().getSelectedItem());
		employee.setStatus(StatusConstants.ACTIVE);

		employeeService.save(employee);

		AppUtil.showPopupWindow("Employee [" + employee.getEmployeeName() + "] has been saved", "");

		SellitApplication.getApplicationContainer().popCenterPaneStack();
	}

	private boolean validate() {

		if (ValidateUtil.blankTextOrInvalidLength(employeeNameField, 50)) {
			AppUtil.showPopupWindow("Employee name is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(emailField, 100)) {
			AppUtil.showPopupWindow("Email is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(phoneField, 50)) {
			AppUtil.showPopupWindow("Phone is empty or has too many characters", "");
			return false;
		} else if (ValidateUtil.blankTextOrInvalidLength(pinField, 20)) {
			AppUtil.showPopupWindow("PIN is empty or has too many characters", "");
			return false;
		} else if (employeeLevelCombo.getSelectionModel().getSelectedItem() == null) {
			AppUtil.showPopupWindow("Select Employee Level", "");
			return false;
		}

		return true;
	}

	@Override
	public void refresh() {

	}

}
