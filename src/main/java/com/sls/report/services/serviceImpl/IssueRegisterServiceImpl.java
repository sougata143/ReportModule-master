package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.component.ScmIssueHdrComponent;
import com.sls.report.component.ScmIssueLineItemComponent;
import com.sls.report.component.ScmMrLineItemComponent;
import com.sls.report.dto.IssueRegisterDTO;
import com.sls.report.dto.IssueRegisterLineitemDTO;
import com.sls.report.dto.IssueReportGrandTotalDTO;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.entity.ScmIssueHdr;
import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.entity.ScmMrLineItem;
import com.sls.report.services.IssueRegisterService;


/*
 *Service For IssueRegisterReport 
 */

@Service
public class IssueRegisterServiceImpl implements IssueRegisterService {
	
	@Autowired
	ScmIssueHdrComponent issuehdrDao;
	
	@Autowired
	ScmIssueLineItemComponent issuelineitemDao;
	
	@Autowired
	PhysicalStockComponent physicalstockDao;

	@Autowired
	JuteQualityPriceMasterComponent priceDao;
	
	@Autowired
	ScmMrLineItemComponent mrlineitemDao;

	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.IssueRegisterService#createReport(java.sql.Date)
	 * 
	 * Fetching Issue Line Items by groupcode (for getting only jute type records) and issue header by issue date
	 * 
	 * Then matching the issuedate with the user input date, if match found then prepare the report
	 * 
	 * @Param 
	 * Date date
	 * 
	 * @RequestMapping GET
	 * 
	 */
	@Override
	public List<IssueRegisterDTO> createReport(Date date) {
		List<IssueRegisterDTO> recieptregiDTO = new ArrayList<>(); 
		
		try {
//			List<ScmIssueLineItem> lineitems = issuelineitemDao.getIssueLineItemByGroupCode("999");
			List<ScmIssueHdr> issuehdrs = issuehdrDao.getScmIssuHdrByModOn(date);
			List<ScmIssueLineItem> lineitems = new ArrayList<>();
			for(int i = 0 ; i < issuehdrs.size(); i++) {
				List<ScmIssueLineItem> lineitem = 
					issuelineitemDao.getIssueLineItemByIssueNoAndGroupCode(String.valueOf(issuehdrs.get(i).getId()),"999");
				lineitems.addAll(lineitem);
			}
			issuehdrs.forEach(issuehdt->{
				recieptregiDTO.add(prepareIssueRegiDTO(issuehdt,lineitems));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recieptregiDTO;
	}

	private IssueRegisterDTO prepareIssueRegiDTO(ScmIssueHdr issuehdt, List<ScmIssueLineItem> lineitems) {
		IssueRegisterDTO issueregister = new IssueRegisterDTO();
		
		/*
		 * Fetching Issue Line Item by issueno and group code 999
		 */
		List<ScmIssueLineItem> lineitem = 
				issuelineitemDao.getIssueLineItemByIssueNoAndGroupCode(String.valueOf(issuehdt.getId()),"999");
		
		IssueReportGrandTotalDTO issuegrandtotal = new IssueReportGrandTotalDTO();	
		long balegrandtotal = 0;
		long loosegrandtotal = 0;
		double weightgrandtotal = 0;
		
		/*
		 * For every line items fetching physicalstock by getting trans id from lineitem field additional req
		 * 
		 * preparing the DTO from the fetched data
		 * 
		 */
		for(int j = 0 ; j < lineitems.size() ; j++) {
			
			//Getting trans id from addtional req field by splitting up then string and selecting the 0th element
			String[] additionalreq = lineitems.get(j).getAdditionalRequirement().split("\\^");
			//fetching physical stock entity by trans id
			PhysicalStock physicalstock = physicalstockDao.getPhysicalStockById(Long.parseLong(additionalreq[0]));
			
			/*
			 * matching conversion unit from physical stock with BALE or LOOSE to decide whether is issue quantity is
			 * of BALE type or LOOSE
			 */
			if(physicalstock.getConversionUnit().equalsIgnoreCase("BALE")) {
				balegrandtotal = balegrandtotal + lineitems.get(j).getIssueQty();
				ScmMrLineItem mrline = mrlineitemDao.getScmMrLineItemByLineId(physicalstock.getGoodItemSerialNo());
				long avg = mrline.getActualWeight()/mrline.getActualBale();
				weightgrandtotal = weightgrandtotal + (lineitems.get(j).getIssueQty()*avg);
			}
			if(physicalstock.getConversionUnit().equalsIgnoreCase("LOOSE")) {
				loosegrandtotal = loosegrandtotal + lineitems.get(j).getIssueQty();
				weightgrandtotal = weightgrandtotal + lineitems.get(j).getIssueQty();
			}
			
			
		}
		
		if(!lineitem.isEmpty()) {
			List<IssueRegisterLineitemDTO> lines = new ArrayList<>();
			for(int i = 0 ; i < lineitem.size() ; i++) {
				IssueRegisterLineitemDTO line = new IssueRegisterLineitemDTO();
				
				//Getting trans id from addtional req field by splitting up then string and selecting the 0th element
				String[] additionalreq = lineitem.get(i).getAdditionalRequirement().split("\\^");
				//fetching physical stock entity by trans id
				PhysicalStock physicalstock = physicalstockDao.getPhysicalStockById(Long.parseLong(additionalreq[0]));
				
				issueregister.setIssueDate(issuehdt.getIssueDate());
				issueregister.setIssueNo(issuehdt.getId());
//				issueregister.setLotNo(12);
				
				//Fetching quality by quality id fetched from lineitem table
				JuteQualityPriceMaster quality = new JuteQualityPriceMaster();
				if(!lineitem.get(i).getQuality().isEmpty() || !lineitem.get(i).getQuality().equals("0")) {
					quality = priceDao.getJuteQualityPriceMasterById(Long.valueOf(lineitem.get(i).getQuality()));
				}
				if(quality!=null) {
					line.setQuality(quality.getJuteQuality());
				}
				line.setWarehouseNo(physicalstock.getWareHouseNo());
				
				/*
				 * matching conversion unit from physical stock with BALE or LOOSE to decide whether is issue quantity is
				 * of BALE type or LOOSE
				 */
				if(physicalstock.getConversionUnit().equalsIgnoreCase("BALE")) {
					line.setBales(String.valueOf(lineitem.get(i).getIssueQty())+" BALES");
					line.setWeight(String.valueOf(lineitem.get(i).getIssueQty()*1.5)+" QNT");
					/*balegrandtotal = balegrandtotal + lineitems.get(i).getIssueQty();
					weightgrandtotal = weightgrandtotal + (lineitems.get(i).getIssueQty()*1.5);*/
				}else if(physicalstock.getConversionUnit().equalsIgnoreCase("LOOSE")) {
					line.setLoose(String.valueOf(lineitem.get(i).getIssueQty())+" QNT");
					line.setWeight(String.valueOf(lineitem.get(i).getIssueQty())+" QNT");
					/*loosegrandtotal = loosegrandtotal + lineitems.get(i).getIssueQty();
					weightgrandtotal = weightgrandtotal + lineitems.get(i).getIssueQty();*/
				}
				lines.add(line);
			}
			issuegrandtotal.setBales(balegrandtotal);
			issuegrandtotal.setLoose(loosegrandtotal);
			issuegrandtotal.setWeight(weightgrandtotal);
			
			issueregister.setIssuegrnadtotal(issuegrandtotal);
			issueregister.setLineitems(lines);
		}
		
//		System.out.println(" "+lineitem+ " "+physicalstock.getId()+" "+issuehdt);
		
		return issueregister;
	}

}
