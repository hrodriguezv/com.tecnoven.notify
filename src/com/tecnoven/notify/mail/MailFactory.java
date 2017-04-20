/*
 * MailFactory.java
 *
 * 1.0 (Aug 25, 2007)
 *
 */
package com.tecnoven.notify.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.tecnoven.notify.domain.ConfigurationData;
import com.tecnoven.notify.util.CryptoLibrary;


/**
 * 
 * 
 * @version 1.0 (Aug 25, 2007)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 */
public class MailFactory {

	/**
	 * 
	 */
	private Session session;

	/**
	 * 
	 */
	private String from;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private String subject;

	/**
	 * 
	 * @param config
	 */
	public MailFactory(ConfigurationData config) {
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap
				.getDefaultCommandMap();
		mc
				.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc
				.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc
				.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc
				.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc
				.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);

		from = config.getMailSender();
		title = config.getTitleSender();

		Properties prop = new Properties();
		prop.put("mail.smtp.host", config.getServerName());
		prop.put("mail.smtp.port", config.getServerPort());

		// gmail
		prop.put("mail.smtp.starttls.enable", "true");

		prop.put("mail.smtp.auth", "true");
		try {
			CryptoLibrary localEncrypter = new CryptoLibrary();
			String plainPassword = localEncrypter.decrypt(config.getPassword());
			SMTPAuthentication auth = new SMTPAuthentication(config
					.getMailSender(), plainPassword);
			this.session = Session.getInstance(prop, auth);
			this.session.setDebug(true);
		} catch (Exception e) {
			ExceptionManager.ManageException(e);
		}
	}

	/**
	 * 
	 * @param msg
	 * @throws MessagingException
	 */
	public void sendMessage(Message msg) throws MessagingException {
		Transport.send(msg);
	}

	/**
	 * 
	 * @param from
	 * @param title
	 * @param to
	 * @param message
	 * @return
	 */
	public MimeMessage getMessage(String from, String title, String to,
			String message) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(message
					.toString(), "text/html")));
			msg.setText(message);
			msg.setSubject(subject);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setFrom(new InternetAddress(from, title));
			return msg;
		} catch (java.io.UnsupportedEncodingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (MessagingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (IOException e) {
			ExceptionManager.ManageException(e);
			return null;
		}
	}

	/**
	 * 
	 * @param to
	 * @param message
	 * @return
	 */
	public MimeMessage getMessage(String to, String message) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(message
					.toString(), "text/html")));
			msg.setText(message);
			msg.setSubject(subject);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setFrom(new InternetAddress(this.from, this.title));
			return msg;
		} catch (java.io.UnsupportedEncodingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (MessagingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (IOException e) {
			ExceptionManager.ManageException(e);
			return null;
		}
	}

	/**
	 * 
	 * @param to
	 * @param subjectMail
	 * @param message
	 * @return
	 */
	public MimeMessage getMessage(String to, String subjectMail, String message) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(message
					.toString(), "text/html")));
			msg.setText(message);
			msg.setSubject(subjectMail);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setFrom(new InternetAddress(this.from, this.title));
			return msg;
		} catch (java.io.UnsupportedEncodingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (MessagingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (IOException e) {
			ExceptionManager.ManageException(e);
			return null;
		}
	}

	/**
	 * 
	 * @param to
	 * @param subjectMail
	 * @param message
	 * @param filesAttached
	 * @return
	 */
	public MimeMessage getMessage(String to, String subjectMail,
			String message, ArrayList<java.io.File> filesAttached) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(message, "text/html")));
			msg.setSubject(subjectMail);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setFrom(new InternetAddress(this.from, this.title));

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(message);
			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			if (filesAttached == null){
				// add the Multipart to the message
				msg.setContent(mp);
				// set the Date: header
				msg.setSentDate(new Date());
				return msg;
			}

			for (File file : filesAttached) {
				// create the second message part
				MimeBodyPart mbp2 = new MimeBodyPart();
				// attach the file to the message
				FileDataSource fds = new FileDataSource(file);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}
			// add the Multipart to the message
			msg.setContent(mp);
			// set the Date: header
			msg.setSentDate(new Date());
			return msg;
		} catch (java.io.UnsupportedEncodingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (MessagingException ex) {
			ExceptionManager.ManageException(ex);
			return null;
		} catch (IOException e) {
			ExceptionManager.ManageException(e);
			return null;
		}
	}

}

/**
 * 
 * 
 * @version 1.0 (Aug 25, 2007)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 */
class SMTPAuthentication extends javax.mail.Authenticator {

	private String userName;

	private String passwd;

	SMTPAuthentication(String user, String pwd) {
		this.userName = user;
		this.passwd = pwd;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.userName, this.passwd);
	}
}

/**
 * 
 * 
 * @version 1.0 (Aug 25, 2007)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 */
class ExceptionManager {
	public static void ManageException(Exception e) {
		System.out.println("Se ha producido una exception");
		System.out.println(e.getMessage());
		e.printStackTrace(System.out);
	}
}