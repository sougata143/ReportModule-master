package com.sls.report.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JUTE_GATE_ENTRY_HDR")
public class JuteEntryHeader {
	
	@Id
	@Column(name="REC_ID", nullable = true)
	private  long id;
	
	@Column(name="CHALLAN_NO", nullable = true)
	private  long chalanNo;
	
	@Column(name="MR_NO", nullable = true)
	private  long mrNo;
	
	@Column(name="PO_NO", nullable = true)
	private  String poNo;
	
	@Column(name="CHALLAN_DATE", nullable = true)
	private  Date chalanDate ;
	
	@Column(name="MUKAM", nullable = true)
	private  String mukam ;
	
	@Column(name="BROKER_ADDRESS", nullable = true)
	private String brokerAddress;
	
	@Column(name="VEHICLE_NO", nullable = true)
	private String vehicleNo;
	
	@Column(name="DRIVER_NAME", nullable = true)
	private String driverName;
	
	@Column(name="BROKER_NAME", nullable = true)
	private String brokerName;
	
	@Column(name="TRANSPORTER", nullable = true)
	private String transporter;
	
	@Column(name="IN_DATE", nullable = true)
	private Date inDate;
	
	@Column(name="UPDATE_BY", nullable = true)
	private String updateBy;
	
	@Column(name="IN_TIME", nullable = true)
	private Timestamp inTime;
	
	@Column(name="OUT_TIME", nullable = true)
	private Timestamp outTime;
	
	@Column(name="OUT_DATE", nullable = true)
	private Date outDate;
	
	@Column(name="UPDATE_DATE_TIME", nullable = true)
	private Timestamp updateDateTime;
	
	@Column(name="FIN_YEAR", nullable = true)
	private String finYear;
	
	@Column(name="OPEN_CLOSE", nullable = true)
	private String openClose;
	
	@Column(name="NET_WEIGHT", nullable = true)
	private Long netWeight;
	
	@Column(name="GROSS_WEIGHT", nullable = true)
	private Long grossWeight;
	
	@Column(name="ACTUAL_WEIGHT", nullable = true)
	private Long actualWeight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChalanNo() {
		return chalanNo;
	}

	public void setChalanNo(Long chalanNo) {
		this.chalanNo = chalanNo;
	}

	public Long getMrNo() {
		return mrNo;
	}

	public void setMrNo(Long mrNo) {
		this.mrNo = mrNo;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getChalanDate() {
		return chalanDate;
	}

	public void setChalanDate(Date chalanDate) {
		this.chalanDate = chalanDate;
	}

	public String getMukam() {
		return mukam;
	}

	public void setMukam(String mukam) {
		this.mukam = mukam;
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

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getInTime() {
		return inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getOpenClose() {
		return openClose;
	}

	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}

	public Long getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Long netWeight) {
		this.netWeight = netWeight;
	}

	public Long getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Long grossWeight) {
		this.grossWeight = grossWeight;
	}

	public long getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Long actualWeight) {
		this.actualWeight = actualWeight;
	}

	@Override
	public String toString() {
		return "JuteEntryHeader [id=" + id + ", chalanNo=" + chalanNo + ", mrNo=" + mrNo + ", poNo=" + poNo
				+ ", chalanDate=" + chalanDate + ", mukam=" + mukam + ", brokerAddress=" + brokerAddress
				+ ", vehicleNo=" + vehicleNo + ", driverName=" + driverName + ", brokerName=" + brokerName
				+ ", transporter=" + transporter + ", inDate=" + inDate + ", updateBy=" + updateBy + ", inTime="
				+ inTime + ", outTime=" + outTime + ", outDate=" + outDate + ", updateDateTime=" + updateDateTime
				+ ", finYear=" + finYear + ", openClose=" + openClose + ", netWeight=" + netWeight + ", grossWeight="
				+ grossWeight + ", actualWeight=" + actualWeight + "]";
	}

	public JuteEntryHeader(long id, long chalanNo, long mrNo, String poNo, Date chalanDate, String mukam,
			String brokerAddress, String vehicleNo, String driverName, String brokerName, String transporter,
			Date inDate, String updateBy, Timestamp inTime, Timestamp outTime, Date outDate, Timestamp updateDateTime,
			String finYear, String openClose, long netWeight, long grossWeight, long actualWeight) {
		super();
		this.id = id;
		this.chalanNo = chalanNo;
		this.mrNo = mrNo;
		this.poNo = poNo;
		this.chalanDate = chalanDate;
		this.mukam = mukam;
		this.brokerAddress = brokerAddress;
		this.vehicleNo = vehicleNo;
		this.driverName = driverName;
		this.brokerName = brokerName;
		this.transporter = transporter;
		this.inDate = inDate;
		this.updateBy = updateBy;
		this.inTime = inTime;
		this.outTime = outTime;
		this.outDate = outDate;
		this.updateDateTime = updateDateTime;
		this.finYear = finYear;
		this.openClose = openClose;
		this.netWeight = netWeight;
		this.grossWeight = grossWeight;
		this.actualWeight = actualWeight;
	}

	public JuteEntryHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	


}
