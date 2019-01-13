package com.sellit.controller;

import javafx.stage.Stage;

public abstract class Controller {

	public Stage dialogStage;

	public abstract void refresh();

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
