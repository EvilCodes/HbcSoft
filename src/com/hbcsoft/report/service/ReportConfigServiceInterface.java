package com.hbcsoft.report.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfigInterface;

public interface ReportConfigServiceInterface {

	void saveInterfaceMessage(List<ReportConfigInterface> interfaceList) throws HbcsoftException;

	List<ReportConfigInterface> queryEntity(String tempReportId);
	
	String [] validateEntity(String tempReportId, String...rowids) throws HbcsoftException;
}
