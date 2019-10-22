package com.sls.report.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sls.report.entity.PhysicalStock;

public interface PhysicalstockRepository extends JpaRepository<PhysicalStock, Long> {
	
	List<PhysicalStock> findByLastModifiedDate(Date modon);
	List<PhysicalStock> findByitemId(String itemCode);
	List<PhysicalStock> findByQualityCode(String qualityCode);
	List<PhysicalStock> findByQualityCodeAndLastModifiedDate(String qualityCode, Date modon);
	List<PhysicalStock> findByItemIdAndQualityCode(String itemCode, String qualityCode);
	List<PhysicalStock> findByWareHouseNo(String warehouseNo);
	List<PhysicalStock> findByWareHouseNoAndLastModifiedDate(String warehouseNo, Date modon);
	List<PhysicalStock> findByItemGroupId(String grpCode);
	List<PhysicalStock> findByItemGroupIdAndLastModifiedDate(String grpCode, Date modOn);
	@Query("select distinct p.wareHouseNo from PhysicalStock p")
	List<String> findByUniqueWareHouseNo();
	PhysicalStock findByGoodItemSerialNo(long grn);
	List<PhysicalStock> findByGoodType(String goodType);
}
