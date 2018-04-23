package com.sellit.persistence;

import java.math.BigDecimal;

public interface Product {

	Long getProductId();

	void setProductId(Long productId);

	String getBarcode();

	void setBarcode(String barcode);

	String getFullName();

	void setFullName(String fullName);

	String getShortNameInEnglish();

	void setShortNameInEnglish(String shortNameInEnglish);

	String getShortNameInOtherLanguage();

	void setShortNameInOtherLanguage(String shortNameInOtherLanguage);

	Integer getInventoryOnHand();

	void setInventoryOnHand(Integer inventoryOnHand);

	Integer getSafetyInventoryOnHand();

	void setSafetyInventoryOnHand(Integer safetyInventoryOnHand);

	BigDecimal getCostAmount();

	void setCostAmount(BigDecimal costAmount);

	BigDecimal getSalesAmount();

	void setSalesAmount(BigDecimal salesAmount);

}