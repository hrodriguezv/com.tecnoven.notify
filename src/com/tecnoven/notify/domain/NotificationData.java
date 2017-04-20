/**
 * 
 */
package com.tecnoven.notify.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * 
 * @version 1.0 (May 03, 2009)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 * 
 * @hibernate.class table="notification"
 */
public class NotificationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388720872113397338L;

	private String type;

	private Date created;

	private long notificationId;

	private int numberNotification;

	private int numberSent;

	private Collection<RecordData> records;

	private TemplateData template;

	/**
	 * 
	 */
	public NotificationData() {

	}

	/**
	 * @return the type
	 * @hibernate.property column="type" not-null="true"
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the created
	 * @hibernate.property column="created" not-null="true"
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the notificationId
	 * @hibernate.id generator-class="sequence" column="id_notification"
	 * @hibernate.generator-param name="sequence" value="s_notification"
	 */
	public long getNotificationId() {
		return notificationId;
	}

	/**
	 * @return the numberNotification
	 * @hibernate.property column="notification_number" not-null="true"
	 */
	public int getNumberNotification() {
		return numberNotification;
	}

	/**
	 * @return the numberSent
	 * @hibernate.property column="sent_number" not-null="true"
	 */
	public int getNumberSent() {
		return numberSent;
	}

	/**
	 * @return the records
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="id_notification"
	 *                           foreign-key="notification_fk1_record"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.tecnoven.notify.domain.RecordData"
	 */
	public Collection<RecordData> getRecords() {
		return records;
	}

	/**
	 * @return the template
	 * @hibernate.many-to-one outer-join="true" not-null="true"
	 * @hibernate.column name="id_template"
	 */
	public TemplateData getTemplate() {
		return template;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @param notificationId
	 *            the notificationId to set
	 */
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * @param numberNotification
	 *            the numberNotification to set
	 */
	public void setNumberNotification(int numberNotification) {
		this.numberNotification = numberNotification;
	}

	/**
	 * @param numberSent
	 *            the numberSent to set
	 */
	public void setNumberSent(int numberSent) {
		this.numberSent = numberSent;
	}

	/**
	 * @param records
	 *            the records to set
	 */
	public void setRecords(Collection<RecordData> records) {
		this.records = records;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(TemplateData template) {
		this.template = template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.notificationId,
				((NotificationData) obj).notificationId).append(
				this.numberNotification,
				((NotificationData) obj).numberNotification).append(
				this.numberSent, ((NotificationData) obj).numberSent).append(
				this.created, ((NotificationData) obj).created).append(
				this.template, ((NotificationData) obj).template).append(
				this.type, ((NotificationData) obj).type).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.notificationId).append(
				this.numberNotification).append(this.numberSent).append(
				this.created).append(this.template)
				.append(this.type).toHashCode();
	}
}
