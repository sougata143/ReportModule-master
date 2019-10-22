package com.sls.report.dto;

public class ReceiptDTO {

	private String packingType;
	private String no;
	private Long baleQuantity;
	private Long looseQuantity;
	private String claimsCondition;
	private String claimsQuality;
	private Long actualWeight;
	private Long rate;
	private Long price;
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
	public Long getBaleQuantity() {
		return baleQuantity;
	}
	public void setBaleQuantity(Long baleQuantity) {
		this.baleQuantity = baleQuantity;
	}
	public Long getLooseQuantity() {
		return looseQuantity;
	}
	public void setLooseQuantity(Long looseQuantity) {
		this.looseQuantity = looseQuantity;
	}
	public String getClaimsCondition() {
		return claimsCondition;
	}
	public void setClaimsCondition(String claimsCondition) {
		this.claimsCondition = claimsCondition;
	}
	public String getClaimsQuality() {
		return claimsQuality;
	}
	public void setClaimsQuality(String claimsQuality) {
		this.claimsQuality = claimsQuality;
	}
	public Long getActualWeight() {
		return actualWeight;
	}
	public void setActualWeight(Long actualWeight) {
		this.actualWeight = actualWeight;
	}
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}

	
	

}
