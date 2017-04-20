/**
 * 
 */
package com.tecnoven.notify.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tecnoven.notify.ui.core.func.ICatalogueObject;


/**
 * 
 * 
 * @version 1.0 (May 03, 2009)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 * 
 * @hibernate.class table="configuration"
 */
public class ConfigurationData implements Serializable, ICatalogueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809611103143766289L;

	/**
	 * 
	 */
	private long configurationId;

	/**
	 * 
	 */
	private String configName;

	/**
	 * 
	 */
	private String serverName;

	/**
	 * 
	 */
	private String serverPort;

	/**
	 * 
	 */
	private String titleSender;

	/**
	 * 
	 */
	private String mailSender;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private boolean current;

	/**
	 * 
	 */
	public ConfigurationData() {

	}

	/**
	 * @return the configurationId
	 * @hibernate.id generator-class="sequence" column="id_configuration"
	 * @hibernate.generator-param name="sequence" value="s_configuration"
	 */
	public long getConfigurationId() {
		return configurationId;
	}

	/**
	 * @param configurationId
	 *            the configurationId to set
	 */
	public void setConfigurationId(long configurationId) {
		this.configurationId = configurationId;
	}

	/**
	 * @return the current
	 * @hibernate.property column="current"
	 */
	public boolean isCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(boolean current) {
		this.current = current;
	}

	/**
	 * @return the mailSender
	 * @hibernate.property column="mail_sender" length="100"
	 */
	public String getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return the serverName
	 * @hibernate.property column="server_name" length="100"
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName
	 *            the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the serverPort
	 * @hibernate.property column="server_port" length="5"
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the titleSender
	 * @hibernate.property column="title_sender" length="100"
	 */
	public String getTitleSender() {
		return titleSender;
	}

	/**
	 * @param titleSender
	 *            the titleSender to set
	 */
	public void setTitleSender(String titleSender) {
		this.titleSender = titleSender;
	}

	/**
	 * @return the password
	 * @hibernate.property column="password" length="200"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the configName
	 * @hibernate.property column="config_name" length="200"
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * @param configName
	 *            the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public long getId() {
		return this.getConfigurationId();
	}

	public String getName() {
		return this.getConfigName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.configurationId,
				((ConfigurationData) obj).configurationId).append(this.current,
				((ConfigurationData) obj).current).append(this.configName,
				((ConfigurationData) obj).configName).append(this.mailSender,
				((ConfigurationData) obj).mailSender).append(this.password,
				((ConfigurationData) obj).password).append(this.serverName,
				((ConfigurationData) obj).serverName).append(this.serverPort,
				((ConfigurationData) obj).serverPort).append(this.titleSender,
				((ConfigurationData) obj).titleSender).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.configurationId).append(
				this.current).append(this.configName).append(this.mailSender)
				.append(this.password).append(this.serverName).append(
						this.serverPort).append(this.titleSender).toHashCode();
	}

}
