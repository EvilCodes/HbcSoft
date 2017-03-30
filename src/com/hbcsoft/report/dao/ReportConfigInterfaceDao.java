package com.hbcsoft.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.report.entity.ReportConfigInterface;
import com.hbcsoft.zdy.common.HbcsoftJT;

@Repository
public interface ReportConfigInterfaceDao {

	List<ReportConfigInterface> queryListEntity(String sql,HbcsoftJT jt, String...param);
}
