package com.crazycoder2010.email;

/**
 * 邮件发送器监听程序，一个observer模式的实现，当有邮件要发送时触发，可以为邮件服务器配置一个或多个监听程序，定义了三个核心接口方法
 * @author Administrator
 *
 */
public interface EmailSendListener {
	/**
	 * 邮件发送前做的操作
	 * @param emailInfo
	 */
	public abstract void before(EmailContext emailInfo);
	/**
	 * 邮件发送结束后做的操作
	 * @param emailContext
	 */
	public abstract void after(EmailContext emailContext);

	/**
	 * 邮件发送出现异常时做的处理
	 */
	public abstract void afterThrowable(EmailContext emailContext);
}
