package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.BudgetDaoImp;
import com.hbcsoft.sys.entity.Budget;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("budgetService")
public class BudgetServiceImp extends LogBaseClass<BudgetServiceImp> implements BudgetService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private BudgetDaoImp budgetDao;
	
	@Override
	public List<Budget> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_budget_queryForList");
		System.out.println("=========queryForList====>"+sql);
		List<Budget> list = new ArrayList<Budget>();
		try{
			list =budgetDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(Budget budget) {
		try {
			budgetDao.save(budget, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
