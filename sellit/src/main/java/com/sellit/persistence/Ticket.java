package com.sellit.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.BooleanUtils;

import com.sellit.util.DoubleUtil;

@Entity
@Table(name = "TICKET")
public class Ticket extends BaseEntity {

	public static final Double ZERO = 0.00;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private Long ticketId;

	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
	private Customer customer;

	@OneToMany(targetEntity = TicketData.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID")
	private final List<TicketData> ticketData = new ArrayList<>();

	@OneToMany(targetEntity = Payment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID")
	private final List<Payment> payments = new ArrayList<>();

	@Column(name = "TICKET_DTM")
	private LocalDateTime ticketDateTime;

	@Column(name = "QTY_TOTAL")
	private Integer quantityTotal;

	@Column(name = "SUBTOTAL_AMT")
	private Double subtotalAmount;

	@Column(name = "TAX1_RATE")
	private final Double tax1Rate;

	@Column(name = "TAX1_TOTAL_AMT")
	private Double tax1TotalAmount;

	@Column(name = "TAX2_RATE")
	private final Double tax2Rate;

	@Column(name = "TAX2_TOTAL_AMT")
	private Double tax2TotalAmount;

	@Column(name = "TAX3_RATE")
	private final Double tax3Rate;

	@Column(name = "TAX3_TOTAL_AMT")
	private Double tax3TotalAmount;

	@Column(name = "TOTAL_AMT")
	private Double totalAmount;

	public Ticket(final Double tax1Rate, final Double tax2Rate, final Double tax3Rate) {
		this.tax1Rate = tax1Rate;
		this.tax2Rate = tax2Rate;
		this.tax3Rate = tax3Rate;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(final Long ticketId) {
		this.ticketId = ticketId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public List<TicketData> getTicketData() {
		return ticketData;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public LocalDateTime getTicketDateTime() {
		return ticketDateTime;
	}

	public void setTicketDateTime(final LocalDateTime ticketDateTime) {
		this.ticketDateTime = ticketDateTime;
	}

	public Integer getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(final Integer quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Double getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(final Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public Double getTax1Rate() {
		return tax1Rate;
	}

	public Double getTax1TotalAmount() {
		return tax1TotalAmount;
	}

	public void setTax1TotalAmount(final Double tax1TotalAmount) {
		this.tax1TotalAmount = tax1TotalAmount;
	}

	public Double getTax2Rate() {
		return tax2Rate;
	}

	public Double getTax2TotalAmount() {
		return tax2TotalAmount;
	}

	public void setTax2TotalAmount(final Double tax2TotalAmount) {
		this.tax2TotalAmount = tax2TotalAmount;
	}

	public Double getTax3Rate() {
		return tax3Rate;
	}

	public Double getTax3TotalAmount() {
		return tax3TotalAmount;
	}

	public void setTax3TotalAmount(final Double tax3TotalAmount) {
		this.tax3TotalAmount = tax3TotalAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(final Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public TicketData createTicketData(final Product product) {
		final TicketData ticketData = new TicketData(this, product);
		this.ticketData.add(ticketData);

		return ticketData;
	}

	public void calculateTotal() {

		calculateQuantity();
		calculateSubtotal();
		calculateTax1();
		calculateTax2();
		calculateTax3();

		totalAmount = subtotalAmount + tax1TotalAmount + tax2TotalAmount + tax3TotalAmount;

	}

	private void calculateQuantity() {
		quantityTotal = ticketData.stream().mapToInt(TicketData::getQuantity).sum();
	}

	private void calculateSubtotal() {
		subtotalAmount = ticketData.stream().mapToDouble(TicketData::getTotalSalesAmount).sum();
	}

	private void calculateTax1() {

		if (tax1Rate == null) {
			tax1TotalAmount = ZERO;
			return;
		}

		final Double subtotalAmount = ticketData.stream().filter(t -> BooleanUtils.isTrue(t.getTaxable1Flag()))
				.mapToDouble(TicketData::getTotalSalesAmount).sum();
		tax1TotalAmount = DoubleUtil.truncate(subtotalAmount * tax1Rate);
	}

	private void calculateTax2() {

		if (tax2Rate == null) {
			tax2TotalAmount = ZERO;
			return;
		}

		final Double subtotalAmount = ticketData.stream().filter(t -> BooleanUtils.isTrue(t.getTaxable2Flag()))
				.mapToDouble(TicketData::getTotalSalesAmount).sum();
		tax2TotalAmount = DoubleUtil.truncate(subtotalAmount * tax2Rate);
	}

	private void calculateTax3() {

		if (tax3Rate == null) {
			tax3TotalAmount = ZERO;
			return;
		}

		final Double subtotalAmount = ticketData.stream().filter(t -> BooleanUtils.isTrue(t.getTaxable3Flag()))
				.mapToDouble(TicketData::getTotalSalesAmount).sum();
		tax3TotalAmount = DoubleUtil.truncate(subtotalAmount * tax3Rate);
	}

}
