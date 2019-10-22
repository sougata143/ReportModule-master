package com.sls.report.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sls.report.component.JuteQualityPriceMasterComponent;
import com.sls.report.component.PhysicalStockComponent;
import com.sls.report.component.ScmIssueLineItemComponent;
import com.sls.report.dto.DailyJuteStockReportDTO;
import com.sls.report.entity.JuteQualityPriceMaster;
import com.sls.report.entity.PhysicalStock;
import com.sls.report.entity.ScmIssueLineItem;
import com.sls.report.services.DailyJuteStockReportService;

@Service
public class DailyJuteStockReportServiceImpl implements DailyJuteStockReportService{
	
	@Autowired
	PhysicalStockComponent physicalstockDao;
	
	@Autowired
	JuteQualityPriceMasterComponent pricemasterDao;
	
	@Autowired
	ScmIssueLineItemComponent lineitemDao;


	@Override
	public List<DailyJuteStockReportDTO> getDailyJuteStockReport() {
		List<DailyJuteStockReportDTO> dailyjutestocks = new ArrayList<>();
		
			try {
				List<JuteQualityPriceMaster> jutestocks = pricemasterDao.getAllJuteQualityPriceMaster();
				jutestocks.forEach(jutes->{
//					List<PhysicalStock> physicalstock = 
//										physicalstockDao.getPhysicalStockByQuality(jutes.getJuteQuality());
					
					dailyjutestocks.add(prepareJuteStockDTO(jutes));
				});
			}catch(Exception e) {                    
			e.printStackTrace();
		}
		return dailyjutestocks;
	}

