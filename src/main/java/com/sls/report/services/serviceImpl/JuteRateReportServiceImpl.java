package com.sls.report.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.BatchMasterComponent;
import com.sls.report.component.JuteQualityBatchMasterComponent;
import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.dto.JuteRateReportDTO;
import com.sls.report.entity.BatchMaster;
import com.sls.report.entity.JuteQualityBatchMap;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.services.JuteRateReportService;

/*
 * Service class for Jute Rate Report
 */
@Service
public class JuteRateReportServiceImpl implements JuteRateReportService {

	@Autowired
	JuteQualityPriceMasterComponent priceDao;
	
	@Autowired
	JuteQualityBatchMasterComponent batchmapDao;
	
	@Autowired
	BatchMasterComponent batchDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.sls.report.services.JuteRateReportService#getJuteRateReport()
	 * 
	 * Fetching all Jute Quality Price Master records
	 * 
	 */
	@Override
	public List<JuteRateReportDTO> getJuteRateReport() {
		List<JuteRateReportDTO> juterate = new ArrayList<>();
		try {
			List<JuteQualityPriceMaster> prices = priceDao.getAllJuteQualityPriceMaster();
			prices.forEach(price->{
				juterate.add(prepareJuteRateDTO(price));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return juterate;
	}

	/*
	 * Preparing DTO for Jute Rate Report
	 */
	private JuteRateReportDTO prepareJuteRateDTO(JuteQualityPriceMaster price) {
		JuteRateReportDTO jutestock = new JuteRateReportDTO();
		
		JuteQualityBatchMap batchmap = new JuteQualityBatchMap();
		BatchMaster batch = new BatchMaster();
		
		/*
		 * Fetching batchmap table by jute quality which is fetched from jute quality price master and the by batch id 
		 * 
		 * fetching batch master table
		 * 
		 * By that fetched batchname for the field batch
		 */
		if(batchmap!=null) {
			batchmap = batchmapDao.getByJuteQuality(price.getId());
			batch = batchDao.getBatchById(batchmap.getBatchId());
		}
		jutestock.setBatch(batch.getBatchName());
		jutestock.setDescription(price.getJuteQuality());
		jutestock.setJcode(String.valueOf(price.getId()));
		jutestock.setRate(String.valueOf(price.getRate()));
		
		return jutestock;
	}

}
