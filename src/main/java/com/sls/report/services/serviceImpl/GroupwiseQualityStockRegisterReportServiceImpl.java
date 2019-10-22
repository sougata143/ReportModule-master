package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.component.ScmIssueLineItemComponent;
import com.sls.report.component.ScmMrLineItemComponent;
import com.sls.report.dto.GroupwiseQualityStockRegisterGeneratedPdfReportDTO;
import com.sls.report.dto.GroupwiseQualityStockRegisterGeneratedReportTotalDTO;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.entity.ScmMrLineItem;
import com.sls.report.services.GroupwiseQualityStockRegisterReportService;


/*
 * service class for Groupwise Qaulity Stock Register Report 
 */

@Service
public class GroupwiseQualityStockRegisterReportServiceImpl implements GroupwiseQualityStockRegisterReportService {

	@Autowired
	PhysicalStockComponent physicalstockDao;
	
	@Autowired
	JuteQualityPriceMasterComponent pricemasterDao;
	
	@Autowired
	ScmIssueLineItemComponent lineitemDao;
	
	@Autowired
	ScmMrLineItemComponent mrlineitemDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.GroupwiseQualityStockRegisterReportService#getGroupwiseQualityStockRegisterReport(java.sql.Date)
	 * 
	 * Fetching physical stock for preparing groupwise quality stock register report
	 * 
	 * @Param
	 * Date modon
	 * 
	 * @RequestMethod  GET
	 * 
	 */
	@Override
	public List<GroupwiseQualityStockRegisterGeneratedPdfReportDTO> 
													getGroupwiseQualityStockRegisterReport(Date modon) {
		 List<GroupwiseQualityStockRegisterGeneratedPdfReportDTO> physicalstockDTOs = new ArrayList<>();
		
		 /*
		  * Fetching All PhysicalStocks by groupcode (for fetching only jute records) and modification date
		  */
		 try {
//			List<PhysicalStock> physicalstocks =  physicalstockDao.getByModifiedDate(modon);
//			List<PhysicalStock> physicalstocks =  physicalstockDao.getAllPhysicalStockByGrpCode("999");
			 
			List<PhysicalStock> physicalstocks =  physicalstockDao.getAllPhysicalStockByGrpCodeAndModOn("999", modon);
			List<PhysicalStock> allPhysicalstocks =  physicalstockDao.getAllPhysicalStock();
			physicalstocks.forEach(physicalstock->{
				physicalstockDTOs.add(preparePhysicalStockDTO(physicalstock,physicalstocks, modon));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return physicalstockDTOs;
	}
	
	
	/*
	 * Preparing DTO for Groupwise Quality Stock Register Report
	 */
	private GroupwiseQualityStockRegisterGeneratedPdfReportDTO preparePhysicalStockDTO(PhysicalStock ps,
			List<PhysicalStock> allPhysicalstocks, Date modon) {
		GroupwiseQualityStockRegisterGeneratedPdfReportDTO physicalstockDTO = 
		new GroupwiseQualityStockRegisterGeneratedPdfReportDTO();
		String qualitycode = ps.getQualityCode();
		
		/*
		 * Fetching JUTE_QUALITY_PRICE_MASTER for getting the jute quality
		 */
		JuteQualityPriceMaster qualityprice = new JuteQualityPriceMaster();
		if(ps.getQualityCode()!=null) {
		qualityprice = 
		pricemasterDao.getJuteQualityPriceMasterById(Long.valueOf(qualitycode));
		}
		
		
		/*
		 * fetching issue lineitems by lineitem id retained from physicalstock
		 */
		List<ScmIssueLineItem> issuelineitem = lineitemDao.getLineItemsByItem(ps.getItemId());
		long issue = 0;
		if(!issuelineitem.isEmpty()) {
			
			for(int i = 0 ; i < issuelineitem.size() ; i++) {
					String[] tranid = issuelineitem.get(i).getAdditionalRequirement().split("\\^");
					
					if(tranid[0].equals(String.valueOf(ps.getId()))) {
						if(ps.getConversionUnit().equalsIgnoreCase("BALE")) {
							ScmMrLineItem mrline = mrlineitemDao.getIssueLineItem(ps.getGoodItemSerialNo());
							Long avg = mrline.getActualWeight()/mrline.getActualBale();
							issue = issuelineitem.get(i).getIssueQty()*avg;
						}else if(ps.getConversionUnit().equalsIgnoreCase("LOOSE")) {
							issue = issuelineitem.get(i).getIssueQty();
						}
//						issue = issuelineitem.get(i).getIssueQty();
					}
					physicalstockDTO.setIssueWt(String.valueOf(issue));
			}
		
		}
		
		GroupwiseQualityStockRegisterGeneratedReportTotalDTO grandtotal =
														new GroupwiseQualityStockRegisterGeneratedReportTotalDTO();
		float oppenningWt = 0.0f;
		float receiptWt = 0.0f;
		float closingWt = 0.0f;
		/*
		 * Getting Grand Total
		 */
		for(int j = 0 ; j < allPhysicalstocks.size() ; j++) {
			oppenningWt = oppenningWt + allPhysicalstocks.get(j).getTotalStock();
			receiptWt = receiptWt + allPhysicalstocks.get(j).getNewStock();
			closingWt = closingWt + allPhysicalstocks.get(j).getStockInHand();
		}
		
		grandtotal.setClosingWt(String.valueOf(closingWt));
		//grandtotal.setIssueWt(issueWt);
		grandtotal.setOppenningWt(String.valueOf(oppenningWt));
		grandtotal.setReceiptWt(String.valueOf(receiptWt));
		
		physicalstockDTO.setGrandtotal(grandtotal);
		
		String[] modons1 = String.valueOf(modon).split("\\-");
		String modons = modons1[0]+"-"+modons1[1]+"-"+String.valueOf(Long.parseLong(modons1[2])-1);
		Date modon1 = Date.valueOf(modons);
		List<PhysicalStock> prevstock = physicalstockDao.getPhysicalStockByQualityAndModon(ps.getQualityCode(),modon1);
		Float opnstock = 0.0F;
		for(int x = 0 ; x < prevstock.size() ; x++) {
			opnstock = opnstock + prevstock.get(x).getStockInHand();
		}
		physicalstockDTO.setClosingWt(String.valueOf(ps.getStockInHand()));
		physicalstockDTO.setOppenningWt(String.valueOf(ps.getTotalStock()));
//		physicalstockDTO.setOppenningWt(String.valueOf(opnstock));
		physicalstockDTO.setQuality(qualityprice.getJuteQuality());
		physicalstockDTO.setReceiptWt(String.valueOf(ps.getNewStock()));
		
		return physicalstockDTO;
	}
}
