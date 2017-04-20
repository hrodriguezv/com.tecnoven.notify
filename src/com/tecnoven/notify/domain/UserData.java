/*
 * User.java
 *
 * 1.0 (Sep 12, 2007)
 *
 */

package com.tecnoven.notify.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 *
 * @version 1.0 (Sep 12, 2007)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 * 
 * @hibernate.class table="user_app"
 */

public class UserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1164907577633529465L;

	private String login;
	
	private String password;
	
	private boolean logged;
	
	/**
	 * 
	 */
	public UserData() {
	}

	/**
	 * Getter of <code>login</code> property.
	 * @return <code>login</code> property value.
	 * @see #login
	 * @hibernate.id column="login" length="80" not-null="true" unique ="true" generator-class="assigned"
	 */
	
	public String getLogin() {
		return login;
	}

	/**
	 * Setter of <code>login</code> property.
	 * @param login The <code>login</code> to set.
	 * @see #login
	 */
	public void setLogin(String login) {
		this.login = login;
	
		return;
	}

	/**
	 * Getter of <code>password</code> property.
	 * @return <code>password</code> property value.
	 * @see #password
	 * @hibernate.property column="password" length="120"
	 * 					not-null="true"
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * Setter of <code>password</code> property.
	 * @param password The <code>password</code> to set.
	 * @see #password
	 */
	public void setPassword(String password) {
		this.password = password;
	
		return;
	}

	/**
	 * @return the logged
	 * @hibernate.property column="logged"
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param logged the logged to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		return new EqualsBuilder().append(this.login, ((UserData)arg0).login).append(this.password, ((UserData)arg0).password).append(this.logged, ((UserData)arg0).logged).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.login).append(this.password).append(this.logged).toHashCode();
	}

	
}
