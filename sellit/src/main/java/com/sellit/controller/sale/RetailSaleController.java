package com.sellit.controller.sale;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellit.container.PaneContainer;
import com.sellit.controller.Controller;
import com.sellit.enums.ConfigEnum;
import com.sellit.persistence.Product;
import com.sellit.persistence.Ticket;
import com.sellit.service.ConfigService;
import com.sellit.service.TicketService;
import com.sellit.util.AppUtil;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

@Component
public class RetailSaleController extends Controller implements InitializingBean {

	@Autowired
	private ConfigService configService;

	@Autowired
	private TicketService ticketService;

	@FXML
	private BorderPane borderPane;

	private TicketListController ticketListViewController;

	private TicketManageController ticketManageViewController;

	private Ticket currentTransaction;

	private Double tax1Rate;
	private Double tax2Rate;
	private Double tax3Rate;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public RetailSaleController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 *
	 * @throws IOException
	 */
	@FXML
	private void initialize() throws IOException {
		startNewTransaction();
	}

	private void showTicketListView() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/sale/TicketList.fxml", this);
		ticketListViewController = (TicketListController) paneContainer.getController();
		ticketListViewController.setTicket(currentTransaction);

		borderPane.setLeft(paneContainer.getPane());
	}

	private void showManageView() throws IOException {
		PaneContainer paneContainer = AppUtil.createPaneContainer("/com/sellit/controller/sale/TicketManage.fxml",
				this);
		ticketManageViewController = (TicketManageController) paneContainer.getController();
		ticketManageViewController.setParent(this);
		ticketManageViewController.setTicket(currentTransaction);

		borderPane.setRight(paneContainer.getPane());
	}

	public void addProduct(final Product product) {
		if (currentTransaction == null) {
			currentTransaction = new Ticket(tax1Rate, tax2Rate, tax3Rate);
		}

		currentTransaction.createTicketData(product);

		ticketListViewController.refresh();
	}

	public void startNewTransaction() throws IOException {
		currentTransaction = new Ticket(tax1Rate, tax2Rate, tax3Rate);

		showTicketListView();
		showManageView();
	}

	public void closeTransaction() throws IOException {
		ticketService.save(currentTransaction);

		startNewTransaction();
	}

	@Override
	public void refresh() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final String tax1RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX1_RATE.getCategory(), ConfigEnum.TAX1_RATE.getValue());

		if (tax1RateConfig != null) {
			tax1Rate = Double.valueOf(tax1RateConfig);
		}

		final String tax2RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX2_RATE.getCategory(), ConfigEnum.TAX2_RATE.getValue());

		if (tax2RateConfig != null) {
			tax2Rate = Double.valueOf(tax2RateConfig);
		}

		final String tax3RateConfig = configService
				.findByConfigCategoryAndConfigName(ConfigEnum.TAX3_RATE.getCategory(), ConfigEnum.TAX3_RATE.getValue());

		if (tax3RateConfig != null) {
			tax3Rate = Double.valueOf(tax3RateConfig);
		}
	}

}
