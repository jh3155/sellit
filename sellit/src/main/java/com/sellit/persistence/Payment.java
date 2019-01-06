package com.sellit.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_ID")
	private Long paymentId;

	@ManyToOne(targetEntity = Ticket.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID")
	private Ticket ticket;

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "PAYMENT_AMT")
	private Double paymentAmount;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(final Long paymentId) {
		this.paymentId = paymentId;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(final Ticket ticket) {
		this.ticket = ticket;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(final String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(final Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
