package com.crazycoder2010.email;

/**
 * 邮件发送门面类，用于客户端直接调用
 *
 * 邮件发送模块对外暴露的外部接口，用来封装各个底层实现细节
 * @author Administrator
 *
 */
public class EmailSendFacade {
	private EmailTemplateService emailTemplateService;
	private EmailServer emailServer;

	public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}

	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}

	/**
	 * 发送邮件
	 * @param emailInfo 邮件参数封装，emailInfo的title和content字段的值将被重置为实际的值
	 */
	public void send(EmailInfo emailInfo){
		String title = emailTemplateService.getText(emailInfo.getTemplateId()+"-title", emailInfo.getParameters());
		String content = emailTemplateService.getText(emailInfo.getTemplateId()+"-body", emailInfo.getParameters());
		emailInfo.setContent(content);
		emailInfo.setTitle(title);

		emailServer.send(emailInfo);
	}
}
