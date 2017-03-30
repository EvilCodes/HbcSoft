package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Attachment;

@Service
public interface AttachmentService {

	List<Attachment> queryForList() throws HbcsoftException;

	int add(Attachment attachment);

}
