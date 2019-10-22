package com.sls.report.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity Class for SCM_MR_LINE_ITEM
 */

@Entity
@Table(name="SCM_MR_LINE_ITEM")
public class ScmMrLineItem {
	
	@Id
	@Column(name="JUTE_LINE_ITEM_NO", nullable = true)
	private Long juteLineItemNo;
	
	@Column(name="JUTE_RECEIVE_NO", nullable = true)
	private Long juteReceiveNo;
	
	@Column(name="QUANTITY", nullable = true)
	private Long quantity;
	
	@Column(name="MARKA", nullable = true)
	private String marka;
	
	@Column(name="ADVISED_QUALITY", nullable = true)
	private String advisedQuality;
	
	@Column(name="ACTUAL_QUALITY", nullable = true)
	private String actualQuality;
	
	@Column(name="ADVISED_WEIGHT", nullable = true)
	private Long advisedWeight;
	
	@Column(name="ACTUAL_WEIGHT", nullable = true)
	private Long actualWeight;
	
	@Column(name="DEVIATION", nullable = true)
	private Long deviation;
	
	@Column(name="RATE", nullable = true)
	private Long rate;
	
	@Column(name="CLAIMS_QUALITY", nullable = true)
	private String claimsQuality;
	
	@Column(name="CLAIMS_CONDITION", nullable = true)
	private String claimsCondition;
	
	@Column(name="WAREHOUSE_NO", nullable = true)
	private String warehouseNo;
	
	@Column(name="REMARKS", nullable = true)
	private String remarks;
	
	@Column(name="TOTAL_PRICE", nullable = true)
	private long totalPrice;
	
	@Column(name="STATUS", nullable = true)
	private String status;
	
	@Column(name="ITEM_CODE", nullable = true)
	private String itemCode;
	
	@Column(name="APPROVER_LEVEL_FIRST", nullable = true)
	private String approverLevelFirst;
	
	@Column(name="APPROVER_LEVEL_SECOND", nullable = true)
	private String approverLevelSecond;
	
	@Column(name="APPROVE_LEVEL_FIRST_DATE", nullable = true)
	private Date approverLevelFirstDate;
	
	@Column(name="APPROVE_LEVEL_SECOND_DATE", nullable = true)
	private Date approverLevelSecondDate;
	
	@Column(name="DEBIT_CREDIT_NOTES_FLAG", nullable = true)
	private String debitCreditNotesFlag;
	
	@Column(name="QUANTITY_UNIT", nullable = true)
	private String quantityUnit;
	
	@Column(name="ISSUABLE_WEIGHT", nullable = true)
	private Long issuableWeight;
	
	@Column(name="BALE", nullable = true)
	private Long bale;
	
	@Column(name="LOOSE", nullable = true)
	private Long loose;
	
	@Column(name="ACTUAL_BALE", nullable = true)
	private Long actualBale;
	
	@Column(name="ACTUAL_LOOSE", nullable = true)
	private Long actualLoose;
	
	
	public long getActualBale() {
		return actualBale;
	}

	public void setActualBale(long actualBale) {
		this.actualBale = actualBale;
	}

	public long getActualLoose() {
		return actualLoose;
	}

	public void setActualLoose(long actualLoose) {
		this.actualLoose = actualLoose;
	}

	public long getJuteLineItemNo() {
		return juteLineItemNo;
	}

	public void setJuteLineItemNo(long juteLineItemNo) {
		this.juteLineItemNo = juteLineItemNo;
	}

	public long getJuteReceiveNo() {
		return juteReceiveNo;
	}

