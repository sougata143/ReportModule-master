package com.sls.report.dto;

public class JuteEntryDtlForReceiptRegiDTO {
	
	private String no;
	private long quantity;
	private String packingType;

	
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
}
