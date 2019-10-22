package com.sls.report.dto;

public class JuteRateReportDTO {
	
	private String jcode;
	private String description;
	private String batch;
	private String rate;
	public String getJcode() {
		return jcode;
	}
	public void setJcode(String jcode) {
		this.jcode = jcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "JuteRateReportDTO [jcode=" + jcode + ", description=" + description + ", batch=" + batch + ", rate="
				+ rate + "]";
	}
	public JuteRateReportDTO(String jcode, String description, String batch, String rate) {
		super();
		this.jcode = jcode;
		this.description = description;
		this.batch = batch;
		this.rate = rate;
	}
	public JuteRateReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
