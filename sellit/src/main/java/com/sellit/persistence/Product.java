package com.sellit.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "PRODUCT")
public class Product extends BaseTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
	private Department department;

	@Column(name = "BARCODE")
	private String barcode;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_MENU_NAME")
	private String productMenuName;

	@Column(name = "INVENTORY_ON_HAND")
	private Integer inventoryOnHand;

	@Column(name = "SAFETY_INVENTORY_ON_HAND")
	private Integer safetyInventoryOnHand;

	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name = "TAXABLE1")
	@Type(type = "yes_no")
	private Boolean taxable1;

	@Column(name = "TAXABLE2")
	@Type(type = "yes_no")
	private Boolean taxable2;

	@Column(name = "TAXABLE3")
	@Type(type = "yes_no")
	private Boolean taxable3;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMenuName() {
		return productMenuName;
	}

	public void setProductMenuName(String productMenuName) {
		this.productMenuName = productMenuName;
	}

	public Integer getInventoryOnHand() {
		return inventoryOnHand;
	}

	public void setInventoryOnHand(Integer inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}

	public Integer getSafetyInventoryOnHand() {
		return safetyInventoryOnHand;
	}

	public void setSafetyInventoryOnHand(Integer safetyInventoryOnHand) {
		this.safetyInventoryOnHand = safetyInventoryOnHand;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getTaxable1() {
		return taxable1;
	}

	public void setTaxable1(Boolean taxable1) {
		this.taxable1 = taxable1;
	}

	public Boolean getTaxable2() {
		return taxable2;
	}

	public void setTaxable2(Boolean taxable2) {
		this.taxable2 = taxable2;
	}

	public Boolean getTaxable3() {
		return taxable3;
	}

	public void setTaxable3(Boolean taxable3) {
		this.taxable3 = taxable3;
	}

}
