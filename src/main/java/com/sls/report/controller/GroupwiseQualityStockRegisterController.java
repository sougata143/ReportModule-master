package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.GroupwiseQualityStockRegisterGeneratedPdfReportDTO;
import com.sls.report.services.serviceImpl.GroupwiseQualityStockRegisterReportServiceImpl;


/*
 * Controller class for PhysicalStock
 */

@RestController
@RequestMapping("/")
public class GroupwiseQualityStockRegisterController {
	
	@Autowired
	GroupwiseQualityStockRegisterReportServiceImpl physicalstockService;
	
	@GetMapping("/GroupwiseQualityStockRegister/{modon}")
	public List<GroupwiseQualityStockRegisterGeneratedPdfReportDTO> getPhysicalStock(@PathVariable("modon") Date modon){
		
		return physicalstockService.getGroupwiseQualityStockRegisterReport(modon);
		
	}
	
}
