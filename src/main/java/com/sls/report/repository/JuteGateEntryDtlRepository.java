package com.sls.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sls.report.entity.JuteGateEntryDtl;

public interface JuteGateEntryDtlRepository extends JpaRepository<JuteGateEntryDtl, Long> {

	List<JuteGateEntryDtl> findByHdrId(long hdrId);
	
}
