package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.StdBatchReportDTO;
import com.sls.report.services.serviceImpl.StdBatchReportServiceImpl;

@RestController
@RequestMapping("/")
public class StdBatchReportController {
	
	@Autowired
	StdBatchReportServiceImpl stdBatchReportService;
	
	@GetMapping("/getstdbatchreport/{date}")
	public List<StdBatchReportDTO> getStdBatchReport(@PathVariable("date") Date date){
		return stdBatchReportService.getStdBatchReport(date);
	}

}
