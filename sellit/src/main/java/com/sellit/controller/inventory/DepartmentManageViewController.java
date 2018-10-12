package com.sellit.controller.inventory;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.constants.StatusConstants;
import com.sellit.controller.Controller;
import com.sellit.persistence.Department;
import com.sellit.service.DepartmentService;
import com.sellit.util.AppUtil;
import com.sellit.util.ValidateUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class DepartmentManageViewController extends Controller {

	@Autowired
	private DepartmentService departmentService;

	@FXML
	private TextField departmentNameField;

	private Department department;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public DepartmentManageViewController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		Platform.runLater(() -> departmentNameField.requestFocus());
	}

	public void updateFields() {
		departmentNameField.setText(department.getDepartmentName());
	}

	public void setDepartment(Department department) {

		this.department = department;

		updateFields();
	}

	@FXML
	private void save() throws IOException {

		if (validate() == false) {
			return;
		}

		department.setDepartmentName(departmentNameField.getText());
		department.setStatus(StatusConstants.ACTIVE);

		departmentService.save(department);

		AppUtil.showPopupWindow("Department [" + department.getDepartmentName() + "] has been saved", "");

		SellitApplication.getApplicationContainer().popCenterPaneStack();
	}

	private boolean validate() {

		if (ValidateUtil.blankTextOrInvalidLength(departmentNameField, 50)) {
			AppUtil.showPopupWindow("Department name is empty or has too many characters", "");
			return false;
		}

		return true;
	}

	@Override
	public void refresh() {

	}
}
