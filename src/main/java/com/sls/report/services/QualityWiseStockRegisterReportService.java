package com.sls.report.services;

import java.sql.Date;
import java.util.List;

import com.sls.report.dto.QualityWiseStockRegisterReportDTO;

public interface QualityWiseStockRegisterReportService {
	
	public List<QualityWiseStockRegisterReportDTO> getAllQualityWiseStockReport(Date date);

}
