package com.sls.report.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.repository.ScmIssueLineItemRepository;

/*
 * Component Class for ScmIssueLineItem
 */

@Component
public class ScmIssueLineItemComponent {
	
	@Autowired
	ScmIssueLineItemRepository issueLineItemRepository;
	
	@Transactional
	public List<ScmIssueLineItem> getAllScmIssueLineItem(){
		return issueLineItemRepository.findAll();
	}
	
	@Transactional
	public ScmIssueLineItem getIssueLineItem(String id) {
		return issueLineItemRepository.findOne(id);
	}
	
	@Transactional
	public List<ScmIssueLineItem> getLineItemsByItem(String itemCode){
		return issueLineItemRepository.findByItemCode(itemCode);
	}
	
	@Transactional
	public List<ScmIssueLineItem> getIssueLineItemByQuality(String quality){
		return issueLineItemRepository.findByQuality(quality);
	}
	
	@Transactional
	public List<ScmIssueLineItem> getIssueLineItemByIssueNo(String IssueNo){
		return issueLineItemRepository.findByIssueNo(IssueNo);
	}
	
	@Transactional
	public List<ScmIssueLineItem> getIssueLineItemByIssueNoAndGroupCode(String IssueNo, String groupCode){
		return issueLineItemRepository.findByIssueNoAndGroupCode(IssueNo,groupCode);
	}
	
	@Transactional
	public List<ScmIssueLineItem> getIssueLineItemByGroupCode(String grpCode){
		return issueLineItemRepository.findByGroupCode(grpCode);
	}

}
