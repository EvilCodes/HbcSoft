package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.AdvNumParaDaoImp;
import com.hbcsoft.sys.entity.AdvNumPara;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("advNumParaService")
public class AdvNumParaServiceImp extends LogBaseClass<AdvNumParaServiceImp> implements AdvNumParaService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private AdvNumParaDaoImp advNumParaDao;
	
	@Override
	public List<AdvNumPara> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_advnumpara_queryForList");
		System.out.println("======AdvNumParaServiceImp===queryForList====>"+sql);
		List<AdvNumPara> list = new ArrayList<AdvNumPara>();
		try{
			list =advNumParaDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(AdvNumPara advNumPara) {
		try {
			advNumParaDao.save(advNumPara, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
