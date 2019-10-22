package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.JuteRateReportDTO;
import com.sls.report.services.serviceImpl.JuteRateReportServiceImpl;

@RestController
@RequestMapping("/")
public class JuteRateReportController {
	
	@Autowired
	JuteRateReportServiceImpl juterateService;
	
	@GetMapping("/juteratereport/{date}")
	public List<JuteRateReportDTO> getAllJyteRate(@PathVariable("date") Date date){
		return juterateService.getJuteRateReport();
	}
	
}
