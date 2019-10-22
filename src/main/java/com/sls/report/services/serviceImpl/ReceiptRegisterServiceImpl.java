package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.ItemMasterDao;
import com.sls.report.component.JuteGateEntryDtlComponent;
import com.sls.report.component.JuteGateEntryHdrComponent;
import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.ScmMrLineItemComponent;
import com.sls.report.dto.MrLineDTO;
import com.sls.report.dto.ReceiptDTO;
import com.sls.report.dto.ReceiptRegisterDTO;
import com.sls.report.entity.ItemMaster;
import com.sls.report.entity.JuteEntryHeader;
import com.sls.report.entity.JuteGateEntryDtl;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.ScmMrLineItem;
import com.sls.report.services.ReceiptRegisterService;


/*
 * Service class for Reciept Register Report
 */
@Service
public class ReceiptRegisterServiceImpl implements ReceiptRegisterService{
	
	@Autowired
	JuteGateEntryDtlComponent juteentrydtlDao;
	
	@Autowired
	JuteGateEntryHdrComponent juteentryhdrDao;
	
	@Autowired
	ScmMrLineItemComponent mrlineitemDao;
	
	@Autowired
	ItemMasterDao itemDao;
	
	@Autowired
	JuteQualityPriceMasterComponent priceDao;

	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.ReceiptRegisterService#getAllReceiptRegister(java.sql.Date)
	 * 
	 * Fetching all JuteGateEntryHdr table by chalan date and preparing the report DTO
	 * 
	 * @Param
	 * Date date
	 * 
	 * @RequestMethod GET
	 * 
	 */
	@Override
	public List<ReceiptRegisterDTO> getAllReceiptRegister(Date date) {
		List<ReceiptRegisterDTO> reportDtoList = new ArrayList<>();
		
		try {
			List<JuteEntryHeader> entryhdr = juteentryhdrDao.getJuteEntryHeaderByChallanDate(date);
			entryhdr.forEach(entry->{
				reportDtoList.add(prepareReportDTO(entry, date));
			});
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reportDtoList;
		
	}


	private ReceiptRegisterDTO prepareReportDTO(JuteEntryHeader entry, Date date) {
		ReceiptRegisterDTO receiptDTO = new ReceiptRegisterDTO();
		
		//Fetching JuteGateEntryDtl table by JuteGateEntryHdr hdrid
		List<JuteGateEntryDtl> entrydtl = juteentrydtlDao.getJuteGateEntryDtlByHdrId(entry.getId());
		
		//Fetching Mr Line Item by mr no fetched from JuteGateEntryHdr table
		List<ScmMrLineItem> mrline = mrlineitemDao.getScmMrLineItemById(entry.getMrNo());
		
		receiptDTO.setBrokerAddress(entry.getBrokerAddress());
		receiptDTO.setChallanDate(entry.getChalanDate());
		
		
		
		/*
		 * preparing entrydtl DTO
		 */
		List<ReceiptDTO> receiptDTOs = new ArrayList<>();
		float totalqnt = 0.0f;
		long totalBalePrice= 0l;
		long totalLoosePrice= 0l;
		List<ScmMrLineItem> mrline2 = mrlineitemDao.getScmMrLineItemById(entry.getMrNo());
		for(int i = 0 ; i < entrydtl.size() ; i++) {
			ReceiptDTO recieptEmpDTO = new ReceiptDTO();
			ItemMaster item = itemDao.getItemByName(entrydtl.get(i).getActualJuteTyp());
			JuteQualityPriceMaster price = priceDao.getPriceByQuality(entrydtl.get(i).getActualQuality());
			
			ScmMrLineItem mrline3 =
					mrlineitemDao.getMrLineItemByQualityAndItemAndMrNo(
							String.valueOf(price.getId()), item.getId(), entry.getMrNo());
			recieptEmpDTO.setClaimsCondition(mrline3.getClaimsCondition());
			recieptEmpDTO.setActualWeight(mrline3.getActualWeight());
			recieptEmpDTO.setRate(mrline3.getRate());
			recieptEmpDTO.setClaimsQuality(mrline3.getClaimsQuality());
			
			recieptEmpDTO.setNo(entrydtl.get(i).getActualQuality());
			
			if(entrydtl!=null) {
				if(entrydtl.get(i).getReceivedIn().equalsIgnoreCase("BALE")) {
					if(entrydtl.get(i).getActualQuantity()!=0) {
						recieptEmpDTO.setBaleQuantity(entrydtl.get(i).getActualQuantity());
					}
					recieptEmpDTO.setPackingType(entrydtl.get(i).getReceivedIn());
					Long rate = mrline3.getRate();
					Long avrgConv = 0L;
					if(entrydtl.get(i).getActualQuantity()!=0) {
						if(mrline3.getActualWeight()!=0) {
							avrgConv = mrline3.getActualWeight()/entrydtl.get(i).getActualQuantity();
						}
					}
						
					
					Long qnt = entrydtl.get(i).getActualQuantity();
					Long balePrice  = rate * qnt;
					recieptEmpDTO.setPrice(balePrice);
					totalBalePrice = totalBalePrice + balePrice;
				}else if(entrydtl.get(i).getReceivedIn().equalsIgnoreCase("LOOSE")) {
					recieptEmpDTO.setLooseQuantity(entrydtl.get(i).getActualQuantity());
					recieptEmpDTO.setPackingType(entrydtl.get(i).getReceivedIn());
					recieptEmpDTO.setPrice(mrline3.getRate() * entrydtl.get(i).getActualQuantity());
					Long loosePrice = mrline3.getRate() * entrydtl.get(i).getActualQuantity();
					totalLoosePrice = totalLoosePrice + loosePrice;
				}
			}
			
			
			totalqnt = totalqnt + entrydtl.get(i).getActualQuantity();
			
			receiptDTOs.add(recieptEmpDTO);
		}
		
		/*
		 * Fetching claims condition from mr line item and summing them for getting totalcondition
		 */
		Float totalcondition = 0.0f;
		Float totalQuality = 0.0f;
		Long totalRate = 0l;
		
		List<MrLineDTO> mrlines = new ArrayList<>();
		for(int j = 0 ; j < mrline.size() ; j++) {
			MrLineDTO mrlineEmp = new MrLineDTO();
			
			if(mrline.get(j).getClaimsCondition()!=null) {
				mrlineEmp.setCondition(Float.valueOf(mrline.get(j).getClaimsCondition()));
				totalcondition = totalcondition + Float.parseFloat(mrline.get(j).getClaimsCondition());
				if(mrline.get(j).getClaimsQuality()!=null)
					totalQuality = totalQuality + Float.parseFloat(mrline.get(j).getClaimsQuality());
				totalRate = totalRate + mrline.get(j).getRate();
			}
			
			mrlines.add(mrlineEmp);
		}
		
		receiptDTO.setTotalcondition(totalcondition);
		receiptDTO.setVehicleNo(entry.getVehicleNo());
		receiptDTO.setInDate(entry.getInDate());
		receiptDTO.setChalanNo(String.valueOf(entry.getChalanNo()));
		
		receiptDTO.setTotalQuantity(totalqnt);
		receiptDTO.setJuteEntryDtls(receiptDTOs);
		receiptDTO.setTotalBalePrice(totalBalePrice);
		receiptDTO.setTotalLoosePrice(totalLoosePrice);
		receiptDTO.setTotalRate(totalRate);
		receiptDTO.setTotalQuality(totalQuality);
		return receiptDTO;
	}
	
	

}
