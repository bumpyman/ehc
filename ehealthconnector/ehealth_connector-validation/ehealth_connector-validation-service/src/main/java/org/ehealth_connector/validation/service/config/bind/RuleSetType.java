//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.04.08 at 08:04:19 AM CEST
//
/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/

package org.ehealth_connector.validation.service.config.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for RuleSetType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="RuleSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="display-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="file" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="persistable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="cacheable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RuleSetType", propOrder = { "description", "displayName", "templateId" })
public class RuleSetType {

	protected String description;
	@XmlElement(name = "display-name")
	protected String displayName;
	protected String templateId;
	@XmlAttribute(name = "file", required = true)
	protected String fileName;
	@XmlAttribute
	protected Boolean persistable;
	@XmlAttribute
	protected Boolean cacheable;

	/**
	 * Gets the value of the description property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the value of the displayName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Gets the value of the fileName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the value of the templateId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * Gets the value of the cacheable property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public boolean isCacheable() {
		if (cacheable == null) {
			return true;
		} else {
			return cacheable;
		}
	}

	/**
	 * Gets the value of the persistable property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public boolean isPersistable() {
		if (persistable == null) {
			return true;
		} else {
			return persistable;
		}
	}

	/**
	 * Sets the value of the cacheable property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setCacheable(Boolean value) {
		this.cacheable = value;
	}

	/**
	 * Sets the value of the description property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Sets the value of the displayName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDisplayName(String value) {
		this.displayName = value;
	}

	/**
	 * Sets the value of the fileName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFileName(String value) {
		this.fileName = value;
	}

	/**
	 * Sets the value of the persistable property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setPersistable(Boolean value) {
		this.persistable = value;
	}

	/**
	 * Sets the value of the templateId property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTemplateId(String value) {
		this.templateId = value;
	}

}
