/**
 * 
 */
package com.tecnoven.notify.ui.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import javax.xml.rpc.ServiceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;


import com.sistelecvoice.WSSendMessages.MService;
import com.sistelecvoice.WSSendMessages.MServiceLocator;
import com.sistelecvoice.WSSendMessages.MServiceSoap;
import com.sistelecvoice.WSSendMessages.Respuesta;
import com.tecnoven.notify.controller.LicenseManager;
import com.tecnoven.notify.domain.NotificationData;
import com.tecnoven.notify.domain.RecordData;
import com.tecnoven.notify.mail.MailFactory;
import com.tecnoven.notify.util.Constants;
import com.tecnoven.notify.util.FileUtil;

/**
 * @author hector
 *
 */
public class LongTask {
	
	private int lengthOfTask;

	private int current = 0;

	private int currentSMS = 0;
	
	private boolean done = false;

	private boolean canceled = false;

	private String statMessage;

	private String resultMessage;

	private MailFactory sender;

	private ArrayList<RecordData> listOfSender;

	private Session session;

	private ArrayList<String> listError = new ArrayList<String>();

	private ArrayList<String> listMessages = new ArrayList<String>();

	private ArrayList<File> listOfFiles;
	
	private String pathFilePersonal;

	private NotificationData notification;

	private MService service;
	
	private MServiceSoap port;

