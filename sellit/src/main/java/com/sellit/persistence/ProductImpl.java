package com.sellit.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductImpl implements Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public Long getProductId() {
		return productId;
	}

	@Override
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String getBarcode() {
		return barcode;
	}

	@Override
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String getShortNameInEnglish() {
		return shortNameInEnglish;
	}

	@Override
	public void setShortNameInEnglish(String shortNameInEnglish) {
		this.shortNameInEnglish = shortNameInEnglish;
	}

	@Override
	public String getShortNameInOtherLanguage() {
		return shortNameInOtherLanguage;
	}

	@Override
	public void setShortNameInOtherLanguage(String shortNameInOtherLanguage) {
		this.shortNameInOtherLanguage = shortNameInOtherLanguage;
	}

	@Override
	public Integer getInventoryOnHand() {
		return inventoryOnHand;
	}

	@Override
	public void setInventoryOnHand(Integer inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}

	@Override
	public Integer getSafetyInventoryOnHand() {
		return safetyInventoryOnHand;
	}

	@Override
	public void setSafetyInventoryOnHand(Integer safetyInventoryOnHand) {
		this.safetyInventoryOnHand = safetyInventoryOnHand;
	}

	@Override
	public BigDecimal getCostAmount() {
		return costAmount;
	}

	@Override
	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	@Override
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	@Override
	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

}
