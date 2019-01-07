package com.sellit.enums;

public enum ConfigEnum {

	//@formatter:off
	
	TAX1_RATE("TAX", "TAX1_RATE"),
	TAX2_RATE("TAX", "TAX2_RATE"),
	TAX3_RATE("TAX", "TAX3_RATE")

	//@formatter:on
	;

	private String category;
	private String value;

	ConfigEnum(final String category, final String value) {
		this.category = category;
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public String getValue() {
		return value;
	}

}
