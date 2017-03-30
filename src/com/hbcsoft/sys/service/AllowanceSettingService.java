package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.AllowanceSetting;
@Service
public interface AllowanceSettingService {
	public List<AllowanceSetting> queryForList()throws HbcsoftException;
	public int add(AllowanceSetting allowanceSetting);
}
