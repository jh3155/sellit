package com.sellit.persistence;

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

import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "TICKET_DATA")
public class TicketData extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_DATA_ID")
	private Long ticketDataId;

	@ManyToOne(targetEntity = Ticket.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID")
	private Ticket ticket;

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
	private Product product;

	@Column(name = "QTY")
	private Integer quantity;

	@Column(name = "UNIT_PRICE")
	private Double unitPrice;

	@Column(name = "TOTAL_SALES_AMT")
	private Double totalSalesAmount;

	@Type(type="yes_no")
	@Column(name = "TAXABLE_1_FLG")
	private Boolean taxable1Flag;

	@Type(type="yes_no")
	@Column(name = "TAXABLE_2_FLG")
	private Boolean taxable2Flag;

	@Type(type="yes_no")
	@Column(name = "TAXABLE_3_FLG")
	private Boolean taxable3Flag;

	public TicketData(Ticket ticket, Product product) {

		Validate.notNull(ticket, "ticket cannot be null!");
		this.ticket = ticket;

		Validate.notNull(product, "product cannot be null!");
		this.product = product;
		this.unitPrice = product.getUnitPrice();
		this.taxable1Flag = product.getTaxable1Flag();
		this.taxable2Flag = product.getTaxable2Flag();
		this.taxable3Flag = product.getTaxable3Flag();
		setQuantity(1);
	}

	public Long getTicketDataId() {
		return ticketDataId;
	}

	public void setTicketDataId(Long ticketDataId) {
		this.ticketDataId = ticketDataId;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		totalSalesAmount = quantity * unitPrice;
		ticket.calculateTotal();
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public Double getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public Boolean getTaxable1Flag() {
		return taxable1Flag;
	}

	public Boolean getTaxable2Flag() {
		return taxable2Flag;
	}

	public Boolean getTaxable3Flag() {
		return taxable3Flag;
	}

}
