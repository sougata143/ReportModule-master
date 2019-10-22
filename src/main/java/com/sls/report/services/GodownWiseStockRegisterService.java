package com.sls.report.services;

import java.sql.Date;
import java.util.List;

import com.sls.report.dto.GodownWiseStockRegisterReportDTOss;

public interface GodownWiseStockRegisterService {

	public List<GodownWiseStockRegisterReportDTOss> godownWiseStockRegisterReport(Date modon);
	
}
