package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.AuditentrustD;

@Service
public interface AuditentrustDService {

	public List<AuditentrustD> queryForList() throws HbcsoftException;

	public int add(AuditentrustD auditentrustD);

}
