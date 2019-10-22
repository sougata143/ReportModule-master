package com.sls.report.dto;

public class StdBatchReportDTO {
	
	private long juteCode;
	private String codeDesc;
	private String batch;
	private long stdIssue;
	private String stdIssueQnt;
	private long actualIssueQnt;
	private String qnt;
	private String issuewisebatch;
	private long monthStdIssue;
	private long monthActIssue;
	private long monthDiff;
	private long deg;
	private long stdIssueBale;
	
	
	
	public long getStdIssueBale() {
		return stdIssueBale;
	}
	public void setStdIssueBale(long stdIssueBale) {
		this.stdIssueBale = stdIssueBale;
	}
	public long getJuteCode() {
		return juteCode;
	}
	public void setJuteCode(long juteCode) {
		this.juteCode = juteCode;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public long getStdIssue() {
		return stdIssue;
	}
	public void setStdIssue(long stdIssue) {
		this.stdIssue = stdIssue;
	}
	public String getStdIssueQnt() {
		return stdIssueQnt;
	}
	public void setStdIssueQnt(String string) {
		this.stdIssueQnt = string;
	}
	public long getActualIssueQnt() {
		return actualIssueQnt;
	}
	public void setActualIssueQnt(long actualIssueQnt) {
		this.actualIssueQnt = actualIssueQnt;
	}
	public String getQnt() {
		return qnt;
	}
	public void setQnt(String qnt) {
		this.qnt = qnt;
	}
	public String getIssuewisebatch() {
		return issuewisebatch;
	}
	public void setIssuewisebatch(String issuewisebatch) {
		this.issuewisebatch = issuewisebatch;
	}
	public long getMonthStdIssue() {
		return monthStdIssue;
	}
	public void setMonthStdIssue(long monthStdIssue) {
		this.monthStdIssue = monthStdIssue;
	}
	public long getMonthActIssue() {
		return monthActIssue;
	}
	public void setMonthActIssue(long monthActIssue) {
		this.monthActIssue = monthActIssue;
	}
	public long getMonthDiff() {
		return monthDiff;
	}
	public void setMonthDiff(long monthDiff) {
		this.monthDiff = monthDiff;
	}
	public long getDeg() {
		return deg;
	}
	public void setDeg(long deg) {
		this.deg = deg;
	}
	@Override
	public String toString() {
		return "StdBatchReportDTO [juteCode=" + juteCode + ", codeDesc=" + codeDesc + ", batch=" + batch + ", stdIssue="
				+ stdIssue + ", stdIssueQnt=" + stdIssueQnt + ", actualIssueQnt=" + actualIssueQnt + ", qnt=" + qnt
				+ ", issuewisebatch=" + issuewisebatch + ", monthStdIssue=" + monthStdIssue + ", monthActIssue="
				+ monthActIssue + ", monthDiff=" + monthDiff + ", deg=" + deg + "]";
	}
	public StdBatchReportDTO(long juteCode, String codeDesc, String batch, long stdIssue, String stdIssueQnt,
			long actualIssueQnt, String qnt, String issuewisebatch, long monthStdIssue, long monthActIssue,
			long monthDiff, long deg) {
		super();
		this.juteCode = juteCode;
		this.codeDesc = codeDesc;
		this.batch = batch;
		this.stdIssue = stdIssue;
		this.stdIssueQnt = stdIssueQnt;
		this.actualIssueQnt = actualIssueQnt;
		this.qnt = qnt;
		this.issuewisebatch = issuewisebatch;
		this.monthStdIssue = monthStdIssue;
		this.monthActIssue = monthActIssue;
		this.monthDiff = monthDiff;
		this.deg = deg;
	}
	public StdBatchReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
