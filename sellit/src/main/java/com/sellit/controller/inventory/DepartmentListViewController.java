package com.sellit.controller.inventory;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.constants.StatusConstants;
import com.sellit.controller.Controller;
import com.sellit.persistence.Department;
import com.sellit.persistence.Product;
import com.sellit.service.DepartmentService;
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
	private void searchDepartmentNameContaining() {
		List<Department> departments = departmentService.findByDepartmentNameContaining(departmentNameField.getText(),
				StatusConstants.ACTIVE);
		departmentTable.setItems(FXCollections.observableArrayList(departments));
		departmentTable.refresh();

		departmentNameField.requestFocus();
	}

	@FXML
	private void addNewDepartment() throws IOException {
		Department department = new Department();

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/inventory/DepartmentManageView.fxml");
		Pane pane = fxmlLoader.load();
		DepartmentManageViewController controller = fxmlLoader.getController();
		controller.setDepartment(department);

		AppUtil.pushCenterPaneStack(pane, this);
	}

	@FXML
	private void modifyDepartment() throws IOException {
		Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();

		if (selectedDepartment == null) {
			AppUtil.showPopupWindow("Select a product to modify", "");
			return;
		}

		FXMLLoader fxmlLoader = AppUtil.createFxmlLoader("/com/sellit/controller/inventory/DepartmentManageView.fxml");
		Pane pane = fxmlLoader.load();
		DepartmentManageViewController controller = fxmlLoader.getController();

		// fetch the record again to make sure it gets the latest version
		Department department = departmentService.findById(selectedDepartment.getDepartmentId());
		controller.setDepartment(department);

		AppUtil.pushCenterPaneStack(pane, this);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
