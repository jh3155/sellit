package com.sellit.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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
@Table(name = "TICKET")
public class Ticket extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private Long ticketId;

	@ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
	private Department customer;

	@Column(name = "TICKET_DTM")
	private LocalDateTime ticketDateTime;

	@Column(name = "QTY_TOTAL")
	private Integer quantityTotal;

	@Column(name = "SUBTOTAL_AMT")
	private Double subtotalAmount;

	@Column(name = "TAX1_RATE")
	private Double tax1Rate;

	@Column(name = "TAX1_TOTAL_AMT")
	private Double tax1Amount;

	@Column(name = "TAX2_RATE")
	private Double tax2Rate;

	@Column(name = "TAX2_TOTAL_AMT")
	private Double tax2Amount;

	@Column(name = "TAX3_RATE")
	private Double tax3Rate;

	@Column(name = "TAX3_TOTAL_AMT")
	private Double tax3Amount;

	@Column(name = "TOTAL_AMT")
	private Double totalAmount;

}
