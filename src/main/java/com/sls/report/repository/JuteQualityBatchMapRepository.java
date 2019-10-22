package com.sls.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sls.report.entity.JuteQualityBatchMap;

@Repository
public interface JuteQualityBatchMapRepository extends JpaRepository<JuteQualityBatchMap, Long> {

	JuteQualityBatchMap findByBatchId(long batchId);
	JuteQualityBatchMap findByJuteQualityId(long juteQualityId);
	
}
