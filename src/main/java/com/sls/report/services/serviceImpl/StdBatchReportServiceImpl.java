package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.BatchMasterComponent;
import com.sls.report.component.JuteQualityBatchMasterComponent;
import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.component.ScmIssueHdrComponent;
import com.sls.report.component.ScmIssueLineItemComponent;
import com.sls.report.dto.StdBatchReportDTO;
import com.sls.report.entity.BatchMaster;
import com.sls.report.entity.JuteQualityBatchMap;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.entity.ScmIssueHdr;
import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.services.StdBatchReportService;

@Service
public class StdBatchReportServiceImpl implements StdBatchReportService {

	@Autowired
	JuteQualityPriceMasterComponent pricemasterDao;
	
	@Autowired
	ScmIssueLineItemComponent issueDao;
	
	@Autowired
	ScmIssueHdrComponent issuehdrDao;
	
	@Autowired
	BatchMasterComponent batchDao;
	
	@Autowired
	JuteQualityBatchMasterComponent batchmapDao;
	
	@Autowired
	PhysicalStockComponent stockDao;
	
	@Override
	public List<StdBatchReportDTO> getStdBatchReport(Date date) {
		List<StdBatchReportDTO> stdBatchReport = new ArrayList<>();
		
		try {
			List<JuteQualityPriceMaster> prices = pricemasterDao.getAllJuteQualityPriceMaster();
			prices.forEach(price->{
				stdBatchReport.add(prepareStdBatchReportDTO(price, date));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return stdBatchReport;
	}

	private StdBatchReportDTO prepareStdBatchReportDTO(JuteQualityPriceMaster price, Date date) {
		StdBatchReportDTO batchreport = new StdBatchReportDTO();
		List<ScmIssueLineItem> issuelineitems = issueDao.getIssueLineItemByQuality(String.valueOf(price.getId()));
		long stdissue = 0;
		long mtdIssue = 0;
		long stdissuebale = 0;
		for(int i = 0 ; i < issuelineitems.size(); i++) {
			ScmIssueHdr issuehdr = issuehdrDao.getScmIssuHdrById(issuelineitems.get(i).getIssueNo());
			String[] tranid = issuelineitems.get(i).getAdditionalRequirement().split("\\^");
			PhysicalStock physicalstock = stockDao.getPhysicalStockById(Long.parseLong(tranid[0]));
			if(physicalstock.getConversionUnit().equals("BALE")) {
				stdissuebale = stdissuebale + physicalstock.getBaleStockInHand();
			}
			stdissue = stdissue+issuelineitems.get(i).getIssueQty();
			if(issuehdr.getIssueDate().equals(date)) {
				mtdIssue = mtdIssue + issuelineitems.get(i).getIssueQty();
			}
		}
		
		JuteQualityBatchMap batchmap = new JuteQualityBatchMap();
		BatchMaster batch = new BatchMaster();
		
		batchmap = batchmapDao.getByJuteQuality(price.getId());
		batch = batchDao.getBatchById(batchmap.getBatchId());
		
		batchreport.setActualIssueQnt(stdissue);
		batchreport.setStdIssueBale(stdissuebale);
		batchreport.setBatch(batch.getPercentage());
		batchreport.setCodeDesc(price.getJuteQuality());
//		batchreport.setDeg(deg);
		batchreport.setIssuewisebatch(null);
		batchreport.setJuteCode(price.getId());
		batchreport.setMonthActIssue(mtdIssue);
		batchreport.setMonthDiff(mtdIssue-stdissue);
//		batchreport.setMonthStdIssue(monthStdIssue);
//		batchreport.setQnt(qnt);
		batchreport.setStdIssue(stdissue);
		batchreport.setStdIssueQnt(null);
		
		return batchreport;
	}

}
