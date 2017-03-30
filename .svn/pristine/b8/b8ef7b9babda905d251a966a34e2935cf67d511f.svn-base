package com.hbcsoft.zdy.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hbcsoft.zdy.task.SqlInitTask;

/**
 * 系统参数初始化
 * 
 * @author zhangdengyu
 *
 */
@WebListener
public class SqlInitListener implements ServletContextListener {
	private transient Timer timer = null;

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		timer.cancel();
	}

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		timer = new Timer(true);
		timer.schedule(new SqlInitTask(), new Date(), 60 * 60 * 1000);
	}
}