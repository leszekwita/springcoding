/*
 *     Copyright 2014 iApps Technologies GmbH.
 *     http://www.iapps-technologies.com
 */
package radzik.michal.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@Component
public final class Emailer {

	private static final Logger logger = LoggerFactory.getLogger(Emailer.class);

	@Autowired
	private Environment env;
	
	private String getEmailFooter() {
		String footer = "";
		footer += "<br/><br/><hr/>";
		footer += "Sent from localhost";
		footer += "<br/><hr/>";
		return footer;
	}	
	
	/**
	 * Send a single email.
	 * @throws javax.mail.MessagingException 
	 * @throws AddressException 
	 */
	public void sendEmail(List<String> aToEmailsAddr, String aSubject, String aBody) throws MessagingException, AddressException, javax.mail.MessagingException {
		sendEmail(aToEmailsAddr, aSubject, aBody, null, null);
	}
	
	
	private void sendEmail(List<String> aToEmailsAddr, String aSubject, String aBody, Map<String, String> attachments,
			String imagePath) throws MessagingException, AddressException, javax.mail.MessagingException {
		
		if (aToEmailsAddr == null || aToEmailsAddr.isEmpty()) {
			return;
		}

		Properties props = new Properties();
		props.put("mail.transport.protocol", env.getProperty("email.protocol"));
		props.put("mail.smtp.port", env.getProperty("email.port"));
		props.put("mail.smtp.host", env.getProperty("email.host"));
		
		//String debug = env.getProperty("email.debug");
		String emailUser = env.getProperty("email.user");
		String emailFrom = env.getProperty("email.from");
		String emailHost = env.getProperty("email.host");
		String emailPassword = env.getProperty("email.password");
		
		if (emailUser != null && !emailUser.trim().isEmpty()) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.user", emailUser);
			props.put("mail.smtp.password", emailPassword);
		}
		
		Session mailSession = Session.getDefaultInstance(props, null);		
		Transport transport = mailSession.getTransport(env.getProperty("email.protocol"));
		MimeMessage message = new MimeMessage(mailSession);
		
		try {

			if (emailFrom != null && !emailFrom.trim().isEmpty()) {
				message.setFrom(new InternetAddress(emailFrom, "Michal Emailer"));
			} else if (emailUser != null && !emailUser.trim().isEmpty()) {
				message.setFrom(new InternetAddress(emailUser, "Michal Emailer"));
			} else {
				message.setFrom(new InternetAddress(emailHost, "Michal Emailer"));
			}
		} catch (UnsupportedEncodingException ex) {
			logger.error("UnsupportedEncodingException thrown");
			message.setFrom(new InternetAddress(emailUser));
		}
		String prefix = env.getProperty("email.subject_prefix");
		if (prefix != null && !prefix.isEmpty()) {
			aSubject = prefix.concat(aSubject);
		}
		message.setSubject(aSubject);
		aBody += getEmailFooter();
		
		message.setContent(aBody, "text/html; charset=UTF-8");
		for (String addr : aToEmailsAddr) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(addr));
		}
		
		if (emailUser != null && !emailUser.trim().isEmpty()) {
			transport.connect(emailHost, emailUser, emailPassword);
		} else {
			transport.connect();
		}
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
		
	}
}
