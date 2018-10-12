package com.sellit.container;

import com.sellit.controller.Controller;

import javafx.scene.layout.Pane;

public class PaneContainer {

	private Pane pane;

	private Controller controller;

	private Controller parentController;

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Controller getParentController() {
		return parentController;
	}

	public void setParentController(Controller controller) {
		this.parentController = controller;
	}

}
