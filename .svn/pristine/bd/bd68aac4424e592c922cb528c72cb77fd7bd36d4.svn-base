package com.hbcsoft.common;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@WebService(name = "commonService")
public class CommonServiceImp extends LogBaseClass<CommonServiceImp> implements CommonService {
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private CommonDaoImp commonDao;

	@Override
	public List<KeyValueEntity> queryList(String sql) throws HbcsoftException {
		this.getLogger().info("============queryList=========start==");
		
		List<KeyValueEntity> lst;
		try {
			lst = commonDao.queryAll(sql, jt);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		
		this.getLogger().info("============queryList=========end==");
		return lst;
	}
	
}
