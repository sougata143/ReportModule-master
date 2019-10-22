package com.sls.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity class for JUTE_QUALITY_PRICE_MASTER
 */

@Entity
@Table(name="JUTE_QUALITY_PRICE_MASTER")
public class JuteQualityPriceMaster {
	
	@Id
	@Column(name="ID", nullable = true)
	private Long id;
	
	@Column(name="ITEM_CODE", nullable = true)
	private String itemCode;
	
	@Column(name="JUTE_QUALITY", nullable = true)
	private String juteQuality;
	
	@Column(name="RATE", nullable = true)
	private Long rate;
	
	@Column(name="CURRENCY", nullable = true)
	private String currency;

	
	
	
	public String getJuteQuality() {
		return juteQuality;
	}

	public void setJuteQuality(String juteQuality) {
		this.juteQuality = juteQuality;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public JuteQualityPriceMaster(long id, String itemCode, long rate, String currency) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.rate = rate;
		this.currency = currency;
	}

	public JuteQualityPriceMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JuteQualityPriceMaster [id=" + id + ", itemCode=" + itemCode + ", rate=" + rate + ", currency="
				+ currency + "]";
	}
	
	
	

}
