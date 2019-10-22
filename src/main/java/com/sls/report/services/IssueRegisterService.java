package com.sls.report.services;

import java.sql.Date;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.sls.report.dto.IssueRegisterDTO;

public interface IssueRegisterService {
	
	List<IssueRegisterDTO> createReport(Date date);

}
