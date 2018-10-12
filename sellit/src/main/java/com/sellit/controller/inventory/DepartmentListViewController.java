package com.sellit.controller.inventory;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.StatusConstants;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.persistence.Department;
import com.sellit.service.DepartmentService;
import com.sellit.util.AppUtil;

import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Component
public class DepartmentListViewController extends Controller {

	@Autowired
	private DepartmentService departmentService;

	@FXML
	private TableView<Department> departmentTable;

	@FXML
	private TableColumn<Department, Long> departmentIdColumn;

	@FXML
	private TableColumn<Department, String> departmentNameColumn;

	@FXML
	private TextField departmentNameField;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public DepartmentListViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		departmentIdColumn.setCellValueFactory(
				cellData -> new SimpleLongProperty(cellData.getValue().getDepartmentId()).asObject());
		departmentNameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartmentName()));

		departmentTable.setItems(null);

		Platform.runLater(() -> departmentNameField.requestFocus());
	}

	@FXML
	private void search() {
		List<Department> departments = departmentService.findByDepartmentNameContaining(departmentNameField.getText(),
				StatusConstants.ACTIVE);
		departmentTable.setItems(FXCollections.observableArrayList(departments));
		departmentTable.refresh();

		departmentNameField.requestFocus();
	}

	@FXML
	private void addNewDepartment() throws IOException {
		Department department = new Department();

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/DepartmentManageView.fxml", this);
		((DepartmentManageViewController) paneContainer.getController()).setDepartment(department);
		paneContainer.setParentController(this);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@FXML
	private void modifyDepartment() throws IOException {
		Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();

		if (selectedDepartment == null) {
			AppUtil.showPopupWindow("Select a department to modify", "");
			return;
		}

		// fetch the record again to make sure it gets the latest version
		Department department = departmentService.findById(selectedDepartment.getDepartmentId());

		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/DepartmentManageView.fxml", this);
		((DepartmentManageViewController) paneContainer.getController()).setDepartment(department);
		paneContainer.setParentController(this);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@Override
	public void refresh() {
		search();
	}

}
