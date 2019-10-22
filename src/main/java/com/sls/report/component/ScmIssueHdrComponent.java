package com.sls.report.component;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.ScmIssueHdr;
import com.sls.report.repository.ScmIssueHdrRepository;

@Component
public class ScmIssueHdrComponent {
	
	@Autowired
	ScmIssueHdrRepository issuehdrRepository;
	
	@Transactional
	public List<ScmIssueHdr> getAllIssueHdr(){
		return issuehdrRepository.findAll();
	}
	
	@Transactional
	public List<ScmIssueHdr> getScmIssuHdrByCreateDate(Date date){
		return issuehdrRepository.findByCreateDate(date);
	}
	
	@Transactional
	public List<ScmIssueHdr> getScmIssuHdrByIssueDate(Date date){
		return issuehdrRepository.findByIssueDate(date);
	}
	
	@Transactional
	public List<ScmIssueHdr> getScmIssuHdrByModOn(Date date){
		return issuehdrRepository.findByLastModifiedDate(date);
	}
	
	@Transactional
	public ScmIssueHdr getScmIssuHdrById(String issueNo){
		return issuehdrRepository.findOne(Long.valueOf(issueNo));
	}

}
