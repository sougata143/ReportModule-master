package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.dto.GodownWiseStockRegisterDTO;
import com.sls.report.dto.GodownWiseStockRegisterReportDTOss;
import com.sls.report.dto.GodownWiseStockRegistersDTO;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.services.GodownWiseStockRegisterService;

/*
 * Service class for Godownwise Stock Register Report 
 */
@Service
public class GodownWiseStockRegisterServiceImpl implements GodownWiseStockRegisterService {

	@Autowired
	PhysicalStockComponent physicalstockDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.GodownWiseStockRegisterService#godownWiseStockRegisterReport(java.sql.Date)
	 * 
	 * GodownWiseStockRegisterReport
	 * 
	 * @Param 
	 * Date modon
	 * 
	 * @RequestMethod GET
	 * 
	 */
	@Override
	public List<GodownWiseStockRegisterReportDTOss> godownWiseStockRegisterReport(Date modon) {
		List<GodownWiseStockRegisterReportDTOss> godownreportDTO = new ArrayList<>();
		
		/*
		 *Fetching PhysicalStock table by group code (for fetching only jute records)
		 *
		 * and modification date and preparing DTO for each record
		 *  
		 */
		try {
			List<PhysicalStock> stocks = physicalstockDao.getAllPhysicalStockByGrpCode("999");
			List<PhysicalStock> allstocks = physicalstockDao.getAllPhysicalStockByGoodType("MR");
//			List<PhysicalStock> allstocks = physicalstockDao.getByModifiedDate(modon);
			
			/*
			 * Fetching warehouse no for each physical stock record
			 */
			List<String> warehouses = new ArrayList<>();
			for(int i = 0 ; i < stocks.size() ; i++) {
				String warehouse = null;
				if(stocks.get(i).getWareHouseNo()!=null) {
					warehouse = stocks.get(i).getWareHouseNo();
				}
				warehouses.add(warehouse);
			}
			
			/*
			 *Getting distinct warehouse no 
			 */
			List<String> warehousess = warehouses.stream().distinct().collect(Collectors.toList());
			
			/*
			 * For each distinct warehosue no fetching physicalstock by warehouse no
			 */
			warehousess.forEach(warehouse->{
				List<PhysicalStock> stockss = physicalstockDao.getPhysicalStockByWarehouseAndModOn(warehouse, modon);
				godownreportDTO.add(prepareGodownWiseReportDTO(stockss,warehouse,allstocks));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return godownreportDTO;
	}

	
	/*
	 * DTO Preparation class for Godownwise Stock Register Report
	 */
	private GodownWiseStockRegisterReportDTOss prepareGodownWiseReportDTO(List<PhysicalStock> stockss,
																	String warehouse, List<PhysicalStock> allstocks) {
		
		GodownWiseStockRegisterReportDTOss reportsDTO = new GodownWiseStockRegisterReportDTOss();
		GodownWiseStockRegistersDTO reportDTO = new GodownWiseStockRegistersDTO();
		
		reportDTO.setQuality(warehouse);		//Setting quality as warehouse no
		
		float godownbale = 0;
		float godownloose = 0.0f;
		float godownweight = 0.0f;
		long godownhbale = 0;
		long godownpbale = 0;
		
		float grandbale = 0;
		float grandloose = 0.0f;
		float grandweight = 0.0f;
		long grandhbale = 0;
		long grandpbale = 0;
		
		/*
		 * Fetching Physical Stock records and setting the records into the DTO for ReportRows field
		 */
		List<GodownWiseStockRegisterDTO> report = new ArrayList<>();
		for(int i = 0 ; i < stockss.size() ; i++) {
			GodownWiseStockRegisterDTO dto = new GodownWiseStockRegisterDTO();
			
			if(stockss.get(i).getConversionUnit()!=null) {
				if(stockss.get(i).getConversionUnit().equals("LOOSE")) {
					dto.setLoose(stockss.get(i).getStockInHand());
					godownloose = godownloose + stockss.get(i).getStockInHand();
					dto.setWeight(stockss.get(i).getStockInHand());
					godownweight = godownweight + stockss.get(i).getStockInHand();
				}else if(stockss.get(i).getConversionUnit().equals("BALE")) {
					godownbale = godownbale + stockss.get(i).getNewBaleStock();
//					dto.setBale(stockss.get(i).getNewBaleStock());
					float weight = (float) (stockss.get(i).getNewBaleStock()*1.5);
					dto.setWeight(weight);
					dto.setBale(stockss.get(i).getNewBaleStock());
					float godownbaleweight = (float) (stockss.get(i).getNewBaleStock()*1.5);
					godownweight = godownweight + godownbaleweight;
				}
			}
			
//			dto.setHbale(hbale);
//			dto.setPbale(pbale);
//			dto.setWeight(stockss.get(i).getStockInHand());
			report.add(dto);
		}
		reportDTO.setReportrows(report);
		
		/*
		 * Fetching data for godowntotal field
		 */
		GodownWiseStockRegisterDTO godowntotal = new GodownWiseStockRegisterDTO();
		godowntotal.setBale(godownbale);
		godowntotal.setHbale(godownhbale);
		godowntotal.setLoose(godownloose);
		godowntotal.setPbale(godownpbale);
		godowntotal.setWeight(godownweight);
		reportDTO.setGodowntotal(godowntotal);
		
		/*
		 * Fetching data for grandntotal field
		 */
		GodownWiseStockRegisterDTO grandtotal = new GodownWiseStockRegisterDTO();
		for(int j = 0 ; j < allstocks.size() ; j++) {
			if(allstocks.get(j).getConversionUnit()!=null) {
				if(allstocks.get(j).getConversionUnit().equals("LOOSE")) {
					grandloose = grandloose + allstocks.get(j).getStockInHand();
					grandweight = grandweight + allstocks.get(j).getStockInHand();
				}else if(allstocks.get(j).getConversionUnit().equals("BALE")) {
					grandbale = grandbale + allstocks.get(j).getNewBaleStock();
					float baleweight = (float) (allstocks.get(j).getNewBaleStock()*1.5);
					grandweight = grandweight + baleweight;
				}
			}
//			 grandweight = grandweight + allstocks.get(j).getStockInHand();
			 grandhbale = 0;
			 grandpbale = 0;
		}
		
		grandtotal.setBale(grandbale);
		grandtotal.setHbale(grandhbale);
		grandtotal.setLoose(grandloose);
		grandtotal.setPbale(grandpbale);
		grandtotal.setWeight(grandweight);
		
		reportsDTO.setGrandtotal(grandtotal);		//Setting grand total
		reportsDTO.setReportDTO(reportDTO);
		
		return reportsDTO;
	}

}
