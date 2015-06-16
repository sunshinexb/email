package com.crazycoder2010.email;

/**
 * 默认的邮件发送事件处理类-打印一些程序执行信息到控制台
 *
 * @author Administrator
 */
public class ConsoleEmailSendListener implements EmailSendListener {
	public void before(EmailContext emailInfo) {
		System.out.println("Start to send mail:"+emailInfo.getEmailInfo());
	}

	public void after(EmailContext emailContext) {
		System.out.println("End of sending "+emailContext.getEmailInfo());
	}

	public void afterThrowable(EmailContext emailContext) {
		System.out.println("Error Occurs when sending email "+ emailContext.getEmailInfo()+",exception:"+emailContext.getThrowable());
	}

}
