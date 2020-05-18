package com.infosys.demo.entity;

import java.io.Serializable;

public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6359263396065405008L;
	private Integer id;
	private String securityCode;
	private Integer quantity;

	private String buyOrSell;
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	@Override
	public String toString() {
		return "Position{" +
				"id=" + id +
				", securityCode='" + securityCode + '\'' +
				", quantity=" + quantity +
				", buyOrSell='" + buyOrSell + '\'' +
				'}';
	}
}
