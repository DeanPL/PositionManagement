package com.infosys.demo.entity;

import java.io.Serializable;

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1460415589842353632L;
	private Integer transactionID;
	private Integer tradeID;
	private Integer version;
	private Integer securityCode;
    private Integer quantity;
    private String IUC;
    private String trade;
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public Integer getTradeID() {
		return tradeID;
	}
	public void setTradeID(Integer tradeID) {
		this.tradeID = tradeID;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getIUC() {
		return IUC;
	}
	public void setIUC(String iUC) {
		IUC = iUC;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
    
}
