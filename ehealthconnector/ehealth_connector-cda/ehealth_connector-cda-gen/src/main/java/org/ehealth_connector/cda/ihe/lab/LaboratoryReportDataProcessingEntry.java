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
 * Year of publication: 2016
 *
 *******************************************************************************/
package org.ehealth_connector.cda.ihe.lab;

import org.openhealthtools.mdht.uml.cda.ihe.lab.LABFactory;

/**
 * The Class LaboratoryReportDataProcessingEntry. One Laboratory Report Data
 * Processing Entry SHALL be present in each leaf section of the report.
 */
public class LaboratoryReportDataProcessingEntry
		extends AbstractLaboratoryReportDataProcessingEntry {

	/**
	 * Instantiates a new laboratory report data processing entry.
	 */
	protected LaboratoryReportDataProcessingEntry() {
		super(LABFactory.eINSTANCE.createLaboratoryReportDataProcessingEntry().init());
	}

	/**
	 * Instantiates a new laboratory report data processing entry.
	 *
	 * @param mdht
	 *            the mdht
	 */
	protected LaboratoryReportDataProcessingEntry(
			org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryReportDataProcessingEntry mdht) {
		super(mdht);
	}

	/**
	 * Gets the specimen act.
	 *
	 * @return the specimen act
	 */
	protected org.ehealth_connector.cda.ihe.lab.SpecimenAct getSpecimenAct() {
		if (getMdht().getAct() != null) {
			return new SpecimenAct(
					(org.openhealthtools.mdht.uml.cda.ihe.lab.SpecimenAct) getMdht().getAct());
		}
		return null;
	}

	/**
	 * Gets the specimen act ihe.
	 *
	 * @return the specimen act ihe
	 */
	protected SpecimenAct getSpecimenActIhe() {
		if (getMdht().getAct() != null) {
			return new SpecimenAct(
					(org.openhealthtools.mdht.uml.cda.ihe.lab.SpecimenAct) getMdht().getAct());
		}
		return null;
	}

	/**
	 * Sets the specimen act.
	 *
	 * @param specimenAct
	 *            the new specimen act
	 */
	protected void setSpecimenAct(
			org.ehealth_connector.cda.ihe.lab.AbstractSpecimenAct specimenAct) {
		getMdht().setAct(specimenAct.getMdht());
	}
}
