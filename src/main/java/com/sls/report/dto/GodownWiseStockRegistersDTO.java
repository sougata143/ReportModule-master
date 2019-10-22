package com.sls.report.dto;

import java.util.List;

public class GodownWiseStockRegistersDTO {

	private String quality;
	private List<GodownWiseStockRegisterDTO> reportrows;
	private GodownWiseStockRegisterDTO godowntotal;
	
	
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public List<GodownWiseStockRegisterDTO> getReportrows() {
		return reportrows;
	}
	public void setReportrows(List<GodownWiseStockRegisterDTO> reportrows) {
		this.reportrows = reportrows;
	}
	public GodownWiseStockRegisterDTO getGodowntotal() {
		return godowntotal;
	}
	public void setGodowntotal(GodownWiseStockRegisterDTO godowntotal) {
		this.godowntotal = godowntotal;
	}
	
	
}
