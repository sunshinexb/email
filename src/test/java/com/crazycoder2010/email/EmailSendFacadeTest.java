package com.crazycoder2010.email;

import org.junit.Test;

public class EmailSendFacadeTest {
	@Test
	public void testSend() throws InterruptedException {
		//启动邮件服务器
		EmailServer emailServer = new EmailServer();
		emailServer.init();
		emailServer.addEmailListener(new ConsoleEmailSendListener());
		emailServer.addEmailListener(new DatabaseEmailSendListener());

		//启动模板服务
		EmailTemplateService emailTemplateService = new FreemarkerEmailTemplateService();
		emailTemplateService.init();//模板引擎初始化

		//组装邮件发送门面类
		EmailSendFacade emailSendFacade = new EmailSendFacade();
		emailSendFacade.setEmailServer(emailServer);//注册邮件服务器
		emailSendFacade.setEmailTemplateService(emailTemplateService);//注册模板

		//测试数据
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setFrom("sunshinexb@126.com");
		//emailInfo.setTo(new String[]{"to_01@localhost","to_02@localhost"});
		//emailInfo.setCc(new String[]{"cc_01@localhost","cc_02@localhost"});
		emailInfo.setTo(new String[]{"123456@qq.com","834983172@qq.com"});
		emailInfo.setCc(new String[]{"123456@qq.com","834983172@qq.com"});
		emailInfo.setTemplateId("reset_password");
		emailInfo.addParameter("name", "yanping");
		emailInfo.addParameter("newPassword", "123456");

		//发送
		emailSendFacade.send(emailInfo);
		Thread.sleep(10000);
	}
}
