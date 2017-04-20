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
 * @hibernate.class table="template"
 */

public class TemplateData implements Serializable, ICatalogueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1383193352967181595L;

	private TemplateDefinitionData definition;

	private String description;

	private String name;

	private String subject;

	private String templateBody;

	private long templateId;

	/**
	 * 
	 */
	public TemplateData() {
		super();
	}

	/**
	 * @return the definition
	 * @hibernate.many-to-one outer-join="true" not-null="true"
	 * @hibernate.column name="id_template_def"
	 */
	public TemplateDefinitionData getDefinition() {
		return definition;
	}

	/**
	 * @return the description
	 * @hibernate.property column="description" length="500000" not-null="true"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name
	 * @hibernate.property column="name" length="500" not-null="true"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the subject
	 * @hibernate.property column="subject" not-null="true"
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the templateBody
	 * @hibernate.property column="body" length="500000" not-null="true"
	 */
	public String getTemplateBody() {
		return templateBody;
	}

	/**
	 * @return the templateId
	 * @hibernate.id generator-class="sequence" column="id_template"
	 * @hibernate.generator-param name="sequence" value="s_template"
	 */
	public long getTemplateId() {
		return templateId;
	}

	/**
	 * @param definition
	 *            the definition to set
	 */
	public void setDefinition(TemplateDefinitionData definition) {
		this.definition = definition;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param templateBody
	 *            the templateBody to set
	 */
	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return templateId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.templateId,
				((TemplateData) obj).templateId).append(this.definition,
				((TemplateData) obj).definition).append(this.description,
				((TemplateData) obj).description).append(this.name,
				((TemplateData) obj).name).append(this.subject,
				((TemplateData) obj).subject).append(this.templateBody,
				((TemplateData) obj).templateBody).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.templateId).append(
				this.definition).append(this.description).append(this.name)
				.append(this.subject).append(this.templateBody).toHashCode();
	}

}
