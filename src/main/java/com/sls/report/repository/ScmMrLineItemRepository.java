package com.sls.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sls.report.entity.ScmMrLineItem;

public interface ScmMrLineItemRepository extends JpaRepository<ScmMrLineItem, Long> {
	
	List<ScmMrLineItem> findByJuteReceiveNo(long rcvno);
	
	@Query("select l from ScmMrLineItem l where l.juteReceiveNo = :rcvno")
	List<ScmMrLineItem> findByReceiveNoEntity(@Param("rcvno") long rcvno);
	ScmMrLineItem findByActualQualityAndItemCodeAndJuteReceiveNo(String quality, String itemCode, long mrNo);
	List<ScmMrLineItem> findByActualQuality(String quality);
}
