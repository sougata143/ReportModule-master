package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.component.ScmIssueLineItemComponent;
import com.sls.report.component.ScmMrHdrComponent;
import com.sls.report.component.ScmMrLineItemComponent;
import com.sls.report.dto.ClosingStockTotalDTO;
import com.sls.report.dto.IssueTotalStockDTO;
import com.sls.report.dto.OpnningStockGrandTotalDTO;
import com.sls.report.dto.QualityWiseStockRegisterReportDTO;
import com.sls.report.dto.QualityWiseStockRegisterReportTotalDTO;
import com.sls.report.dto.StockRegisterDTO;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.entity.ScmMrHdr;
import com.sls.report.entity.ScmMrLineItem;
import com.sls.report.services.QualityWiseStockRegisterReportService;

/*
 * Service class for Quality Wise Stock Register Report
 */
@Service
public class QualityWiseStockRegisterReportServiceImpl implements QualityWiseStockRegisterReportService {
	
	@Autowired
	ScmMrLineItemComponent mrlineitemDao;
	
	@Autowired
	ScmIssueLineItemComponent issuelineitemDao;
	
	@Autowired
	ScmMrHdrComponent mrhdrDao;
	
	@Autowired
	JuteQualityPriceMasterComponent priceDao;
	
	@Autowired
	PhysicalStockComponent stockDao;

	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.QualityWiseStockRegisterReportService#getAllQualityWiseStockReport(java.sql.Date)
	 * 
	 * Fetching All Mr Line Items then fetching Mr Header by mr no fetched from each mr lineitem
	 * 
	 * Then if the user input date matched with jute reciept date and the status is 3 then prepare the DTO
	 * 
	 */
	@Override
	public List<QualityWiseStockRegisterReportDTO> getAllQualityWiseStockReport(Date date) {
		List<QualityWiseStockRegisterReportDTO> qualitywiseReportDTO = new ArrayList<>();
		String datestr = null;
		String year = null;
		String month = null;
		String day = null;
		String[] datestrstream = null;
		List<String> qualities = new ArrayList<>();
		try {
			List<ScmMrLineItem> mrlineitem = mrlineitemDao.getAllScmMrLineItem();
			List<ScmMrLineItem> mrlineitems = mrlineitemDao.getAllScmMrLineItem();
			datestr = String.valueOf(date);
			datestrstream = datestr.split("\\-");
			year = datestrstream[0];
//			List<ScmMrHdr> hdrs = mrhdrDao.getMrHdrByJuteReceiptDateAndStatus(date, "3");
			mrlineitem.forEach(lineitem->{
				ScmMrHdr mrhdr = mrhdrDao.getMrHdrById(lineitem.getJuteReceiveNo());
//				if(mrhdr.getSourceMrNo()==null)
				if(mrhdr.getGoodReceiptDate().equals(date)) {
					String quality = lineitem.getActualQuality();
					qualities.add(quality);
//					qualitywiseReportDTO.add(prepareQualityreportDTO(lineitem,mrlineitems,date));
					
				}
				
				/*long rcvno = lineitem.getJuteReceiveNo();
				if(hdrs.contains(rcvno)) {
					qualitywiseReportDTO.add(prepareQualityreportDTO(lineitem,mrlineitems,date));
				}*/
			});
			List<String> distquality = qualities.stream().distinct().collect(Collectors.toList());
			for(int i = 0 ; i < distquality.size() ; i++) {
				List<ScmMrLineItem> mrlineitembyquality =
						mrlineitemDao.getAllScmMrLineItemByQaulilty(distquality.get(i));
				qualitywiseReportDTO.add(prepareQualityReportDTO(mrlineitembyquality,mrlineitems,date,distquality.get(i)));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("String "+datestr);
		System.out.println("String "+datestr);
		return qualitywiseReportDTO;
	}

	
	private QualityWiseStockRegisterReportDTO prepareQualityReportDTO(List<ScmMrLineItem> mrlineitembyquality,
			List<ScmMrLineItem> mrlineitems, Date date, String qaulity) {
QualityWiseStockRegisterReportDTO qualityReportDTO = new QualityWiseStockRegisterReportDTO();
		
		float openningactualQuality = 0.0f;
		
		//preparing grand total field
		QualityWiseStockRegisterReportTotalDTO grandtotal = new QualityWiseStockRegisterReportTotalDTO();
		float openningbale = 0.0f;
		float opennigloose = 0.0f;
		float opennigweight = 0.0f;
		float opennighbale = 0.0f;
		float opennigpbale = 0.0f;
		float opennigdrum = 0.0f;
		
		float closingbale = 0.0f;
		float closingloose = 0.0f;
		float closingweight = 0.0f;
		float closinghbale = 0.0f;
		float closingpbale = 0.0f;
		float closingdrum = 0.0f;
		
		float issueingbale = 0.0f;
		float issueingloose = 0.0f;
		float issueingweight = 0.0f;
		float issueinghbale = 0.0f;
		float issueingpbale = 0.0f;
		float issueingdrum = 0.0f;
		
		List<ScmIssueLineItem> issues = 
				issuelineitemDao.getAllScmIssueLineItem();
		for(int k = 0 ; k < issues.size() ; k++) {
			String[] transid = issues.get(k).getAdditionalRequirement().split("\\^");
			PhysicalStock stock = stockDao.getPhysicalStockById(Long.parseLong(transid[0]));
			ScmMrLineItem mrline = new ScmMrLineItem();
			if(stock!=null)
				mrline = mrlineitemDao.getIssueLineItem(stock.getGoodItemSerialNo());
			
			issueingbale = issueingbale + issues.get(k).getIssueQty();
			issueingloose = issueingloose + issues.get(k).getIssueQty();
			
			
//			issueinghbale = issueinghbale +
//			issueingpbale = issueingpbale +
//			issueingdrum = issueingdrum +
			long baleweight = (long) (issueingbale * 1.5);
			long looseweight = (long) issueingloose;
			issueingweight = (issueingweight + baleweight + looseweight);
			if(stock!=null) {
				if(mrline!=null)
					issueingweight = mrline.getActualWeight();
			}
		}
		
		
		for(int j = 0 ; j < mrlineitems.size() ; j++) {
			//For fetching data to get issue fields
			/*List<ScmIssueLineItem> issues = 
								issuelineitemDao.getIssueLineItemByQuality(mrlineitems.get(j).getActualQuality());*/
			
			/*
			 * for grand total of opennig stock fields
			 */
			openningbale = openningbale + mrlineitems.get(j).getActualBale();
			opennigloose = opennigloose + mrlineitems.get(j).getActualLoose();
			opennigweight = opennigweight + mrlineitems.get(j).getActualWeight();
//			opennighbale = opennighbale +
//			opennigpbale = opennigpbale +
//			opennigdrum = opennigdrum +
			
			/*
			 * for grand total of closing stock fields
			 */
			closingbale = closingbale + mrlineitems.get(j).getActualBale();
			closingloose = closingloose + mrlineitems.get(j).getActualLoose();
			closingweight = closingweight + mrlineitems.get(j).getActualWeight();
//			closinginghbale = closinginghbale +
//			closingingpbale = closingingpbale +
//			closingingdrum = closingingdrum +
			
			/*
			 * For grand total of issue fields
			 */
			/*for(int k = 0 ; k < issues.size() ; k++) {
				issueingbale = issueingbale + issues.get(k).getIssueQty();
				issueingloose = issueingloose + issues.get(k).getIssueQty();
//				issueinghbale = issueinghbale +
//				issueingpbale = issueingpbale +
//				issueingdrum = issueingdrum +
			}
			issueingweight = issueingweight + mrlineitems.get(j).getActualWeight();*/
			
		}
		
		OpnningStockGrandTotalDTO openning = new OpnningStockGrandTotalDTO();
		openning.setBale(openningbale);
		openning.setLoose(opennigloose);
		openning.setWeight(opennigweight);
		/*openning.setDrums(opennigdrum);
		openning.setHbale(opennighbale);
		openning.setPbale(opennigpbale);*/
		
		ClosingStockTotalDTO closingtotal = new ClosingStockTotalDTO();
		closingtotal.setBale(closingbale);
		closingtotal.setLoose(closingloose);
		closingtotal.setWeight(closingweight);
		/*closingtotal.setDrums(closinginghbale);
		closingtotal.setHbale(closinginghbale);
		closingtotal.setPbale(closingingpbale);*/
		
		IssueTotalStockDTO issuetotal = new IssueTotalStockDTO();
		issuetotal.setBale(issueingbale);
		issuetotal.setLoose(issueingloose);
		issuetotal.setWeight(issueingweight);
		/*issuetotal.setDrums(issueingdrum);
		issuetotal.setHbale(issueinghbale);
		issuetotal.setPbale(issueingpbale);*/
		
		grandtotal.setClosingstockgrandtotal(closingtotal);
		grandtotal.setIssuegrandtotal(issuetotal);
		grandtotal.setOpenningstockgrandtotal(openning);
		qualityReportDTO.setGrandtotal(grandtotal);
		
		//Fetching Jute Quality Price Master by actualquality from mr lineitem to get the jutequality name
		JuteQualityPriceMaster quality = 
				priceDao.getJuteQualityPriceMasterById(Long.valueOf(qaulity));
		qualityReportDTO.setActualQuality(quality.getJuteQuality());
				
		//setting openning stock
		long openstockweight = 0l;
		long openstockbale = 0l;
		long openstockloose = 0l;
		
		long closingstockweight = 0l;
		long closingstockbale = 0l;
		long closingstockloose = 0l;
		StockRegisterDTO openningstock = new StockRegisterDTO();
		
		/*openningstock.setActualWeight(lineitem.getActualWeight());
		openningstock.setBale(lineitem.getActualBale());
//		openningstock.setDrums(drums);
//		openningstock.setHbale(hbale);
		openningstock.setLoose(lineitem.getActualLoose());
//		openningstock.setPbale(pbale);*/
		
		for(int i = 0 ; i < mrlineitembyquality.size() ; i++) {
			openstockweight = openstockweight + mrlineitembyquality.get(i).getActualWeight();
			openstockbale = openstockbale + mrlineitembyquality.get(i).getActualBale();
			openstockloose = openstockloose + mrlineitembyquality.get(i).getActualLoose();
			
			closingstockweight = closingstockweight + mrlineitembyquality.get(i).getActualWeight();
			closingstockbale = closingstockbale + mrlineitembyquality.get(i).getActualBale();
			closingstockloose = closingstockloose + mrlineitembyquality.get(i).getActualLoose();
		}
		
		openningstock.setActualWeight(openstockweight);
		openningstock.setBale(openstockbale);
//		openningstock.setDrums(drums);
//		openningstock.setHbale(hbale);
		openningstock.setLoose(openstockloose);
//		openningstock.setPbale(pbale);
		
		qualityReportDTO.setOpenningStock(openningstock);
		
		//Setting closing stock
		StockRegisterDTO closingstock = new StockRegisterDTO();
		/*closingstock.setActualWeight(lineitem.getActualWeight());
		closingstock.setBale(lineitem.getBale());
//		closingstock.setDrums(drums);
//		closingstock.setHbale(hbale);
		closingstock.setLoose(lineitem.getLoose());
//		closingstock.setPbale(pbale);*/
		
		closingstock.setActualWeight(closingstockweight);
		closingstock.setBale(closingstockbale);
//		closingstock.setDrums(drums);
//		closingstock.setHbale(hbale);
		closingstock.setLoose(closingstockloose);
//		closingstock.setPbale(pbale);
				
		qualityReportDTO.setClosingstock(closingstock);
		
		/*
		 * Fetching Issue Line Items by actual qaulity fetched from Mr Line Item
		 */
		List<ScmIssueLineItem> issuelineitems = issuelineitemDao.getIssueLineItemByQuality(qaulity);
		List<StockRegisterDTO> issuelineitemDTOs = new ArrayList<>();
		StockRegisterDTO issulineitemDTO = new StockRegisterDTO();
		
		long issuebale = 0;
		long issueloose = 0;
		long issueactualweight = 0;
		for(int i = 0 ; i < issuelineitems.size() ; i++) {
			String[] transid = issuelineitems.get(i).getAdditionalRequirement().split("\\^");
			PhysicalStock stock = stockDao.getPhysicalStockById(Long.parseLong(transid[0]));
			ScmMrLineItem mrline = mrlineitemDao.getIssueLineItem(stock.getGoodItemSerialNo());
			if(stock!=null) {
				if(stock.getConversionUnit().equalsIgnoreCase("BALE"))
					issuebale = issuebale + issuelineitems.get(i).getIssueQty();
			}
			
			if(stock!=null) {
				if(stock.getConversionUnit().equalsIgnoreCase("LOOSE"))
					issueloose = issueloose + issuelineitems.get(i).getIssueQty();
			}
			
			/*issuebale = issuebale + issuelineitems.get(i).getIssueQty();
			issueloose = issueloose + issuelineitems.get(i).getIssueQty();*/
			issueactualweight = (long) (issueactualweight + ((issuebale * 1.5) + issueloose));
		}
		
		issulineitemDTO.setActualWeight(issueactualweight);
		issulineitemDTO.setBale(issuebale);
//		issulineitemDTO.setDrums(drums);
//		issulineitemDTO.setHbale(hbale);
		issulineitemDTO.setLoose(issueloose);
//		issulineitemDTO.setPbale(pbale);
		issuelineitemDTOs.add(issulineitemDTO);
		qualityReportDTO.setIssue(issulineitemDTO);
		
		return qualityReportDTO;
	}


	/*
	 * Preparing DTO for Quality Wise Stock Report
	 */
	/*private QualityWiseStockRegisterReportDTO prepareQualityreportDTO(ScmMrLineItem lineitem,
																		List<ScmMrLineItem> mrlineitems, Date date) {
		QualityWiseStockRegisterReportDTO qualityReportDTO = new QualityWiseStockRegisterReportDTO();
		
		float openningactualQuality = 0.0f;
		
		//preparing grand total field
		QualityWiseStockRegisterReportTotalDTO grandtotal = new QualityWiseStockRegisterReportTotalDTO();
		float openningbale = 0.0f;
		float opennigloose = 0.0f;
		float opennigweight = 0.0f;
		float opennighbale = 0.0f;
		float opennigpbale = 0.0f;
		float opennigdrum = 0.0f;
		
		float closingbale = 0.0f;
		float closingloose = 0.0f;
		float closingweight = 0.0f;
		float closinghbale = 0.0f;
		float closingpbale = 0.0f;
		float closingdrum = 0.0f;
		
		float issueingbale = 0.0f;
		float issueingloose = 0.0f;
		float issueingweight = 0.0f;
		float issueinghbale = 0.0f;
		float issueingpbale = 0.0f;
		float issueingdrum = 0.0f;
		
		for(int j = 0 ; j < mrlineitems.size() ; j++) {
			//For fetching data to get issue fields
			List<ScmIssueLineItem> issues = 
								issuelineitemDao.getIssueLineItemByQuality(mrlineitems.get(j).getActualQuality());
			
			
			 * for grand total of opennig stock fields
			 
			openningbale = openningbale + mrlineitems.get(j).getActualBale();
			opennigloose = opennigloose + mrlineitems.get(j).getActualLoose();
			opennigweight = opennigweight + mrlineitems.get(j).getActualWeight();
//			opennighbale = opennighbale +
//			opennigpbale = opennigpbale +
//			opennigdrum = opennigdrum +
			
			
			 * for grand total of closing stock fields
			 
			closingbale = closingbale + mrlineitems.get(j).getActualBale();
			closingloose = closingloose + mrlineitems.get(j).getActualLoose();
			closingweight = closingweight + mrlineitems.get(j).getActualWeight();
//			closinginghbale = closinginghbale +
//			closingingpbale = closingingpbale +
//			closingingdrum = closingingdrum +
			
			
			 * For grand total of issue fields
			 
			for(int k = 0 ; k < issues.size() ; k++) {
				issueingbale = issueingbale + issues.get(k).getIssueQty();
				issueingloose = issueingloose + issues.get(k).getIssueQty();
//				issueinghbale = issueinghbale +
//				issueingpbale = issueingpbale +
//				issueingdrum = issueingdrum +
			}
			issueingweight = issueingweight + mrlineitems.get(j).getActualWeight();
			
		}
		
		OpnningStockGrandTotalDTO openning = new OpnningStockGrandTotalDTO();
		openning.setBale(openningbale);
		openning.setLoose(opennigloose);
		openning.setWeight(opennigweight);
		openning.setDrums(opennigdrum);
		openning.setHbale(opennighbale);
		openning.setPbale(opennigpbale);
		
		ClosingStockTotalDTO closingtotal = new ClosingStockTotalDTO();
		closingtotal.setBale(closingbale);
		closingtotal.setLoose(closingloose);
		closingtotal.setWeight(closingweight);
		closingtotal.setDrums(closinginghbale);
		closingtotal.setHbale(closinginghbale);
		closingtotal.setPbale(closingingpbale);
		
		IssueTotalStockDTO issuetotal = new IssueTotalStockDTO();
		issuetotal.setBale(issueingbale);
		issuetotal.setLoose(issueingloose);
		issuetotal.setWeight(issueingweight);
		issuetotal.setDrums(issueingdrum);
		issuetotal.setHbale(issueinghbale);
		issuetotal.setPbale(issueingpbale);
		
		grandtotal.setClosingstockgrandtotal(closingtotal);
		grandtotal.setIssuegrandtotal(issuetotal);
		grandtotal.setOpenningstockgrandtotal(openning);
		qualityReportDTO.setGrandtotal(grandtotal);
		
		//Fetching Jute Quality Price Master by actualquality from mr lineitem to get the jutequality name
		JuteQualityPriceMaster quality = 
				priceDao.getJuteQualityPriceMasterById(Long.valueOf(lineitem.getActualQuality()));
		qualityReportDTO.setActualQuality(quality.getJuteQuality());
				
		//setting openning stock
		StockRegisterDTO openningstock = new StockRegisterDTO();
		openningstock.setActualWeight(lineitem.getActualWeight());
		openningstock.setBale(lineitem.getActualBale());
//		openningstock.setDrums(drums);
//		openningstock.setHbale(hbale);
		openningstock.setLoose(lineitem.getActualLoose());
//		openningstock.setPbale(pbale);
		
		qualityReportDTO.setOpenningStock(openningstock);
		
		//Setting closing stock
		StockRegisterDTO closingstock = new StockRegisterDTO();
		closingstock.setActualWeight(lineitem.getActualWeight());
		closingstock.setBale(lineitem.getBale());
//		closingstock.setDrums(drums);
//		closingstock.setHbale(hbale);
		closingstock.setLoose(lineitem.getLoose());
//		closingstock.setPbale(pbale);
				
		qualityReportDTO.setClosingstock(closingstock);
		
		
		 * Fetching Issue Line Items by actual qaulity fetched from Mr Line Item
		 
		List<ScmIssueLineItem> issuelineitems = issuelineitemDao.getIssueLineItemByQuality(lineitem.getActualQuality());
		List<StockRegisterDTO> issuelineitemDTOs = new ArrayList<>();
		StockRegisterDTO issulineitemDTO = new StockRegisterDTO();
		
		long issuebale = 0;
		long issueloose = 0;
		for(int i = 0 ; i < issuelineitems.size() ; i++) {
			issuebale = issuebale + issuelineitems.get(i).getIssueQty();
			issueloose = issueloose + issuelineitems.get(i).getIssueQty();
		}
		issulineitemDTO.setActualWeight(lineitem.getActualWeight());
		issulineitemDTO.setBale(issuebale);
//		issulineitemDTO.setDrums(drums);
//		issulineitemDTO.setHbale(hbale);
		issulineitemDTO.setLoose(issueloose);
//		issulineitemDTO.setPbale(pbale);
		issuelineitemDTOs.add(issulineitemDTO);
		qualityReportDTO.setIssue(issulineitemDTO);
		
		return qualityReportDTO;
	}*/

}
