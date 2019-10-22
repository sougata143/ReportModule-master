package com.sls.report.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.JuteGateEntryDtl;
import com.sls.report.repository.JuteGateEntryDtlRepository;

@Component
public class JuteGateEntryDtlComponent {
	
	@Autowired
	JuteGateEntryDtlRepository juteEntryDtlRepository;
	
	@Transactional
	public List<JuteGateEntryDtl> getAllJuteGateEntryDtl(){
		return juteEntryDtlRepository.findAll();
	}
	
	@Transactional
	public List<JuteGateEntryDtl> getJuteGateEntryDtlByHdrId(long hdrId) {
		return juteEntryDtlRepository.findByHdrId(hdrId);
	}

}
