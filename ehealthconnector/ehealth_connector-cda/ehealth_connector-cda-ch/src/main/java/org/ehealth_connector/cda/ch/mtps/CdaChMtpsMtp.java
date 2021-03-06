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

package org.ehealth_connector.cda.ch.mtps;

import org.ehealth_connector.cda.ch.AbstractCdaCh;
import org.ehealth_connector.cda.ihe.pharm.MedicationFullfillmentInstructionsEntry;
import org.ehealth_connector.cda.ihe.pharm.MedicationTreatmentPlanItemEntry;
import org.ehealth_connector.cda.ihe.pharm.MedicationTreatmentPlanSection;
import org.ehealth_connector.cda.ihe.pharm.PatientMedicalInstructionsEntry;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.enums.LanguageCode;
import org.openhealthtools.mdht.uml.cda.Reference;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;

/**
 * The Class CdaChMtpsMtp. see also CDA CH MTPS 7.4.2.3
 */
public class CdaChMtpsMtp extends AbstractCdaCh<org.openhealthtools.mdht.uml.cda.ch.CdaChMtpsMtp> {

	/**
	 * Instantiates a new cda ch mtps mtp.
	 */
	public CdaChMtpsMtp() {
		this(LanguageCode.ENGLISH);
	}

	/**
	 * Instantiates a new cda ch mtps mtp.
	 *
	 * @param languageCode
	 *            the language code
	 */
	public CdaChMtpsMtp(LanguageCode languageCode) {
		super(CHFactory.eINSTANCE.createCdaChMtpsMtp().init());
		this.setLanguageCode(languageCode);
		super.initCda();
		switch (this.getLanguageCode()) {
		case GERMAN:
			this.setTitle("Medikamentöser Behandlungsplan");
			break;
		case FRENCH:
			setTitle("Plan de traitement médicamenteux");
			break;
		case ITALIAN:
			setTitle("Piano terapeutico farmacologico");
			break;
		case ENGLISH:
			setTitle("Medication Treatment Plan");
			break;
		}
		final MedicationTreatmentPlanSection section = new MedicationTreatmentPlanSection(
				getLanguageCode());
		this.getDoc().addSection(section.getMdht());
	}

	/**
	 * Instantiates a new cda ch mtps mtp document
	 *
	 * @param doc
	 *            the document
	 */
	public CdaChMtpsMtp(org.openhealthtools.mdht.uml.cda.ch.CdaChMtpsMtp doc) {
		super(doc);
	}

	public MedicationTreatmentPlanItemEntry createMedicationTreatmentPlanItemEntry(Identificator id,
			PatientMedicalInstructionsEntry patientMedicalInstructions,
			MedicationFullfillmentInstructionsEntry medicationFullfillmentInstructions,
			Reference ref, SubstanceAdministration substance) {
		/** MTP Item Declaration **/
		final MedicationTreatmentPlanItemEntry mtpEntry = new MedicationTreatmentPlanItemEntry(
				getLanguageCode());
		/** ID **/
		mtpEntry.setId(id);
		/** Instructions pour le patient **/
		mtpEntry.setPatientMedicalInstructions(patientMedicalInstructions);
		/** Instructions pour les professionnels **/
		mtpEntry.setMedicationFullfillmentInstructions(medicationFullfillmentInstructions);
		/** Reference **/
		mtpEntry.getMdht().getReferences().add(ref);
		/** SubstanceAdministration **/
		mtpEntry.getMdht().addSubstanceAdministration(substance);
		return mtpEntry;
	}

	/**
	 * Gets the medication treatment plan section.
	 *
	 * @return the medication treatment plan section
	 */
	public MedicationTreatmentPlanSection getMedicationTreatmentPlanSection() {
		if (this.getMdht().getMedicationTreatmentPlanSection() != null) {
			return new MedicationTreatmentPlanSection(
					this.getMdht().getMedicationTreatmentPlanSection());
		}
		return null;
	}
}
