package com.sls.report.dto;

import java.sql.Date;

public class SummaryReportLineItemDTO {
	
	private String quality;
	private String godownNo;
	private long bales;
	private String quantityUnit;
	private long weight;
	private String conversionType;
	private long loose;
	
	
	
	public long getLoose() {
		return loose;
	}
	public void setLoose(long loose) {
		this.loose = loose;
	}
	public String getConversionType() {
		return conversionType;
	}
	public void setConversionType(String conversionType) {
		this.conversionType = conversionType;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getGodownNo() {
		return godownNo;
	}
	public void setGodownNo(String godownNo) {
		this.godownNo = godownNo;
	}
	public long getBales() {
		return bales;
	}
	public void setBales(long bales) {
		this.bales = bales;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	
	
	
	
	
	

}
