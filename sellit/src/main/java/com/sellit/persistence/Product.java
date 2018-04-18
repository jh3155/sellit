package com.sellit.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "BARCODE")
	private String barcode;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "SHORT_NAME_ENG")
	private String shortNameInEnglish;

	@Column(name = "SHORT_NAME_OTHER")
	private String shortNameInOtherLanguage;

	@Column(name = "INVENTORY_ON_HAND")
	private Integer inventoryOnHand;

	@Column(name = "SAFETY_INVENTORY_ON_HAND")
	private Integer safetyInventoryOnHand;

	@Column(name = "COST_AMT")
	private BigDecimal costAmount;

	@Column(name = "SALES_AMT")
	private BigDecimal salesAmount;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortNameInEnglish() {
		return shortNameInEnglish;
	}

	public void setShortNameInEnglish(String shortNameInEnglish) {
		this.shortNameInEnglish = shortNameInEnglish;
	}

	public String getShortNameInOtherLanguage() {
		return shortNameInOtherLanguage;
	}

	public void setShortNameInOtherLanguage(String shortNameInOtherLanguage) {
		this.shortNameInOtherLanguage = shortNameInOtherLanguage;
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

	public BigDecimal getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

}
