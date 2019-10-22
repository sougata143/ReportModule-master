package com.sls.report.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.report.dto.GodownWiseStockRegisterReportDTOss;
import com.sls.report.dto.GodownWiseStockRegistersDTO;
import com.sls.report.services.serviceImpl.GodownWiseStockRegisterServiceImpl;

@RestController
@RequestMapping("/")
public class GodownWiseStockRegisterReportController {
	
	@Autowired
	GodownWiseStockRegisterServiceImpl godownreportService;
	
	@GetMapping("/godownwisestockregisterreport/{modon}")
	public List<GodownWiseStockRegisterReportDTOss> getGodownWoseStockRegisterReport(@PathVariable("modon") Date modon){
		return godownreportService.godownWiseStockRegisterReport(modon);
	}

}
