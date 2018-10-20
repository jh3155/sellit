package com.sellit.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart extends BaseTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	private Long cartId;

	@OneToMany(cascade = CascadeType.DETACH)
	@Column(name = "CART_ID")
	private Set<CartItem> cartItems = new HashSet<>();

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "CASHIER_ID", referencedColumnName = "EMPLOYEE_ID")
	private Employee cashierId;

	@Column(name = "ORDER_DTM")
	private LocalDateTime orderDateTime;

	// TODO customer

	@Column(name = "QUANTITY_TOTAL")
	private Integer quantityTotal;

	@Column(name = "ORDER_SUB_TOTAL")
	private BigDecimal orderSubTotal;

	@Column(name = "ORDER_TAX1_TOTAL")
	private BigDecimal orderTax1Total;

	@Column(name = "ORDER_TAX2_TOTAL")
	private BigDecimal orderTax2Total;

	@Column(name = "ORDER_TAX3_TOTAL")
	private BigDecimal orderTax3Total;

	@Column(name = "ORDER_TAX_TOTAL")
	private BigDecimal orderTaxTotal;

	@Column(name = "ORDER_GRAND_TOTAL")
	private BigDecimal orderGrandTotal;

}