	private DailyJuteStockReportDTO prepareJuteStockDTO(JuteQualityPriceMaster jutes) {
		DailyJuteStockReportDTO dailystock = new DailyJuteStockReportDTO();
		System.out.println(jutes.getItemCode());
		
		dailystock.setJcode(jutes.getId());
		dailystock.setQuality(jutes.getJuteQuality());
		dailystock.setIssueRate(jutes.getRate());
		dailystock.setGroup(jutes.getItemCode());
		
		
		List<PhysicalStock> physicalstock = 
				physicalstockDao.getPhysicalStockByItemAndQuality(jutes.getItemCode(), String.valueOf(jutes.getId()));
		if(!physicalstock.isEmpty()) {
			System.out.println("inside if");
			/*float oppenningwt = 0;
			float receiptwt = 0;
			float closingwt = 0;*/
			
			
			if(physicalstock.size()>1) {
				float oppenningwt = 0;
				float closingwt = 0;
				float receiptwt = 0;
				
				for(int i = 0; i < physicalstock.size(); i++) {
					oppenningwt=oppenningwt+physicalstock.get(i).getTotalStock();
					closingwt = closingwt + physicalstock.get(i).getTotalStock();
					receiptwt = receiptwt + physicalstock.get(i).getNewStock();
					dailystock.setOppenningWt(String.valueOf(oppenningwt));
					dailystock.setReceiptWeight(String.valueOf(receiptwt));
					dailystock.setClosingWeight(String.valueOf(closingwt));
					
					List<ScmIssueLineItem> lineitem = lineitemDao.getLineItemsByItem(jutes.getItemCode());
					String tranid = null;
					List<ScmIssueLineItem> lineiteme = new ArrayList<>();
					if(!lineitem.isEmpty() && physicalstock!=null) {
						for(int j = 0 ; j < lineitem.size() ; j++) {
							String[] additionalreq = lineitem.get(j).getAdditionalRequirement().split("\\^");
							System.out.println(additionalreq[0]);
							System.out.println(physicalstock.get(j).getId());
							/*if(additionalreq[0].equals(String.valueOf(physicalstock.get(j).getId()))) {
								lineiteme.add(lineitem.get(j)) ;
							}*/
							if((String.valueOf(physicalstock.get(j).getId())).equals(additionalreq[0])) {
								lineiteme.add(lineitem.get(j)) ;
							}
						}
					}
					if(lineiteme.size()>1) {
						long qnt = 0;
						for(int k = 0 ; k < lineitem.size() ; k++) {
							qnt = lineitem.get(k).getIssueQty();
							
							dailystock.setIssueWeight(String.valueOf(qnt));
							long issueqnty = qnt;
							long rate = jutes.getRate();
							long issueamnt = issueqnty*rate;
							dailystock.setIssueAmmount(String.valueOf(issueamnt));
						}
						
					}else {
						long qnt = 0;
						for(int k = 0 ; k < lineitem.size() ; k++) {
							qnt = lineitem.get(k).getIssueQty();
							
							dailystock.setIssueWeight(String.valueOf(qnt));
							long issueqnty = qnt;
							long rate = jutes.getRate();
							long issueamnt = issueqnty*rate;
							dailystock.setIssueAmmount(String.valueOf(issueamnt));
						}
					}
				}
			}
			/*physicalstock.forEach(stock->{
				oppenningwt = oppenningwt+stock.getTotalStock();
				receiptwt = receiptwt+stock.getNewStock();
				closingwt = closingwt+stock.getTotalStock();
				dailystock.setOppenningWt(String.valueOf(stock.getTotalStock()));
				dailystock.setReceiptWeight(String.valueOf(stock.getNewStock()));
				dailystock.setClosingWeight(String.valueOf(stock.getTotalStock()));
				
				String tranid = null;
				List<ScmIssueLineItem> lineiteme = new ArrayList<>();
				if(!lineitem.isEmpty() && physicalstock!=null) {
					for(int i = 0 ; i < lineitem.size() ; i++) {
						String[] additionalreq = lineitem.get(i).getAdditionalRequirement().split("\\^");
						System.out.println(additionalreq[0]);
						System.out.println(stock.getId());
						if(additionalreq[0].equals(String.valueOf(stock.getId()))) {
							lineiteme.add(lineitem.get(i)) ;
						}
					}
				}
				if(lineiteme.size()>1) {
					lineiteme.forEach(line->{
						long qnt = 0;
						qnt = qnt+line.getIssueQty();
						
						dailystock.setIssueWeight(String.valueOf(qnt));
						long issueqnty = qnt;
						long rate = jutes.getRate();
						long issueamnt = issueqnty*rate;
						dailystock.setIssueAmmount(String.valueOf(issueamnt));
					});
				}else {
					lineiteme.forEach(line->{
						dailystock.setIssueWeight(String.valueOf(line.getIssueQty()));
						long issueqnty = line.getIssueQty();
						long rate = jutes.getRate();
						long issueamnt = issueqnty*rate;
						dailystock.setIssueAmmount(String.valueOf(issueamnt));
					});
				}
				
			});*/
			/*dailystock.setOppenningWt(String.valueOf(oppenningwt));
			dailystock.setReceiptWeight(String.valueOf(receiptwt));
			dailystock.setClosingWeight(String.valueOf(closingwt));*/
		}else {
			System.out.println("inside else");
			dailystock.setOppenningWt(null);
			dailystock.setReceiptWeight(null);
			dailystock.setClosingWeight(null);
		}
		
		
		/*List<ScmIssueLineItem> lineitem = lineitemDao.getLineItemsByItem(jutes.getItemCode());
		String tranid = null;
		ScmIssueLineItem lineiteme = new ScmIssueLineItem();
		if(!lineitem.isEmpty() && physicalstock!=null) {
			for(int i = 0 ; i < lineitem.size() ; i++) {
				String[] additionalreq = lineitem.get(i).getAdditionalRequirement().split("\\^");
				System.out.println(additionalreq[0]);
				System.out.println(physicalstock.getId());
				if(additionalreq[0].equals(String.valueOf(physicalstock.getId()))) {
					lineiteme = lineitem.get(i);
				}
			}
		}
		
		dailystock.setIssueWeight(String.valueOf(lineiteme.getIssueQty()));
		long issueqnty = lineiteme.getIssueQty();
		long rate = jutes.getRate();
		long issueamnt = issueqnty*rate;
		dailystock.setIssueAmmount(String.valueOf(issueamnt));*/
		
		dailystock.setAdjutsWeight("QNT");
		dailystock.setDeliveryWeight("M/TON");
		
		return dailystock;
	}

}
