package com.crazycoder2010.email;

/**
 * 基于数据库的邮件监听程序，用来把邮件的状态保存到db
 * @author Administrator
 *
 */
public class DatabaseEmailSendListener implements EmailSendListener {

	public void before(EmailContext emailInfo) {
		System.out.println("Enter DatabaseEmailSendListener");
	}

	public void after(EmailContext emailContext) {
		System.out.println("update email send status to db.");
	}

	public void afterThrowable(EmailContext emailContext) {
		System.out.println("Save error email to db");
	}

}
