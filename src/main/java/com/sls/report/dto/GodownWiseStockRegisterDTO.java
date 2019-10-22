package com.sls.report.dto;

public class GodownWiseStockRegisterDTO {

	private float bale;
	private float loose;
	private float weight;
	private long hbale;
	private long pbale;
	
	
	public float getBale() {
		return bale;
	}
	public void setBale(float bale) {
		this.bale = bale;
	}
	public float getLoose() {
		return loose;
	}
	public void setLoose(float loose) {
		this.loose = loose;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public long getHbale() {
		return hbale;
	}
	public void setHbale(long hbale) {
		this.hbale = hbale;
	}
	public long getPbale() {
		return pbale;
	}
	public void setPbale(long pbale) {
		this.pbale = pbale;
	}
	@Override
	public String toString() {
		return "GodownWiseStockRegisterDTO [bale=" + bale + ", loose=" + loose + ", weight="
				+ weight + ", hbale=" + hbale + ", pbale=" + pbale + "]";
	}
	public GodownWiseStockRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
