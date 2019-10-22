package com.sls.report.dto;

public class StockRegisterDTO {
	
	private long pbale;
	private long bale;
	private long loose;
	private long hbale;
	private long drums;
	private float actualWeight;
	public long getPbale() {
		return pbale;
	}
	public void setPbale(long pbale) {
		this.pbale = pbale;
	}
	public long getBale() {
		return bale;
	}
	public void setBale(long bale) {
		this.bale = bale;
	}
	public long getLoose() {
		return loose;
	}
	public void setLoose(long loose) {
		this.loose = loose;
	}
	public long getHbale() {
		return hbale;
	}
	public void setHbale(long hbale) {
		this.hbale = hbale;
	}
	public long getDrums() {
		return drums;
	}
	public void setDrums(long drums) {
		this.drums = drums;
	}
	public float getActualWeight() {
		return actualWeight;
	}
	public void setActualWeight(float actualWeight) {
		this.actualWeight = actualWeight;
	}
	@Override
	public String toString() {
		return "StockRegisterDTO [pbale=" + pbale + ", bale=" + bale + ", loose=" + loose + ", hbale=" + hbale
				+ ", drums=" + drums + ", actualWeight=" + actualWeight + "]";
	}
	public StockRegisterDTO(long pbale, long bale, long loose, long hbale, long drums, float actualWeight) {
		super();
		this.pbale = pbale;
		this.bale = bale;
		this.loose = loose;
		this.hbale = hbale;
		this.drums = drums;
		this.actualWeight = actualWeight;
	}
	public StockRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
