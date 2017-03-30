package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.AuditentrustDaoImp;
import com.hbcsoft.sys.entity.Auditentrust;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("auditentrustService")
public class AuditentrustServiceImp extends LogBaseClass<AuditentrustServiceImp> implements AuditentrustService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private AuditentrustDaoImp auditentrustDao;
	
	@Override
	public List<Auditentrust> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_auditentrust_queryForList");
		System.out.println("=========queryForList====>"+sql);
		List<Auditentrust> list = new ArrayList<Auditentrust>();
		try{
			list =auditentrustDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(Auditentrust auditentrust) {
		try {
			auditentrustDao.save(auditentrust, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
