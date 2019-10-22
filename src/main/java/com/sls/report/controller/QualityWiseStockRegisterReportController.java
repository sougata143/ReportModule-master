package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.QualityWiseStockRegisterReportDTO;
import com.sls.report.services.serviceImpl.QualityWiseStockRegisterReportServiceImpl;

@RestController
@RequestMapping("/")
public class QualityWiseStockRegisterReportController {
	
	@Autowired
	QualityWiseStockRegisterReportServiceImpl qualityReportService;
	
	@GetMapping("getqualitywisestockreport/{date}")
	public List<QualityWiseStockRegisterReportDTO> getAllQualityWiseStockReport(@PathVariable("date") Date date){
		return qualityReportService.getAllQualityWiseStockReport(date);
	}

}
