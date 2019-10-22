package com.sls.report.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.report.entity.ScmIssueHdr;

public interface ScmIssueHdrRepository extends JpaRepository<ScmIssueHdr, Long>{
	
	List<ScmIssueHdr> findByCreateDate(Date date);
	List<ScmIssueHdr> findByIssueDate(Date date);
	List<ScmIssueHdr> findByLastModifiedDate(Date date);

}
