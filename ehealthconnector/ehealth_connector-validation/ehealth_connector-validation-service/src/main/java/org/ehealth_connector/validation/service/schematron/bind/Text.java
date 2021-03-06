//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.04.08 at 10:09:10 AM CEST
//

package org.ehealth_connector.validation.service.schematron.bind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="langText" type="{http://purl.oclc.org/dsdl/svrl}langTextType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "text")
public class Text {

	@XmlElementRef(name = "langText", namespace = "http://purl.oclc.org/dsdl/svrl", type = JAXBElement.class, required = false)
	@XmlMixed
	protected List<Serializable> content;
	@XmlAttribute(name = "lang")
	protected String lang;

	/**
	 * Gets the value of the content property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the content property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link LangTextType }{@code >} {@link String
	 * }
	 *
	 *
	 */
	public List<Serializable> getContent() {
		if (content == null) {
			content = new ArrayList<Serializable>();
		}
		return this.content;
	}

	/**
	 * Gets the value of the lang property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLang() {
		return lang;
	}

	public String getValue() {
		if (this.content.size() > 1) {
			Iterator<Serializable> iter = this.content.iterator();
			while (iter.hasNext()) {
				Object tempObj = iter.next();
				if (tempObj instanceof JAXBElement) {
					LangTextType tempObj2 = ((JAXBElement<LangTextType>) tempObj).getValue();
					return tempObj2.getValue();
				}

			}
		} else {
			return content.get(0).toString();
		}

		return "";

	}

	/**
	 * Sets the value of the lang property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLang(String value) {
		this.lang = value;
	}

}
