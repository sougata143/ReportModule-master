package com.sls.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.report.entity.JuteQualityPriceMaster;

public interface JuteQualityPriceMasterRepository extends JpaRepository<JuteQualityPriceMaster, Long> {

	JuteQualityPriceMaster findById(long id);
	List<JuteQualityPriceMaster> findByItemCode(String itemCode);
	JuteQualityPriceMaster findByJuteQuality(String quality);
	
}
