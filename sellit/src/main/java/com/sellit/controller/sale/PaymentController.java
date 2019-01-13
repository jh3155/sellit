package com.sellit.controller.sale;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sellit.controller.Controller;
import com.sellit.persistence.Payment;
import com.sellit.persistence.Ticket;
import com.sellit.util.AppUtil;
import com.sellit.util.DoubleUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class PaymentController extends Controller {

	private static final String CASH = "CASH";
	// TODO refactor this with login view
	private static final String DOT = ".";
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	private static final String SIX = "6";
	private static final String SEVEN = "7";
	private static final String EIGHT = "8";
	private static final String NINE = "9";
	private static final String EMPTY_STRING = "";

	@FXML
	private Label ticketTotalLabel;

	@FXML
	private Label balanceDueLabel;

	@FXML
	private TextField tenderAmountField;

	private RetailSaleController parent;

	private Ticket ticket;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public PaymentController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	private void createPayment(String paymentMethod, Double paymentAmount) throws IOException {
		Double remainingBalanceDue = ticket.calcRemainingBalanceDue();

		Payment payment = new Payment();
		payment.setPaymentMethod(paymentMethod);

		if (paymentAmount > remainingBalanceDue) {
			payment.setPaymentAmount(remainingBalanceDue);
		} else {
			payment.setPaymentAmount(paymentAmount);
		}

		payment.setRemainingBalanceDueAmount(remainingBalanceDue);
		payment.setTenderAmount(paymentAmount);
		payment.setChangeAmount(DoubleUtil.truncate(payment.getTenderAmount() - remainingBalanceDue));

		payment.setTicket(ticket);

		ticket.getPayments().add(payment);

		refresh();

		if (payment.getChangeAmount() > 0 && CASH.equals(paymentMethod)) {
			AppUtil.showPopupWindow(String.format("Change is $%s", DoubleUtil.formatDouble(payment.getChangeAmount())));
		}

		if (ticket.calcRemainingBalanceDue() == 0) {
			parent.closeTransaction();
			dialogStage.close();
		}
	}

	@FXML
	private void payWithCash() throws IOException {

		String tenderAmountText = tenderAmountField.getText();
		Double tenderAmount = DoubleUtil.parseDouble(tenderAmountText);

		createPayment(CASH, tenderAmount);
	}

	@Override
	public void refresh() {
		ticketTotalLabel.setText(DoubleUtil.formatDouble(ticket.getTotalAmount()));
		balanceDueLabel.setText(DoubleUtil.formatDouble(ticket.calcRemainingBalanceDue()));
	}

	public void setParent(RetailSaleController parent) {
		this.parent = parent;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@FXML
	private void clear() throws IOException {
		tenderAmountField.setText(EMPTY_STRING);
	}

	@FXML
	private void appendText0() throws IOException {
		appendText(ZERO);
	}

	@FXML
	private void appendText1() throws IOException {
		appendText(ONE);
	}

	@FXML
	private void appendText2() throws IOException {
		appendText(TWO);
	}

	@FXML
	private void appendText3() throws IOException {
		appendText(THREE);
	}

	@FXML
	private void appendText4() throws IOException {
		appendText(FOUR);
	}

	@FXML
	private void appendText5() throws IOException {
		appendText(FIVE);
	}

	@FXML
	private void appendText6() throws IOException {
		appendText(SIX);
	}

	@FXML
	private void appendText7() throws IOException {
		appendText(SEVEN);
	}

	@FXML
	private void appendText8() throws IOException {
		appendText(EIGHT);
	}

	@FXML
	private void appendText9() throws IOException {
		appendText(NINE);
	}

	@FXML
	private void appendTextDot() throws IOException {
		appendText(DOT);
	}

	@FXML
	private void appendText5Dollars() throws IOException {
		setText("5.00");
	}

	@FXML
	private void appendText10Dollars() throws IOException {
		setText("10.00");
	}

	@FXML
	private void appendText20Dollars() throws IOException {
		setText("20.00");
	}

	@FXML
	private void appendText50Dollars() throws IOException {
		setText("50.00");
	}

	private void setText(String text) throws IOException {
		tenderAmountField.setText(text);
	}

	private void appendText(String text) throws IOException {

		if (DOT.equals(text) && tenderAmountField.getText().contains(DOT)) {
			return;
		}

		tenderAmountField.appendText(text);
	}

}
