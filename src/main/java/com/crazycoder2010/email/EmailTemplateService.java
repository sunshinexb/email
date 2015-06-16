package com.crazycoder2010.email;

import java.util.Map;

/**
 * 邮件模板服务
 *
 * 邮件的内容采用了模板技术来实现， 定义一个统一的顶层接口getText，对于不同的模板技术实现Freemarker或Velocity分别实现该方法
 * @author Administrator
 *
 */
public interface EmailTemplateService {
	/**
	 * 模板引擎初始化
	 */
	public void init();
	/**
	 * 獲取模板內容
	 * @param templateId
	 * @param parameters
	 * @return
	 */
	public abstract String getText(String templateId,  Map<Object, Object> parameters);

}
