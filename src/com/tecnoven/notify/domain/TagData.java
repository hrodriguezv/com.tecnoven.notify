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
 * @hibernate.class table="tag"
 */
public class TagData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472799735679689734L;

	private String customCode;

	private String name;

	private int tagOrder;

	private boolean pk;

	private String tag;

	private long tagId;

	private TemplateDefinitionData templateDefinition;

	/**
	 * 
	 */
	public TagData() {

	}

	/**
	 * @return the customCode
	 * @hibernate.property column="custom_code" not-null="false" length="20"
	 */
	public String getCustomCode() {
		return customCode;
	}

	/**
	 * @return the name
	 * @hibernate.property column="name" not-null="true" length="100"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the order
	 * @hibernate.property column="tag_order"
	 */
	public int getTagOrder() {
		return tagOrder;
	}

	/**
	 * @return the tag
	 * @hibernate.property column="tag" not-null="false" length="100"
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the tagId
	 * @hibernate.id generator-class="sequence" column="id_tag"
	 * @hibernate.generator-param name="sequence" value="s_tag"
	 */
	public long getTagId() {
		return tagId;
	}

	/**
	 * @return the templateDefinition
	 * @hibernate.many-to-one column = "id_template_def" foreign-key =
	 *                        "template_fk1_tag"
	 */
	public TemplateDefinitionData getTemplateDefinition() {
		return templateDefinition;
	}

	/**
	 * @return the pk
	 * @hibernate.property column="pk"
	 */
	public boolean isPk() {
		return pk;
	}

	/**
	 * @param customCode
	 *            the customCode to set
	 */
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setTagOrder(int order) {
		this.tagOrder = order;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(boolean pk) {
		this.pk = pk;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @param tagId
	 *            the tagId to set
	 */
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	/**
	 * @param templateDefinition
	 *            the templateDefinition to set
	 */
	public void setTemplateDefinition(TemplateDefinitionData templateDefinition) {
		this.templateDefinition = templateDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.tagId, ((TagData) obj).tagId)
				.append(this.tagOrder, ((TagData) obj).tagOrder).append(
						this.customCode, ((TagData) obj).customCode).append(
						this.name, ((TagData) obj).name).append(this.tag,
						((TagData) obj).tag).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.tagId).append(this.tagOrder)
				.append(this.customCode).append(this.name).append(this.tag)
				.toHashCode();
	}

}
