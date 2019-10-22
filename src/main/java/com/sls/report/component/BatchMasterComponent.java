package com.sls.report.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.BatchMaster;
import com.sls.report.repository.BatchMasterRepository;

@Component
public class BatchMasterComponent {
	
	@Autowired
	BatchMasterRepository batchRepository;
	
	@Transactional
	public List<BatchMaster> getAllBatch(){
		return batchRepository.findAll();
	}
	
	@Transactional
	public BatchMaster getBatchById(long id) {
		return batchRepository.findOne(id);
	}

}
