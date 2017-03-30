package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.AuditentrustDDaoImp;
import com.hbcsoft.sys.entity.AuditentrustD;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("auditentrustDService")
public class AuditentrustDServiceImp extends LogBaseClass<AuditentrustDServiceImp> implements AuditentrustDService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private AuditentrustDDaoImp auditentrustDDao;
	
	@Override
	public List<AuditentrustD> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_auditentrust_d_queryForList");
		System.out.println("=========queryForList====>"+sql);
		List<AuditentrustD> list = new ArrayList<AuditentrustD>();
		try{
			list =auditentrustDDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(AuditentrustD auditentrustD) {
		try {
			auditentrustDDao.save(auditentrustD, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
