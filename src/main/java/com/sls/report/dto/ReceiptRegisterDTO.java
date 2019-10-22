package com.sls.report.dto;

import java.sql.Date;
import java.util.List;

import com.sls.report.entity.ScmMrLineItem;

public class ReceiptRegisterDTO {
	
	private Date challanDate;
	private String brokerAddress;
	private String vehicleNo;
	private Date inDate;
	private String chalanNo;
	
	private List<ReceiptDTO> juteEntryDtls;
	
	private float totalQuantity;
	private Float totalcondition;
	private Float totalQuality;
	private long totalRate;
	private long totalBalePrice;
	private long totalLoosePrice;
	
	
	public long getTotalBalePrice() {
		return totalBalePrice;
	}
	public void setTotalBalePrice(long totalBalePrice) {
		this.totalBalePrice = totalBalePrice;
	}
	public long getTotalLoosePrice() {
		return totalLoosePrice;
	}
	public void setTotalLoosePrice(long totalLoosePrice) {
		this.totalLoosePrice = totalLoosePrice;
	}
	public Float getTotalQuality() {
		return totalQuality;
	}
	public void setTotalQuality(Float totalQuality) {
		this.totalQuality = totalQuality;
	}
	public long getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
	public String getChalanNo() {
		return chalanNo;
	}
	public void setChalanNo(String chalanNo) {
		this.chalanNo = chalanNo;
	}
	public Float getTotalcondition() {
		return totalcondition;
	}
	public void setTotalcondition(Float totalcondition) {
		this.totalcondition = totalcondition;
	}
	public float getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(float totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public List<ReceiptDTO> getJuteEntryDtls() {
		return juteEntryDtls;
	}
	public void setJuteEntryDtls(List<ReceiptDTO> juteEntryDtls) {
		this.juteEntryDtls = juteEntryDtls;
	}
	public Date getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(Date challanDate) {
		this.challanDate = challanDate;
	}
	public String getBrokerAddress() {
		return brokerAddress;
	}
	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	
	
	
	
	
	
	

}
