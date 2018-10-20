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
@Table(name = "CART_ITEM")
public class CartItem extends BaseTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ITEM_ID")
	private Long cartItemId;

	@ManyToOne(targetEntity = Cart.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID", insertable = false, updatable = false)
	private Cart cart;

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", insertable = false, updatable = false)
	private Product product;

	@Column(name = "LINE_NUMBER")
	private Integer lineNumber;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
	private Employee employeeId;

	@Column(name = "ORDER_DTM")
	private LocalDateTime orderDateTime;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name = "ITEM_SUB_TOTAL")
	private BigDecimal itemSubTotal;

	@Column(name = "ITEM_TAX1_TOTAL")
	private BigDecimal itemTax1Total;

	@Column(name = "ITEM_TAX2_TOTAL")
	private BigDecimal itemTax2Total;

	@Column(name = "ITEM_TAX3_TOTAL")
	private BigDecimal itemTax3Total;

	@Column(name = "ITEM_TAX_TOTAL")
	private BigDecimal itemTaxTotal;

	@Column(name = "ITEM_GRAND_TOTAL")
	private BigDecimal itemGrandTotal;

}
