package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.AttachmentServiceDaoImp;
import com.hbcsoft.sys.entity.Attachment;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Service("attachmentService")
public class AttachmentServiceImp extends LogBaseClass<AttachmentServiceImp> implements AttachmentService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private AttachmentServiceDaoImp attachmentDao;
	
	@Override
	public List<Attachment> queryForList() throws HbcsoftException {
		this.getLogger().info("============queryForList=========start==");
		String sql = HbcsoftCache.getSqlValue("sys_attachment_queryForList");
		System.out.println("=========queryForList====>"+sql);
		List<Attachment> list = new ArrayList<Attachment>();
		try{
			list =attachmentDao.queryAll(sql, jt);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getLogger().info("============queryForList=========end==");
		return list;
	}
	@Override
	public int add(Attachment attachment) {
		try {
			attachmentDao.save(attachment, jt);
		} catch (HbcsoftException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
