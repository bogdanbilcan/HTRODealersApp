package com.logika.custom.honda.dealerapp.model;

public class HtroDealers {

	protected String dealerID;
	protected String dealerName;

	public HtroDealers(String dealerID, String dealerName) {
		this.dealerID = dealerID;
		this.dealerName = dealerName;
	}

	public HtroDealers() {
		super();
	}

	/**
	 * @return the dealerID
	 */
	public String getDealerID() {
		return dealerID;
	}

	/**
	 * @param dealerID
	 *            the dealerID to set
	 */
	public void setDealerID(String dealerID) {
		this.dealerID = dealerID;
	}

	/**
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * @param dealerName
	 *            the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

}
