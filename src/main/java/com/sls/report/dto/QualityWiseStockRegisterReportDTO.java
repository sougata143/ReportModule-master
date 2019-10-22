package com.sls.report.dto;

import java.util.List;

public class QualityWiseStockRegisterReportDTO {

	private String actualQuality;
	private StockRegisterDTO openningStock;
	private StockRegisterDTO issue;
	private StockRegisterDTO closingstock;
	
	private QualityWiseStockRegisterReportTotalDTO grandtotal;
	
	public QualityWiseStockRegisterReportTotalDTO getGrandtotal() {
		return grandtotal;
	}
	public void setGrandtotal(QualityWiseStockRegisterReportTotalDTO grandtotal) {
		this.grandtotal = grandtotal;
	}
	public String getActualQuality() {
		return actualQuality;
	}
	public void setActualQuality(String actualQuality) {
		this.actualQuality = actualQuality;
	}
	public StockRegisterDTO getOpenningStock() {
		return openningStock;
	}
	public void setOpenningStock(StockRegisterDTO openningStock) {
		this.openningStock = openningStock;
	}
	public StockRegisterDTO getIssue() {
		return issue;
	}
	public void setIssue(StockRegisterDTO issue) {
		this.issue = issue;
	}
	public StockRegisterDTO getClosingstock() {
		return closingstock;
	}
	public void setClosingstock(StockRegisterDTO closingstock) {
		this.closingstock = closingstock;
	}
	@Override
	public String toString() {
		return "QualityWiseStockRegisterReportDTO [actualQuality=" + actualQuality + "]";
	}
	public QualityWiseStockRegisterReportDTO(String actualQuality, StockRegisterDTO openningStock, StockRegisterDTO issue,
			StockRegisterDTO closingstock) {
		super();
		this.actualQuality = actualQuality;
		this.openningStock = openningStock;
		this.issue = issue;
		this.closingstock = closingstock;
	}
	public QualityWiseStockRegisterReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
