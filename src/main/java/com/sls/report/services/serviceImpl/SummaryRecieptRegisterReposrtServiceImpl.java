package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.ScmMrHdrComponent;
import com.sls.report.component.ScmMrLineItemComponent;
import com.sls.report.dto.SummaryRecieptRegisterReportDTO;
import com.sls.report.dto.SummaryReportGrandTotalDTO;
import com.sls.report.dto.SummaryReportLineItemDTO;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.ScmMrHdr;
import com.sls.report.entity.ScmMrLineItem;
import com.sls.report.services.SummeryRecieptRegisterReportService;

/*
 * Service class for Summary Reciept Register Report
 */
@Service
public class SummaryRecieptRegisterReposrtServiceImpl implements SummeryRecieptRegisterReportService{
	
	@Autowired
	ScmMrLineItemComponent mrlineitemDao;
	
	@Autowired
	ScmMrHdrComponent issuemrDao;
	
	@Autowired
	JuteQualityPriceMasterComponent priceDao;

	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.SummeryRecieptRegisterReportService#getSummaryRecieptRegisterReport(java.sql.Date)
	 * 
	 * Fetching All Mr Line Items and then fetching Mr Hdr for each lineitem and matching whether the Jute Reciept Date
	 * 
	 * matches with the user input date and the status is 3. If any match founds then prepare the DTO
	 * 
	 */
	@Override
	public List<SummaryRecieptRegisterReportDTO> getSummaryRecieptRegisterReport(Date date) {
		List<SummaryRecieptRegisterReportDTO> sumaryreport = new ArrayList<>();
		
		try {
			List<ScmMrLineItem> mrlineitems = mrlineitemDao.getAllScmMrLineItem();
			List<ScmMrHdr> mrhdrs = issuemrDao.getMrHdrByJuteReceiptDateAndStatus(date, "3");
			mrhdrs.forEach(mrhdr->{
				if(mrhdr.getGoodReceiptDate().equals(date)) {
					if(mrhdr.getSourceMrNo()==null)
					sumaryreport.add(prepareIssueRegiDTO(mrhdr,mrlineitems));
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sumaryreport;
	}


	/*
	 * Preparing DTO for the Summary Reciept Register Report
	 */
	private SummaryRecieptRegisterReportDTO prepareIssueRegiDTO(ScmMrHdr issuehdr,
			List<ScmMrLineItem> mrlineitems) {
		SummaryRecieptRegisterReportDTO summaryreportDTO =new SummaryRecieptRegisterReportDTO();
		//Fetching Mr Line Item by issueno
		List<ScmMrLineItem> lineitem = mrlineitemDao.getScmMrLineItemByIdByRcvNo(issuehdr.getId());
		
		SummaryReportLineItemDTO linitem = new SummaryReportLineItemDTO();
		long balegrandtotal = 0;
		long loosegrandtotal = 0;
		long weightgrandtotal = 0;
		String quantityunit = null;
		
		/*
		 * preparing grandtotal fields
		 */
		for(int j = 0 ; j < mrlineitems.size() ; j++) {
			balegrandtotal = balegrandtotal + mrlineitems.get(j).getActualBale();
			loosegrandtotal = loosegrandtotal + mrlineitems.get(j).getActualLoose();
			weightgrandtotal = weightgrandtotal + mrlineitems.get(j).getActualWeight();
			quantityunit = mrlineitems.get(j).getQuantityUnit();
		}
		SummaryReportGrandTotalDTO summarygrandtotal = new SummaryReportGrandTotalDTO();
		summarygrandtotal.setBales(balegrandtotal);
		summarygrandtotal.setLoose(loosegrandtotal);
		summarygrandtotal.setQuantityUnit(quantityunit);
		summarygrandtotal.setWeight(weightgrandtotal);
		
		summaryreportDTO.setSummarygrandtotal(summarygrandtotal);
		
		/*
		 * fetching jute quality price master by lineitem's actual quality for getting quality name
		 * 
		 * checking whether bale is empty or loose
		 * 
		 * If bale is not empty then set conversion as BALE and if loose is not empty the set conversion type as LOOSE
		 * 
		 */
		List<SummaryReportLineItemDTO> lineitems = new ArrayList<>();
		for(int i = 0 ; i < lineitem.size() ; i++) {
			SummaryReportLineItemDTO lineDTO = new SummaryReportLineItemDTO();
			JuteQualityPriceMaster quality = 
					priceDao.getJuteQualityPriceMasterById(Long.valueOf(lineitem.get(i).getActualQuality()));
			lineDTO.setQuality(quality.getJuteQuality());
			
			if(lineitem.get(i).getActualWeight()!=0)
				lineDTO.setWeight(lineitem.get(i).getActualWeight());
				
			if(lineitem.get(i).getActualBale()!=0)
				lineDTO.setBales(lineitem.get(i).getActualBale());
				
			
			lineDTO.setLoose(lineitem.get(i).getActualLoose());
			lineDTO.setQuantityUnit(lineitem.get(i).getQuantityUnit());
			
			if(lineitem.get(i).getWarehouseNo()!=null)
				lineDTO.setGodownNo(lineitem.get(i).getWarehouseNo());
				
			
			if(lineitem.get(i).getBale()!=0) {
				lineDTO.setConversionType("BALE");
			}else if(lineitem.get(i).getLoose()!=0)
				lineDTO.setConversionType("LOOSE");
			
			lineitems.add(lineDTO);
		}
		summaryreportDTO.setLineitems(lineitems);
		summaryreportDTO.setDate(issuehdr.getChalanDate());
		summaryreportDTO.setRecptNo(issuehdr.getChalanNo());
		
		return summaryreportDTO;
	}

}
