package com.sls.report.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.repository.JuteQualityPriceMasterRepository;

/*
 * component class for JuteQualityPriceMaster
 */

@Component
public class JuteQualityPriceMasterComponent {
	
	@Autowired
	JuteQualityPriceMasterRepository jutePriceMasterRepository;
	
	@Transactional
	public List<JuteQualityPriceMaster> getAllJuteQualityPriceMaster(){
		return jutePriceMasterRepository.findAll();
	}
	
	@Transactional
	public JuteQualityPriceMaster getJuteQualityPriceMasterById(long id) {
		return jutePriceMasterRepository.findById(id);
	}
	
	@Transactional
	public List<JuteQualityPriceMaster> getPriceMasterByItem(String itemCode){
		return jutePriceMasterRepository.findByItemCode(itemCode);
	}
	
	@Transactional
	public JuteQualityPriceMaster getPriceByQuality(String quality) {
		return jutePriceMasterRepository.findByJuteQuality(quality);
	}

}
