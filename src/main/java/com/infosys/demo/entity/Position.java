package com.infosys.demo.entity;

import java.io.Serializable;

public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6359263396065405008L;
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



	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [securityCode=");
		builder.append(securityCode);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", buyOrSell=");
		builder.append(buyOrSell);
		builder.append("]");
		return builder.toString();
	}
}
