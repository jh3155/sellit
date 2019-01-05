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

@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
	private Department department;

	@Column(name = "BARCODE")
	private String barcode;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "UNIT_PRICE")
	private Double unitPrice;

	@Column(name = "TAXABLE_1_FLG")
	private Boolean taxable1Flag;

	@Column(name = "TAXABLE_2_FLG")
	private Boolean taxable2Flag;

	@Column(name = "TAXABLE_3_FLG")
	private Boolean taxable3Flag;

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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getTaxable1Flag() {
		return taxable1Flag;
	}

	public void setTaxable1Flag(Boolean taxable1Flag) {
		this.taxable1Flag = taxable1Flag;
	}

	public Boolean getTaxable2Flag() {
		return taxable2Flag;
	}

	public void setTaxable2Flag(Boolean taxable2Flag) {
		this.taxable2Flag = taxable2Flag;
	}

	public Boolean getTaxable3Flag() {
		return taxable3Flag;
	}

	public void setTaxable3Flag(Boolean taxable3Flag) {
		this.taxable3Flag = taxable3Flag;
	}

}
