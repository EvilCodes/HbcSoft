package com.hbcsoft.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.report.entity.ReportConfigInterface;
import com.hbcsoft.zdy.common.DaoBaseClass;
import com.hbcsoft.zdy.common.HbcsoftJT;
@Repository
public class ReportConfigInterfaceDaoImp extends DaoBaseClass<ReportConfigInterface>
implements ReportConfigInterfaceDao {

	public List<ReportConfigInterface> queryListEntity(String sql,
			HbcsoftJT jt, String...param) {
		this.getLogger().info("=========queryListEntity======start==");
		
		List<ReportConfigInterface> list = new ArrayList<ReportConfigInterface>();
		final ReportConfigInterface ri = new ReportConfigInterface();
		list = jt.query(sql, param, (RowMapper)ri);
		
		this.getLogger().info("=========queryListEntity======end==");
		return list;
	}

}