	public LongTask(MailFactory sender, ArrayList<RecordData> list, Session s,
			ArrayList<File> files, String path, NotificationData not) {
		this.sender = sender;
		this.listOfSender = list;
		this.lengthOfTask = list.size();
		this.session = s;
		this.listOfFiles = files;
		this.pathFilePersonal = path;
		this.notification = not;
		// Make a service
		service = new MServiceLocator();
		// Now use the service to get a stub which implements the SDI.
		try {
			port = service.getmServiceSoap();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getListMessageError() {
		return listMessages;
	}

	public ArrayList<String> getListError() {
		return listError;
	}

	/**
	 * Called from ProgressBarDemo to start the task.
	 */
	public void go() {
		final SwingWorkerExtended worker = new SwingWorkerExtended() {
			public Object construct() {
				current = 0;
				done = false;
				canceled = false;
				statMessage = null;
				return new CurrentTask();
			}
		};
		worker.start();
	}

	/**
	 * Called from ProgressBarDemo to find out how much work needs to be done.
	 */
	public int getLengthOfTask() {
		return lengthOfTask;
	}

	public void setLengthOfTask(int lenght) {
		lengthOfTask = lenght;
	}

	/**
	 * Called from ProgressBarDemo to find out how much has been done.
	 */
	public int getCurrent() {
		if(notification.getType().equals(Constants.EMAIL_SERVICE)){
			return current;
		}else{
			return currentSMS;
		}
	}

	public void stop() {
		canceled = true;
		statMessage = null;
	}

	/**
	 * Called from ProgressBarDemo to find out if the task has completed.
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Returns the most recent status message, or null if there is no current
	 * status message.
	 */
	public String getMessage() {
		return statMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public int getLengthOfSMSTask() {
		return currentSMS;
	}

	/**
	 * The current long running task. This runs in a SwingWorkerExtended thread.
	 */
	class CurrentTask {
		@SuppressWarnings("unchecked")
		CurrentTask() {
			String messageException = "";
			String messageUser = "";
			// Fake a long task,
			// making a random amount of progress every second.
			while (!canceled && !done) {
				statMessage = "Enviando  " + (current + 1) + " de "
				+ lengthOfTask + ".";
				try {
					RecordData id = listOfSender.get(current);
					try {
						Criteria critRecord = session.createCriteria(RecordData.class);
						
						String dataToBeSent = notification.getTemplate().getTemplateBody();
						
						critRecord.add(Expression.eq("notification", notification));
						critRecord.add(Expression.eq("PKValue", id.getPKValue()));
						List<RecordData> records = critRecord.list();
						for (RecordData data : records) {
							dataToBeSent = dataToBeSent.replace(data.getTag().getTag(), data.getValue());
						}
						
						if(notification.getType().equals(Constants.EMAIL_SERVICE)){
							MimeMessage msg;
							String idToSent = id.getPKValue();
							if (Constants.IS_APPEND_DOMAIN){
								idToSent = FileUtil.getIdWithoutDomain(id.getPKValue());
							}
							ArrayList<File> allListOfFiles = FileUtil.getFilesById(pathFilePersonal, idToSent);
							if (listOfFiles != null) {
								if (allListOfFiles != null) {
									allListOfFiles.addAll(listOfFiles);
								} else {
									allListOfFiles = new ArrayList<File>(
											listOfFiles);
								}
							} 
							msg = sender.getMessage(id.getPKValue(), notification.getTemplate().getSubject(), dataToBeSent, allListOfFiles);

							sender.sendMessage(msg);
						}else{
							String userSMS = LicenseManager.getInstance().getLicense().getSmsUser();
							String pwdSMS = LicenseManager.getInstance().getLicense().getSmsPwd();
							Respuesta answer = null;
							if (dataToBeSent.length() > Constants.MAX_LENGHT_SMS_MESSAGE) {
								int totalPages = new Double(Math.ceil(Double.valueOf(Double.valueOf(dataToBeSent
										.length()) / Double.valueOf(Constants.MAX_LENGHT_SMS_MESSAGE)))).intValue();
								boolean finished = false;
								int countPages = 0, index = 0;
								while (!finished) {
									String token = dataToBeSent.substring(index,
											((index + Constants.MAX_LENGHT_SMS_MESSAGE) > dataToBeSent.length()) ? index = dataToBeSent
													.length() : (index += +Constants.MAX_LENGHT_SMS_MESSAGE));
									String pages = "(" + (++countPages) + "/"
											+ totalPages + ")";
									token = pages + " " + token;
									answer = port.enviarMensaje(userSMS, pwdSMS,
											id.getPKValue(), token);
									if (answer.getCodigo().equals(Constants.SMS_SUCCESS_MESSAGE_SENT)){
										currentSMS++;
									}else{
										resultMessage = "Enviando a :" + id.getPKValue()
										+ "... fall\u00f3";
										listError.add(id.getPKValue());
										listMessages.add(answer.getMensaje());
									}
									finished = (index == dataToBeSent.length());
								}
							} else {
								answer = port.enviarMensaje(userSMS, pwdSMS,
										id.getPKValue(), dataToBeSent);
								if (answer.getCodigo().equals(Constants.SMS_SUCCESS_MESSAGE_SENT)){
									currentSMS++;
								}else{
									resultMessage = "Enviando a :" + id.getPKValue()
									+ "... fall\u00f3";
									listError.add(id.getPKValue());
									listMessages.add(answer.getMensaje());
								}
							}
						}
					} catch (MessagingException e) {
						e.printStackTrace();
						resultMessage = "Enviando a :" + id.getPKValue()
						+ "... fall\u00f3";
						listError.add(id.getPKValue());
						Exception ex = e;
						if (ex instanceof SendFailedException) {
							SendFailedException sfex = (SendFailedException) ex;
							Address[] invalid = sfex.getInvalidAddresses();
							if (invalid != null) {
								messageException = "sendfailedexception";
							}
							Address[] validUnsent = sfex
							.getValidUnsentAddresses();
							if (validUnsent != null) {
								messageException = "validunsentaddrress";
							}
							Address[] validSent = sfex.getValidSentAddresses();
							if (validSent != null) {
								messageException = "validsentadrress";
							}
						}
						messageUser = "" + ex.getMessage();
					} catch (Exception e) {
						e.printStackTrace();
						resultMessage = "Enviando a :" + id.getPKValue()
						+ "... fall\u00f3";
						listError.add(id.getPKValue());
						messageUser = "" + e.getMessage();
					}
					listMessages.add(messageException + messageUser);
					Thread.sleep(1000); // sleep for a second
					current++;
					if (current >= lengthOfTask) {
						done = true;
						current = lengthOfTask;
					}
				} catch (InterruptedException e) {
					System.out.println("ActualTask interrupted");
				}
			}
		}
	}
}
