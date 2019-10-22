package com.sls.report.dto;

import java.sql.Date;
import java.util.List;

public class SummaryRecieptRegisterReportDTO {

	private String recptNo;
	private Date date;
	private List<SummaryReportLineItemDTO> lineitems;
	private SummaryReportGrandTotalDTO summarygrandtotal;
	
	
	public SummaryReportGrandTotalDTO getSummarygrandtotal() {
		return summarygrandtotal;
	}
	public void setSummarygrandtotal(SummaryReportGrandTotalDTO summarygrandtotal) {
		this.summarygrandtotal = summarygrandtotal;
	}
	public String getRecptNo() {
		return recptNo;
	}
	public void setRecptNo(String recptNo) {
		this.recptNo = recptNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<SummaryReportLineItemDTO> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<SummaryReportLineItemDTO> lineitems) {
		this.lineitems = lineitems;
	}
	
	
	
	
	
	
}
