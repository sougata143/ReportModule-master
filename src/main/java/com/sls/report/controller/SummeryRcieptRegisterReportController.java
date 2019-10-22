package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.SummaryRecieptRegisterReportDTO;
import com.sls.report.services.serviceImpl.SummaryRecieptRegisterReposrtServiceImpl;

@RestController
@RequestMapping("/")
public class SummeryRcieptRegisterReportController {
	
	@Autowired
	SummaryRecieptRegisterReposrtServiceImpl summeryRecieptRegisterReportService;
	
	@GetMapping("summeryreport/{date}")
	public List<SummaryRecieptRegisterReportDTO> getSummerReport(@PathVariable("date") Date date){
		return summeryRecieptRegisterReportService.getSummaryRecieptRegisterReport(date);
	}

}
