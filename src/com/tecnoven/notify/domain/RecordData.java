/**
 * 
 */
package com.tecnoven.notify.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * 
 * @version 1.0 (May 03, 2009)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 * 
 * @hibernate.class table="record"
 */
public class RecordData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1517844897348724555L;

	private NotificationData notification;

	private String PKValue;

	private long recordId;

	private TagData tag;

	private String value;

	/**
	 * 
	 */
	public RecordData() {

	}

	/**
	 * @return the notification
	 * @hibernate.many-to-one column = "id_notification" foreign-key =
	 *                        "notification_fk1_record"
	 */
	public NotificationData getNotification() {
		return notification;
	}

	/**
	 * @return the pKValue
	 * @hibernate.property column="pk" not-null="false"
	 */
	public String getPKValue() {
		return PKValue;
	}

	/**
	 * @return the recordId
	 * @hibernate.id generator-class="sequence" column="id_record"
	 * @hibernate.generator-param name="sequence" value="s_record"
	 */
	public long getRecordId() {
		return recordId;
	}

	/**
	 * @return the tag
	 * @hibernate.many-to-one outer-join="true" not-null="true"
	 * @hibernate.column name="id_tag"
	 */
	public TagData getTag() {
		return tag;
	}

	/**
	 * @return the value
	 * @hibernate.property column="value" not-null="false"
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(NotificationData notification) {
		this.notification = notification;
	}

	/**
	 * @param value
	 *            the pKValue to set
	 */
	public void setPKValue(String value) {
		PKValue = value;
	}

	/**
	 * @param recordId
	 *            the recordId to set
	 */
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(TagData tag) {
		this.tag = tag;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.recordId,
				((RecordData) obj).recordId).append(this.notification,
				((RecordData) obj).notification).append(this.PKValue,
				((RecordData) obj).PKValue).append(this.tag,
				((RecordData) obj).tag).append(this.value,
				((RecordData) obj).value).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.recordId).append(
				this.notification).append(this.PKValue).append(this.tag)
				.append(this.value).toHashCode();
	}

}
