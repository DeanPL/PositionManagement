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
	private String securityCode;
    private Integer quantity;

    private String insert_update_cancel;
    private String buy_sell;
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

	public String getInsert_update_cancel() {
		return insert_update_cancel;
	}

	public void setInsert_update_cancel(String insert_update_cancel) {
		this.insert_update_cancel = insert_update_cancel;
	}

	public String getBuy_sell() {
		return buy_sell;
	}

	public void setBuy_sell(String buy_sell) {
		this.buy_sell = buy_sell;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionID=" + transactionID +
				", tradeID=" + tradeID +
				", version=" + version +
				", securityCode='" + securityCode + '\'' +
				", quantity=" + quantity +
				", insert_update_cancel='" + insert_update_cancel + '\'' +
				", buy_sell='" + buy_sell + '\'' +
				'}';
	}
}
