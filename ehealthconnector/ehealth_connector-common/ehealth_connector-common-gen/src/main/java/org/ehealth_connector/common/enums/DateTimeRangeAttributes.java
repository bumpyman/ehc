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
package org.ehealth_connector.common.enums;

/**
 * Represents the XDS metadata attributes, which are related to DateTimeRanges
 *
 */
public enum DateTimeRangeAttributes {

	/**
	 * Time, when the author created the document
	 */
	CREATION_TIME("creationTime"),
	/**
	 * Start time of the service, which led to the document
	 */
	SERVICE_START_TIME("serviceStartTime"),
	/**
	 * End time of the service, which led to the document
	 */
	SERVICE_STOP_TIME("serviceStopTime");

	private String name;

	private DateTimeRangeAttributes(String name) {
		this.name = name;
	}

	/**
	 * Gets the XDS attribute name of the Enum
	 *
	 * @return the name of the attribute
	 */
	public String getName() {
		return name;
	}
}
