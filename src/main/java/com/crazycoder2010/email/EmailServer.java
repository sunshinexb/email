package com.crazycoder2010.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件服务器
 *
 * 邮件服务器，用来进行邮件服务器的配置和实际的邮件发送，这里调用底层的javamail实现，核心方法
 *
 * EmailServer是一个典型的模板模式和观察者模式的应用，
 * 模板send方法中采用java线程池技术ExcecuteService,
 * 在初始化时初始大小为5的线程池，以后每次发送邮件都开启一个新的任务来执行，
 * 每发送一个邮件都依次执行EmailSendListener的before,after,afterThrowable方法，
 * 从来可以灵活扩展邮件发送的处理逻辑，如默认情况下我们可能只是想要跟踪一下邮件的发送过程，
 * 在邮件的发送开始，结束和异常出现时打印出一些基本信息(ConsoleEmailSendListener),
 * 实际生产环境时，我们希望把发送失败的邮件和失败的原因记录到数据库，
 * 以存后期重发用，这个时候我们就可以提供另一个实现类(DatabaseEmailSendListener)来达到这个效果了，
 * 而对于我们整个EmailSever不需要做任何改动，从而达到开闭的原则
 *
 *
 * @author Administrator
 *
 */
public class EmailServer {
	private static final int POOL_SIZE = 5;
	private Session session;
	private ExecutorService theadPool;


	/**
	 * 邮件监听器
	 */
	private List<EmailSendListener> emailSendListeners = new ArrayList<EmailSendListener>();

	public void init() {
		//读取配置文件
		final Properties properties = SysConfig.getConfiguration();
		//初始化线程池
		this.theadPool = Executors.newFixedThreadPool(POOL_SIZE);
		this.session = Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								properties.getProperty("mail.smtp.username"),
								properties.getProperty("mail.smtp.password"));
					}
				});
		this.session.setDebug(true);//生产环境把其设置为false
	}

	/**
	 * 发送单条email
	 *
	 * @param emailInfo
	 */
	public void send(final EmailInfo emailInfo) {
		this.theadPool.execute(new Runnable() {
			public void run() {
				EmailContext emailContext = new EmailContext();
				emailContext.setEmailInfo(emailInfo);
				doBefore(emailContext);
				try {
					Message msg = buildEmailMessage(emailInfo);
					Transport.send(msg);
					doAfter(emailContext);
				} catch (Exception e) {
					emailContext.setThrowable(e);
					doAfterThrowable(emailContext);
				}
			}
		});
	}

	private Message buildEmailMessage(EmailInfo emailInfo)
			throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(this.session);
		message.setFrom(convertString2InternetAddress(emailInfo.getFrom()));
		message.setRecipients(Message.RecipientType.TO,
				converStrings2InternetAddresses(emailInfo.getTo()));
		message.setRecipients(Message.RecipientType.CC,
				converStrings2InternetAddresses(emailInfo.getCc()));

		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailInfo.getContent(), "text/html;charset=UTF-8");
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		message.setSubject(emailInfo.getTitle());
		message.saveChanges();
		return message;
	}

	private InternetAddress convertString2InternetAddress(String address) throws AddressException {
		return new InternetAddress(address);
	}

	private InternetAddress[] converStrings2InternetAddresses(String[] addresses) throws AddressException {
		final int len = addresses.length;
		InternetAddress[] internetAddresses = new InternetAddress[len];
		for (int i = 0; i < len; i++) {
			internetAddresses[i] = convertString2InternetAddress(addresses[i]);
		}
		return internetAddresses;
	}

	public void addEmailListener(EmailSendListener emailSendListener) {
		this.emailSendListeners.add(emailSendListener);
	}

	/**
	 * 发送多条email
	 *
	 * @param emailInfos
	 */
	public void send(List<EmailInfo> emailInfos) {
		for (EmailInfo emailInfo : emailInfos) {
			send(emailInfo);
		}
	}

	private void doBefore(EmailContext emailContext) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.before(emailContext);
		}
	}

	private void doAfter(EmailContext emailContext) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.after(emailContext);
		}
	}

	private void doAfterThrowable(EmailContext emailContext) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.afterThrowable(emailContext);
		}
	}
}
