package com.crazycoder2010.email;

import java.util.HashMap;
import java.util.Map;


/**
 * 邮件实体对象
 */
public class EmailInfo{
	/**
	 * 邮件ID(DB)
	 */
	private long id;
	/**
	 * 发件人
	 */
	private String from;
	/**
	 * 收件人
	 */
	private String[] to;
	/**
	 * 抄送的人
	 */
	private String[] cc;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 * 邮件标题
	 */
	private String title;
	/**
	 * 模板id
	 */
	private String templateId;
	/**
	 * 邮件中的参数
	 */
	private Map<Object, Object> parameters = new HashMap<Object, Object>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<Object, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<Object, Object> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(Object key,Object value){
		this.parameters.put(key, value);
	}
	public void removeParameter(Object key){
		this.parameters.remove(key);
	}
}
