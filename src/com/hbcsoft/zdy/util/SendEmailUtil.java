package com.hbcsoft.zdy.util;

import org.apache.log4j.Logger;

import com.hbcsoft.exception.HbcsoftException;
import com.yaja.email.tool.MailSender;

/**
 * 发邮件服务
 * 
 * @author zhangdengyu
 *
 */
public class SendEmailUtil {

	private final static Logger LOGGER = Logger.getLogger(SendEmailUtil.class);

	/**
	 * 发送类型 html格式
	 */
	public final static String SEND_TYPE_HTML = "html";
	/**
	 * 发送类型 文本格式
	 */
	public final static String SEND_TYPE_TEXT = "text";
	
	private final static String METHOD = "sendEmail";

	/**
	 * 发送邮件
	 * 
	 * @param emailType
	 *            邮件类型
	 * @param sendType
	 *            发送类型
	 * @param strContent
	 *            发送内容
	 * @param strSubject
	 *            邮件标题
	 * @throws Exception
	 *             异常
	 */
	public void sendEmail(final SendMailEntity en) throws HbcsoftException {
		LOGGER.info("============sendEmail=========start===");

		checkParameter(en);
		
		boolean bFlag;
		
		// html格式
		if (SEND_TYPE_HTML.equals(en.getSendType())) {
			bFlag = new MailSender().sendHtmlMail(en.copy());
		} else if (SEND_TYPE_TEXT.equals(en.getSendType())) {
			bFlag = new MailSender().sendTextMail(en.copy());
		}else{
			bFlag = false;
		}

		if (!bFlag) {
			LOGGER.info("============邮件发送失败===");
			throw new HbcsoftException(this.getClass().getName(), -2000, "sendEmail", "邮件发送失败!");
		}
		LOGGER.info("============邮件发送成功===");
		LOGGER.info("============sendEmail=========end===");
	}
	
	private void checkParameter(final SendMailEntity en) throws HbcsoftException
	{
		LOGGER.info("============sendType=========>" + en.getSendType() + "<===");
		if (!SEND_TYPE_HTML.equals(en.getSendType()) && !SEND_TYPE_TEXT.equals(en.getSendType())) {
			LOGGER.info("============发送类型不存在===");
			throw new HbcsoftException(this.getClass().getName(), -2000, METHOD, "发送类型不存在!");
		}

		LOGGER.info("============strContent=========>" + en.getSendType() + "<===");
		if ("".equals(en.getContent()) || en.getContent() == null) {
			LOGGER.info("============邮件内容不能为空===");
			throw new HbcsoftException(this.getClass().getName(), -2000, METHOD, "邮件内容不能为空!");
		}

		LOGGER.info("============strSubject=========>" + en.getSubject() + "<===");
		if ("".equals(en.getSubject()) || en.getSubject() == null) {
			LOGGER.info("============邮件标题不能为空===");
			throw new HbcsoftException(this.getClass().getName(), -2000, METHOD, "邮件标题不能为空!");
		}

		if (en.getToAddress().size() < 1) {
			LOGGER.info("============收件人邮箱为空===");
			throw new HbcsoftException(this.getClass().getName(), -2000, METHOD, "收件人邮箱为空!");
		}
	}
}
