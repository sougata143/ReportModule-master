package com.sls.report.component;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.ScmMrHdr;
import com.sls.report.repository.ScmMrHdrRepository;

@Component
public class ScmMrHdrComponent {
	
	@Autowired
	ScmMrHdrRepository mrhdrRepo;
	
	@Transactional
	public List<ScmMrHdr> getAllMrHdr(){
		return mrhdrRepo.findAll();
	}
	
	@Transactional
	public ScmMrHdr getMrHdrById(long id) {
		return mrhdrRepo.findOne(id);
	}
	
	@Transactional
	public List<ScmMrHdr> getMrHdrByChallanDate(Date date){
		return mrhdrRepo.findByChalanDate(date);
	}
	
	@Transactional
	public List<ScmMrHdr> getMrHdrByContractDate(Date date){
		return mrhdrRepo.findByContractDate(date);
	}
	
	@Transactional
	public List<ScmMrHdr> getMrHdrByJuteReceiptDate(Date date){
		return mrhdrRepo.findByGoodReceiptDate(date);
	}
	
	@Transactional
	public List<ScmMrHdr> getMrHdrByJuteReceiptDateAndStatus(Date date, String status){
		return mrhdrRepo.findByGoodReceiptDateAndStatus(date, status);
	}

}
