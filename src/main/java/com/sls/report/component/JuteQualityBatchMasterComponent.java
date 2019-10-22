package com.sls.report.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.JuteQualityBatchMap;
import com.sls.report.repository.JuteQualityBatchMapRepository;

@Component
public class JuteQualityBatchMasterComponent {
	
	@Autowired
	JuteQualityBatchMapRepository jutebatchmapRepository;
	
	@Transactional
	public List<JuteQualityBatchMap> getAllBatchMap(){
		return jutebatchmapRepository.findAll();
	}
	
	@Transactional
	public JuteQualityBatchMap getByBatchId(long id) {
		return jutebatchmapRepository.findByBatchId(id);
	}
	
	@Transactional
	public JuteQualityBatchMap getByJuteQuality(long id) {
		return jutebatchmapRepository.findByJuteQualityId(id);
	}

}