	public void setJuteReceiveNo(long juteReceiveNo) {
		this.juteReceiveNo = juteReceiveNo;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getAdvisedQuality() {
		return advisedQuality;
	}

	public void setAdvisedQuality(String advisedQuality) {
		this.advisedQuality = advisedQuality;
	}

	public String getActualQuality() {
		return actualQuality;
	}

	public void setActualQuality(String actualQuality) {
		this.actualQuality = actualQuality;
	}

	public long getAdvisedWeight() {
		return advisedWeight;
	}

	public void setAdvisedWeight(long advisedWeight) {
		this.advisedWeight = advisedWeight;
	}

	public long getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(long actualWeight) {
		this.actualWeight = actualWeight;
	}

	public long getDeviation() {
		return deviation;
	}

	public void setDeviation(long deviation) {
		this.deviation = deviation;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public String getClaimsQuality() {
		return claimsQuality;
	}

	public void setClaimsQuality(String claimsQuality) {
		this.claimsQuality = claimsQuality;
	}

	public String getClaimsCondition() {
		return claimsCondition;
	}

	public void setClaimsCondition(String claimsCondition) {
		this.claimsCondition = claimsCondition;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getApproverLevelFirst() {
		return approverLevelFirst;
	}

	public void setApproverLevelFirst(String approverLevelFirst) {
		this.approverLevelFirst = approverLevelFirst;
	}

	public String getApproverLevelSecond() {
		return approverLevelSecond;
	}

	public void setApproverLevelSecond(String approverLevelSecond) {
		this.approverLevelSecond = approverLevelSecond;
	}

	public Date getApproverLevelFirstDate() {
		return approverLevelFirstDate;
	}

	public void setApproverLevelFirstDate(Date approverLevelFirstDate) {
		this.approverLevelFirstDate = approverLevelFirstDate;
	}

	public Date getApproverLevelSecondDate() {
		return approverLevelSecondDate;
	}

	public void setApproverLevelSecondDate(Date approverLevelSecondDate) {
		this.approverLevelSecondDate = approverLevelSecondDate;
	}

	public String getDebitCreditNotesFlag() {
		return debitCreditNotesFlag;
	}

	public void setDebitCreditNotesFlag(String debitCreditNotesFlag) {
		this.debitCreditNotesFlag = debitCreditNotesFlag;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public long getIssuableWeight() {
		return issuableWeight;
	}

	public void setIssuableWeight(long issuableWeight) {
		this.issuableWeight = issuableWeight;
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

	@Override
	public String toString() {
		return "ScmMrLineItem [juteLineItemNo=" + juteLineItemNo + ", juteReceiveNo=" + juteReceiveNo + ", quantity="
				+ quantity + ", marka=" + marka + ", advisedQuality=" + advisedQuality + ", actualQuality="
				+ actualQuality + ", advisedWeight=" + advisedWeight + ", actualWeight=" + actualWeight + ", deviation="
				+ deviation + ", rate=" + rate + ", claimsQuality=" + claimsQuality + ", claimsCondition="
				+ claimsCondition + ", warehouseNo=" + warehouseNo + ", remarks=" + remarks + ", totalPrice="
				+ totalPrice + ", status=" + status + ", itemCode=" + itemCode + ", approverLevelFirst="
				+ approverLevelFirst + ", approverLevelSecond=" + approverLevelSecond + ", approverLevelFirstDate="
				+ approverLevelFirstDate + ", approverLevelSecondDate=" + approverLevelSecondDate
				+ ", debitCreditNotesFlag=" + debitCreditNotesFlag + ", quantityUnit=" + quantityUnit
				+ ", issuableWeight=" + issuableWeight + ", bale=" + bale + ", loose=" + loose + "]";
	}

	public ScmMrLineItem(long juteLineItemNo, long juteReceiveNo, long quantity, String marka, String advisedQuality,
			String actualQuality, long advisedWeight, long actualWeight, long deviation, long rate,
			String claimsQuality, String claimsCondition, String warehouseNo, String remarks, long totalPrice,
			String status, String itemCode, String approverLevelFirst, String approverLevelSecond,
			Date approverLevelFirstDate, Date approverLevelSecondDate, String debitCreditNotesFlag, String quantityUnit,
			long issuableWeight, long bale, long loose) {
		super();
		this.juteLineItemNo = juteLineItemNo;
		this.juteReceiveNo = juteReceiveNo;
		this.quantity = quantity;
		this.marka = marka;
		this.advisedQuality = advisedQuality;
		this.actualQuality = actualQuality;
		this.advisedWeight = advisedWeight;
		this.actualWeight = actualWeight;
		this.deviation = deviation;
		this.rate = rate;
		this.claimsQuality = claimsQuality;
		this.claimsCondition = claimsCondition;
		this.warehouseNo = warehouseNo;
		this.remarks = remarks;
		this.totalPrice = totalPrice;
		this.status = status;
		this.itemCode = itemCode;
		this.approverLevelFirst = approverLevelFirst;
		this.approverLevelSecond = approverLevelSecond;
		this.approverLevelFirstDate = approverLevelFirstDate;
		this.approverLevelSecondDate = approverLevelSecondDate;
		this.debitCreditNotesFlag = debitCreditNotesFlag;
		this.quantityUnit = quantityUnit;
		this.issuableWeight = issuableWeight;
		this.bale = bale;
		this.loose = loose;
	}

	public ScmMrLineItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
