package com.hbcsoft.zdy.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.file.xml.HbcsoftXmlUtil;

public class SqlInitTask extends TimerTask {
	
	private final static Logger LOGGER = Logger.getLogger(SqlInitTask.class);
	
	public void run() {
		LOGGER.info("============SqlInitTask=====run====start===");

		try {
			new HbcsoftXmlUtil().read();
		} catch (HbcsoftException e) {
			LOGGER.info(e);
		}

		LOGGER.info("============SqlInitTask=====run====end===");
	}
}
