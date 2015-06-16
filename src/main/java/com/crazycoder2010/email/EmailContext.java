package com.crazycoder2010.email;


/**
 * 邮件发送执行的上下文信息
 *
 * 邮件监听器用到的邮件发送上线文信息，主要有EmailInfo邮件基本信息和Throwable两个字段
 */
public class EmailContext {

	/**
	 * @uml.property  name="emailInfo"
	 */
	private EmailInfo emailInfo;

	/**
	 * Getter of the property <tt>emailInfo</tt>
	 * @return  Returns the emailInfo.
	 * @uml.property  name="emailInfo"
	 */
	public EmailInfo getEmailInfo() {
		return emailInfo;
	}

	/**
	 * Setter of the property <tt>emailInfo</tt>
	 * @param emailInfo  The emailInfo to set.
	 * @uml.property  name="emailInfo"
	 */
	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	/**
	 * @uml.property  name="throwable"
	 */
	private Throwable throwable;

	/**
	 * Getter of the property <tt>throwable</tt>
	 * @return  Returns the throwable.
	 * @uml.property  name="throwable"
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * Setter of the property <tt>throwable</tt>
	 * @param throwable  The throwable to set.
	 * @uml.property  name="throwable"
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
