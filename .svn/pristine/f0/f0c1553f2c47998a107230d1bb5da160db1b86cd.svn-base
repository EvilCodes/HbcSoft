package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.CompanyDaoImp;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("companyService")
public class CompanyServiceImp extends LogBaseClass<CompanyServiceImp> implements CompanyService {

	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private CompanyDaoImp comDao;
	
	@Override
	public int add(Company com) throws HbcsoftException {
		return comDao.save(com, jt);
	}

	@Override
	public List<Company> queryCount(String companyName) throws HbcsoftException {
		this.getLogger().info("============queryCount=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		String sql = HbcsoftCache.getSqlValue("company_namecount");
		System.out.println("=============>"+sql);
		List<Company> com = null;
		try {
			com = comDao.queryAll(sql, jt, companyName);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.getLogger().info("============queryCount=========end==");
		return com;
	}

	@Override
	public List<Company> queryCountHk(String tableName)  throws HbcsoftException{
		this.getLogger().info("============queryCountHk=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		String sql = HbcsoftCache.getSqlValue("company_hknamecount");
		System.out.println("=============>"+sql);
		List<Company> com = null;
		try {
			com = comDao.queryAll(sql, jt, tableName);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getLogger().info("============queryCountHk=========end==");
		return com;
	}

	@Override
	public List<Company> queryCountCn(String tableName) throws HbcsoftException{
		this.getLogger().info("============queryCountCn=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		String sql = HbcsoftCache.getSqlValue("company_cnnamecount");
		System.out.println("=============>"+sql);
		List<Company> com = null;
		try {
			com = comDao.queryAll(sql, jt, tableName);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getLogger().info("============queryCountCn=========end==");
		return com;
	}

	@Override
	public Company query(String companyName) throws HbcsoftException {
		this.getLogger().info("============query=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		String sql = HbcsoftCache.getSqlValue("company_namecount");
		System.out.println("=============>"+sql);
		Company com = null;
		try {
			com = comDao.query(sql, jt, companyName);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getLogger().info("============query=========end==");
		return com;
	}

	@Override
	public Company queryId(Long companyId) throws HbcsoftException {
		this.getLogger().info("============query=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		String sql = HbcsoftCache.getSqlValue("company_queryId");
		System.out.println("=============>"+sql);
		Company com = null;
		try {
			com = comDao.query(sql, jt, Long.toString(companyId));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.getLogger().info("============query=========end==");
		return com;
	}
	
}