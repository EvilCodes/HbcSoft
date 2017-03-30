package com.hbcsoft.excel.dao;

import org.springframework.stereotype.Repository;

import com.hbcsoft.excel.service.ExcelServiceImp;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
@Repository
public class ExcelDaoImp extends LogBaseClass<ExcelServiceImp>  implements ExcelDao {

	@Override
	public void insertEntity(String sql,HbcsoftJT jt) {
		getLogger().info("=========createSql======start==");
		jt.update(sql);
		getLogger().info("=========createSql======end==");
	}

}
