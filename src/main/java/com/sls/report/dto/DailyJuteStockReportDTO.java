package com.sls.report.dto;

public class DailyJuteStockReportDTO {
	
	private long jcode;
	private String quality;
	private long issueRate;
	private String oppenningWt;
	private String stockForDays;
	private String group;
	private String issueWeight;
	private String issueAmmount;
	private String deliveryWeight;
	private String adjutsWeight;
	private String closingWeight;
	private String monthreceiptWt;
	private String monthIssueWt;
	private String monthIssueAmmount;
	private String receiptWeight;
	
	public long getJcode() {
		return jcode;
	}
	public void setJcode(long jcode) {
		this.jcode = jcode;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public long getIssueRate() {
		return issueRate;
	}
	public void setIssueRate(long issueRate) {
		this.issueRate = issueRate;
	}
	public String getOppenningWt() {
		return oppenningWt;
	}
	public void setOppenningWt(String oppenningWt) {
		this.oppenningWt = oppenningWt;
	}
	public String getStockForDays() {
		return stockForDays;
	}
	public void setStockForDays(String stockForDays) {
		this.stockForDays = stockForDays;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getIssueWeight() {
		return issueWeight;
	}
	public void setIssueWeight(String issueWeight) {
		this.issueWeight = issueWeight;
	}
	public String getIssueAmmount() {
		return issueAmmount;
	}
	public void setIssueAmmount(String issueAmmount) {
		this.issueAmmount = issueAmmount;
	}
	public String getDeliveryWeight() {
		return deliveryWeight;
	}
	public void setDeliveryWeight(String deliveryWeight) {
		this.deliveryWeight = deliveryWeight;
	}
	public String getAdjutsWeight() {
		return adjutsWeight;
	}
	public void setAdjutsWeight(String adjutsWeight) {
		this.adjutsWeight = adjutsWeight;
	}
	public String getClosingWeight() {
		return closingWeight;
	}
	public void setClosingWeight(String closingWeight) {
		this.closingWeight = closingWeight;
	}
	public String getMonthreceiptWt() {
		return monthreceiptWt;
	}
	public void setMonthreceiptWt(String monthreceiptWt) {
		this.monthreceiptWt = monthreceiptWt;
	}
	public String getMonthIssueWt() {
		return monthIssueWt;
	}
	public void setMonthIssueWt(String monthIssueWt) {
		this.monthIssueWt = monthIssueWt;
	}
	public String getMonthIssueAmmount() {
		return monthIssueAmmount;
	}
	public void setMonthIssueAmmount(String monthIssueAmmount) {
		this.monthIssueAmmount = monthIssueAmmount;
	}
	public String getReceiptWeight() {
		return receiptWeight;
	}
	public void setReceiptWeight(String receiptWeight) {
		this.receiptWeight = receiptWeight;
	}
	@Override
	public String toString() {
		return "DailyJuteStockReportDTO [jcode=" + jcode + ", quality=" + quality + ", issueRate=" + issueRate
				+  ", oppenningWt=" + oppenningWt + ", stockForDays=" + stockForDays
				+ ", group=" + group + ", issueWeight=" + issueWeight + ", issueAmmount=" + issueAmmount
				+ ", deliveryWeight=" + deliveryWeight + ", adjutsWeight=" + adjutsWeight + ", closingWeight="
				+ closingWeight + ", monthreceiptWt=" + monthreceiptWt + ", monthIssueWt=" + monthIssueWt
				+ ", monthIssueAmmount=" + monthIssueAmmount + ", receiptWeight=" + receiptWeight + "]";
	}
	public DailyJuteStockReportDTO(long jcode, String quality, long issueRate, String oppenningWt,
			String stockForDays, String group, String issueWeight, String issueAmmount, String deliveryWeight,
			String adjutsWeight, String closingWeight, String monthreceiptWt, String monthIssueWt,
			String monthIssueAmmount, String receiptWeight) {
		super();
		this.jcode = jcode;
		this.quality = quality;
		this.issueRate = issueRate;
		this.oppenningWt = oppenningWt;
		this.stockForDays = stockForDays;
		this.group = group;
		this.issueWeight = issueWeight;
		this.issueAmmount = issueAmmount;
		this.deliveryWeight = deliveryWeight;
		this.adjutsWeight = adjutsWeight;
		this.closingWeight = closingWeight;
		this.monthreceiptWt = monthreceiptWt;
		this.monthIssueWt = monthIssueWt;
		this.monthIssueAmmount = monthIssueAmmount;
		this.receiptWeight = receiptWeight;
	}
	public DailyJuteStockReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
