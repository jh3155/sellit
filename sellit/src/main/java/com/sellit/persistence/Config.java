package com.sellit.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG")
public class Config extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONFIG_ID")
	private Long customerId;

	@Column(name = "CONFIG_CATEGORY")
	private String configCategory;

	@Column(name = "CONFIG_NAME")
	private String configName;

	@Column(name = "CONFIG_VALUE")
	private String configValue;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public String getConfigCategory() {
		return configCategory;
	}

	public void setConfigCategory(final String configCategory) {
		this.configCategory = configCategory;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(final String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(final String configValue) {
		this.configValue = configValue;
	}

}
