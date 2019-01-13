package com.sellit.controller.sale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sellit.constants.SaleConstants;
import com.sellit.controller.Controller;
import com.sellit.persistence.Ticket;
import com.sellit.persistence.TicketData;
import com.sellit.util.DoubleUtil;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
public class TicketListController extends Controller {

	@FXML
	private TableView<TicketData> ticketDataTable;

	@FXML
	private TableColumn<TicketData, String> productNameColumn;

	@FXML
	private TableColumn<TicketData, Integer> ticketDataQtyColumn;

	@FXML
	private TableColumn<TicketData, String> ticketDataUnitPriceColumn;

	@FXML
	private TableColumn<TicketData, String> ticketDataTotalColumn;

	@FXML
	private Label subtotalLabel;

	@FXML
	private Label taxLabel;

	@FXML
	private Label totalLabel;

	private Ticket ticket;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public TicketListController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		productNameColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getProductName()));
		ticketDataQtyColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
		ticketDataUnitPriceColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(DoubleUtil.formatDouble(cellData.getValue().getUnitPrice())));
		ticketDataTotalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				DoubleUtil.formatDouble(cellData.getValue().getTotalSalesAmount())));

		ticketDataTable.setItems(null);

		// change row height by code
		// ticketDataTable.setFixedCellSize(50);
		ticketDataTable.getStyleClass().add("sellit-ticket-table");

		ticketDataQtyColumn.getStyleClass().add(SaleConstants.SELLIT_TEXT_ALIGN_CENTER);
		ticketDataUnitPriceColumn.getStyleClass().add(SaleConstants.SELLIT_TEXT_ALIGN_CENTER_RIGHT);
		ticketDataTotalColumn.getStyleClass().add(SaleConstants.SELLIT_TEXT_ALIGN_CENTER_RIGHT);

		ticketDataTable.setPlaceholder(new Label(StringUtils.EMPTY));

	}

	@Override
	public void refresh() {
		ticketDataTable.setItems(FXCollections.observableArrayList(ticket.getTicketDataList()));
		ticketDataTable.refresh();

		ticketDataTable.getSelectionModel().selectLast();
		ticketDataTable.scrollTo(ticketDataTable.getItems().size());

		subtotalLabel.setText("$ " + DoubleUtil.formatDouble(ticket.getSubtotalAmount()));
		taxLabel.setText("$ " + DoubleUtil.formatDouble(ticket.getTaxTotalAmount()));
		totalLabel.setText("$ " + DoubleUtil.formatDouble(ticket.getTotalAmount()));
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
