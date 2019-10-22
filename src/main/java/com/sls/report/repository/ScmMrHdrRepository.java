package com.sls.report.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.report.entity.ScmMrHdr;

public interface ScmMrHdrRepository extends JpaRepository<ScmMrHdr, Long> {
	
	List<ScmMrHdr> findByChalanDate(Date date);
	List<ScmMrHdr> findByContractDate(Date date);
	List<ScmMrHdr> findByGoodReceiptDate(Date date);
	List<ScmMrHdr> findByGoodReceiptDateAndStatus(Date date, String status);
}
