package com.sls.report.services;

import java.sql.Date;
import java.util.List;

import com.sls.report.dto.StdBatchReportDTO;

public interface StdBatchReportService {
	
	public List<StdBatchReportDTO> getStdBatchReport(Date date);

}
