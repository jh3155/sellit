package com.sellit.controller.inventory;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.SellitApplication;
import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.util.AppUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

@Component
public class InventoryMainViewController extends Controller {

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public InventoryMainViewController() {
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
	private void showDepartmentList() throws IOException {
		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/DepartmentListView.fxml", this);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@FXML
	private void showProductList() throws IOException {
		PaneContainer paneContainer = AppUtil
				.createPaneContainer("/com/sellit/controller/inventory/ProductListView.fxml", this);
		SellitApplication.getApplicationContainer().pushCenterPaneStack(paneContainer);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
