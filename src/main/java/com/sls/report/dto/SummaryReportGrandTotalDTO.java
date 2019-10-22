package com.sls.report.dto;

public class SummaryReportGrandTotalDTO {

	private long bales;
	private String quantityUnit;
	private long weight;
	private long loose;
	
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
	public long getLoose() {
		return loose;
	}
	public void setLoose(long loose) {
		this.loose = loose;
	}
	
}
