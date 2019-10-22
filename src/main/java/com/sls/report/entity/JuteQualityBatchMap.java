package com.sls.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JUTE_QUALITY_BATCH_MAP")
public class JuteQualityBatchMap {
	
	@Id
	@Column(name="MAP_ID", nullable = true)
	private Long mapId;
	
	@Column(name="JUTE_QUALITY_ID", nullable = true)
	private Long juteQualityId;
	
	@Column(name="BATCH_ID", nullable = true)
	private Long batchId;

	public long getMapId() {
		return mapId;
	}

	public void setMapId(long mapId) {
		this.mapId = mapId;
	}

	public long getJuteQualityId() {
		return juteQualityId;
	}

	public void setJuteQualityId(long juteQualityId) {
		this.juteQualityId = juteQualityId;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "JuteQualityBatchMap [mapId=" + mapId + ", juteQualityId=" + juteQualityId + ", batchId=" + batchId
				+ "]";
	}

	public JuteQualityBatchMap(long mapId, long juteQualityId, long batchId) {
		super();
		this.mapId = mapId;
		this.juteQualityId = juteQualityId;
		this.batchId = batchId;
	}

	public JuteQualityBatchMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
