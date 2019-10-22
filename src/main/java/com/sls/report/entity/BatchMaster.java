package com.sls.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BATCH_MASTER")
public class BatchMaster {
	
	@Id
	@Column(name="BATCH_ID", nullable = true)
	private Long batchId;
	
	@Column(name="BATCH_NAME", nullable = true)
	private String batchName;
	
	@Column(name="PERCENTAGE", nullable = true)
	private String percentage;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "BatchMaster [batchId=" + batchId + ", batchName=" + batchName + ", percentage=" + percentage + "]";
	}

	public BatchMaster(long batchId, String batchName, String percentage) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.percentage = percentage;
	}

	public BatchMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
