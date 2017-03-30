package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.AllowanceSettingDaoImp;
import com.hbcsoft.sys.entity.AllowanceSetting;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("allowanceSettingService")
public class AllowanceSettingServiceImp extends LogBaseClass<AllowanceSettingServiceImp> implements AllowanceSettingService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private AllowanceSettingDaoImp allowanceSettingDao;
	
	@Override
	public List<AllowanceSetting> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_allowanceSetting_queryForList");
		System.out.println("=========queryForList====>"+sql);
		List<AllowanceSetting> list = new ArrayList<AllowanceSetting>();
		try{
			list =allowanceSettingDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(AllowanceSetting allowanceSetting) {
		try {
			allowanceSettingDao.save(allowanceSetting, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
