package com.sls.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sls.report.entity.BatchMaster;


@Repository
public interface BatchMasterRepository extends JpaRepository<BatchMaster, Long> {

}
