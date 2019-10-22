package com.sls.report.component;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.report.entity.PhysicalStock;
import com.sls.report.repository.PhysicalstockRepository;

/*
 * Component Class for PhysicalStock
 */

@Component
public class PhysicalStockComponent {
	
	@Autowired
	PhysicalstockRepository physicalstockRepository;
	
	@Transactional
	public List<PhysicalStock> getAllPhysicalStock(){
		return physicalstockRepository.findAll();
	}
	
	@Transactional
	public List<PhysicalStock> getByModifiedDate(Date modon){
		return physicalstockRepository.findByLastModifiedDate(modon);
	}
	
	@Transactional
	public PhysicalStock getPhysicalStockById(long id) {
		return physicalstockRepository.findOne(id);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByItem(String itemCode){
		return physicalstockRepository.findByitemId(itemCode);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByQuality(String qualityCode){
		return physicalstockRepository.findByQualityCode(qualityCode);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByQualityAndModon(String qualityCode, Date modon){
		return physicalstockRepository.findByQualityCodeAndLastModifiedDate(qualityCode, modon);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByItemAndQuality(String itemCode, String qualityCode) {
		return physicalstockRepository.findByItemIdAndQualityCode(itemCode, qualityCode);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByWarehouse(String warehouseNo) {
		return physicalstockRepository.findByWareHouseNo(warehouseNo);
	}
	
	@Transactional
	public List<PhysicalStock> getPhysicalStockByWarehouseAndModOn(String warehouseNo, Date modOn) {
		return physicalstockRepository.findByWareHouseNoAndLastModifiedDate(warehouseNo, modOn);
	}
	
	@Transactional
	public List<String> getPhysicalStockByUniqueWarehouse() {
		return physicalstockRepository.findByUniqueWareHouseNo();
	}
	
	@Transactional
	public List<PhysicalStock> getAllPhysicalStockByGrpCode(String grpCode){
		return physicalstockRepository.findByItemGroupId(grpCode);
	}
	
	@Transactional
	public List<PhysicalStock> getAllPhysicalStockByGrpCodeAndModOn(String grpCode, Date modOn){
		return physicalstockRepository.findByItemGroupIdAndLastModifiedDate(grpCode, modOn);
	}
	
	@Transactional
	public List<PhysicalStock> getAllPhysicalStockByGoodType(String goodType){
		return physicalstockRepository.findByGoodType(goodType);
	}
	
	@Transactional
	public PhysicalStock getPhysicalStockByMrNo(long grn) {
		return physicalstockRepository.findByGoodItemSerialNo(grn);
	}

}
