package com.sls.report.services;

import java.sql.Date;
import java.util.List;

import com.sls.report.dto.GroupwiseQualityStockRegisterGeneratedPdfReportDTO;

/*
 * PhysicalStock service interface
 */
public interface GroupwiseQualityStockRegisterReportService {
	
	public List<GroupwiseQualityStockRegisterGeneratedPdfReportDTO>
								getGroupwiseQualityStockRegisterReport(Date modon);
	

}
