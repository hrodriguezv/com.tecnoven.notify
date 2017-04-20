/**
 * 
 */
package com.tecnoven.notify.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tecnoven.notify.ui.core.func.ICatalogueObject;


/**
 * 
 * 
 * @version 1.0 (May 03, 2009)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 * 
 * @hibernate.class table="template_def"
 */
public class TemplateDefinitionData implements Serializable, ICatalogueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -779489216262127247L;

	private String customCode;

	private String description;

	private String name;

	private Set<TagData> tags;

	private long templateDefId;

	private boolean tagsHasPk;

	public TemplateDefinitionData() {
		super();
	}

	/**
	 * @return the customCode
	 * @hibernate.property column="custom_code" length="20" not-null="false"
	 */
	public String getCustomCode() {
		return customCode;
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
	 * @hibernate.property column="name" not-null="true"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tags
	 * @hibernate.set lazy="false" inverse="true"
	 * @hibernate.collection-key column="id_template_def"
	 *                           foreign-key="template_fk1_tag"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.tecnoven.notify.domain.TagData"
	 */
	public Set<TagData> getTags() {
		return tags;
	}

	/**
	 * @return the templateDefId
	 * @hibernate.id generator-class="sequence" column="id_template_def"
	 * @hibernate.generator-param name="sequence" value="s_template_def"
	 */
	public long getTemplateDefId() {
		return templateDefId;
	}

	/**
	 * @param customCode
	 *            the customCode to set
	 */
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
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
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Set<TagData> tags) {
		this.tags = tags;
	}

	/**
	 * @param templateDefId
	 *            the templateDefId to set
	 */
	public void setTemplateDefId(long templateDefId) {
		this.templateDefId = templateDefId;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return this.templateDefId;
	}

	/**
	 * @return the tagsHasPk
	 * @hibernate.property column="tags_with_pk"
	 */
	public boolean isTagsHasPk() {
		return this.tagsHasPk;
	}

	/**
	 * @param tagsHasPk
	 *            the tagsHasPk to set
	 */
	public void setTagsHasPk(boolean tagsHasPk) {
		this.tagsHasPk = tagsHasPk;
	}

	public TagData getPKTagData() {
		if (this.isTagsHasPk()) {
			for (TagData tag : this.getTags()) {
				if (tag.isPk()) {
					return tag;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this.templateDefId,
				((TemplateDefinitionData) obj).templateDefId).append(
				this.customCode, ((TemplateDefinitionData) obj).customCode)
				.append(this.description,
						((TemplateDefinitionData) obj).description).append(
						this.name, ((TemplateDefinitionData) obj).name).append(
						this.tagsHasPk,
						((TemplateDefinitionData) obj).tagsHasPk).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.templateDefId).append(
				this.customCode).append(this.description).append(this.name)
				.append(this.tagsHasPk).toHashCode();
	}

}
