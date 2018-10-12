package com.sellit.controller.employee;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.StatusConstants;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.controller.inventory.DepartmentManageViewController;
import com.sellit.persistence.Department;
import com.sellit.persistence.Employee;
import com.sellit.service.EmployeeService;
import com.sellit.util.AppUtil;

import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

@Component
public class EmployeeListViewController extends Controller {

	@Autowired
	private EmployeeService employeeService;

	@FXML
	private TableView<Employee> employeeTable;

	@FXML
	private TableColumn<Employee, Long> employeeIdColumn;

	@FXML
	private TableColumn<Employee, String> employeeNameColumn;

	@FXML
	private TableColumn<Employee, String> employeeLevelColumn;

	@FXML
	private TableColumn<Employee, String> employeeEmailColumn;

	@FXML
	private TableColumn<Employee, String> employeePhoneColumn;

	@FXML
	private TextField employeeNameField;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public EmployeeListViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 * 
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {

		employeeIdColumn.setCellValueFactory(
				cellData -> new SimpleLongProperty(cellData.getValue().getEmployeeId()).asObject());
		employeeNameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeName()));
		employeeLevelColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeLevel()));
		employeeEmailColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmailAddress()));
		employeePhoneColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));

		employeeTable.setItems(null);

		Platform.runLater(() -> employeeNameField.requestFocus());

	}

	@FXML
	private void search() {
		List<Employee> employees = employeeService.findByEmployeeNameContaining(employeeNameField.getText(),
				StatusConstants.ACTIVE);
		employeeTable.setItems(FXCollections.observableArrayList(employees));
		employeeTable.refresh();

		employeeNameField.requestFocus();
	}

	@FXML
	private void addNewEmployee() throws IOException {
		Employee employee = new Employee();

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/employee/EmployeeManageView.fxml", this);
		((EmployeeManageViewController) paneContainer.getController()).setEmployee(employee);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@FXML
	private void modifyEmployee() throws IOException {
		Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

		if (selectedEmployee == null) {
			AppUtil.showPopupWindow("Select an employee to modify", "");
			return;
		}

		Employee employee = employeeService.findById(selectedEmployee.getEmployeeId());

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/employee/EmployeeManageView.fxml", this);
		((EmployeeManageViewController) paneContainer.getController()).setEmployee(employee);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@Override
	public void refresh() {
		search();
	}

}
