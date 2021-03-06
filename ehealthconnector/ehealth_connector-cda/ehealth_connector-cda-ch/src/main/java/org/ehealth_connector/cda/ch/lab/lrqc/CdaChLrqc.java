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
package org.ehealth_connector.cda.ch.lab.lrqc;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.ch.lab.AbstractLaboratoryReport;
import org.ehealth_connector.cda.ch.lab.lrqc.enums.QualabQcc;
import org.ehealth_connector.cda.ch.lab.lrqc.enums.SpecialtySections;
import org.ehealth_connector.cda.utils.CdaUtil;
import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Organization;
import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.enums.LanguageCode;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.InformationRecipient;
import org.openhealthtools.mdht.uml.cda.Participant1;
import org.openhealthtools.mdht.uml.cda.Patient;
import org.openhealthtools.mdht.uml.cda.PatientRole;
import org.openhealthtools.mdht.uml.cda.Person;
import org.openhealthtools.mdht.uml.cda.RecordTarget;
import org.openhealthtools.mdht.uml.cda.StructuredBody;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClass;
import org.openhealthtools.mdht.uml.hl7.vocab.x_InformationRecipient;

/**
 * The Class CdaChLrqc. <div class="en">This document defines a standard
 * interchange format for laboratory results for quality control in the Swiss
 * health system. It implements the normative specification, based on HL7
 * CDA.</div> <div class="de">Das vorliegende Dokument definiert ein
 * einheitliches Austauschformat für Laborbefunde zur Qualitätskontrolle im
 * Gesundheitswesen Schweiz. Es beinhaltet die normative Spezifikation basierend
 * auf HL7 CDA. </div>
 */
public class CdaChLrqc
		extends AbstractLaboratoryReport<org.openhealthtools.mdht.uml.cda.ch.CdaChLrqc> {

	/** Root ID for the ZSR ID. */
	public static final String ZSR_ID_ROOT = "2.16.756.5.30.1.123.100.2.1.1";

	/**
	 * Standard constructor.
	 */
	public CdaChLrqc() {
		this(LanguageCode.ENGLISH);
	}

	/**
	 * Instantiates a new cda ch lrqc.
	 *
	 * @param languageCode
	 *            the language code
	 */
	public CdaChLrqc(LanguageCode languageCode) {
		this(languageCode, null, null);
	}

	/**
	 * Constructor with the recommended elements for the LRPH document Header.
	 *
	 * @param languageCode
	 *            the language code
	 * @param custodian
	 *            the sender of this document (a laboratory)
	 * @param recipient
	 *            the recipient of this document (the 'Qualitätskontrollzentrum'
	 *            (QKZ)) this number will also be used to set the
	 *            ClinicalDocument/inFullfillmentOf/id[@root] element
	 * @param qkzOrderNumber
	 *            the order number of the QKZ
	 *            (ClinicalDocument/inFullfillmentOf/id[@extension])
	 * @param legalAuthenticator
	 *            the legal authenticator of this document (e.g. 'Laborleiter')
	 * @param author
	 *            the author of the document
	 * @param id
	 *            the ID of the recordTarget. The ID identifies the specimen and
	 *            will be set in
	 *            ClinicalDocument/recordTarget[0]/patientRole/id. If the ID is
	 *            not known, use null
	 *            (ClinicalDocument/recordTarget[0]/patientRole/id =
	 *            NullFlavor.NA))
	 */
	public CdaChLrqc(LanguageCode languageCode, Organization custodian, QualabQcc recipient,
			String qkzOrderNumber, Author legalAuthenticator, Author author, Identificator id) {
		this(languageCode);
		setCustodian(custodian);
		addIntendedRecipient(recipient);
		addInFulfillmentOf(qkzOrderNumber);
		setLegalAuthenticator(legalAuthenticator);
		addAuthor(author);
		setRecordTarget(id);
	}

	/**
	 * Constructor with the recommended elements for the LRPH document Header.
	 * The LegalAuthenticator will also be added as the author of this document.
	 *
	 * @param languageCode
	 *            the language code
	 * @param custodian
	 *            the sender of this document (a laboratory)
	 * @param recipient
	 *            the recipient of this document (the 'Qualitätskontrollzentrum'
	 *            (QKZ)) this number will also be used to set the
	 *            ClinicalDocument/inFullfillmentOf/id[@root] element
	 * @param qkzOrderNumber
	 *            the order number of the QKZ
	 *            (ClinicalDocument/inFullfillmentOf/id[@extension])
	 * @param legalAuthenticator
	 *            the legal authenticator of this document (e.g. 'Laborleiter').
	 *            You have to provide an Author, who is NOT a software.
	 * @param id
	 *            the ID of the recordTarget. The ID identifies the specimen and
	 *            will be set in
	 *            ClinicalDocument/recordTarget[0]/patientRole/id. If the ID is
	 *            not known, use null
	 *            (ClinicalDocument/recordTarget[0]/patientRole/id =
	 *            NullFlavor.NA))
	 */
	public CdaChLrqc(LanguageCode languageCode, Organization custodian, QualabQcc recipient,
			String qkzOrderNumber, Author legalAuthenticator, Identificator id) {
		this(languageCode);
		setCustodian(custodian);
		addIntendedRecipient(recipient);
		addInFulfillmentOf(new Identificator(recipient.getCodeSystemOid(), qkzOrderNumber));
		setLegalAuthenticator(legalAuthenticator);
		addAuthor(legalAuthenticator);
		setRecordTarget(id);
	}

	/**
	 * Instantiates a new cda ch lrqc.
	 *
	 * @param languageCode
	 *            language of the document contents
	 * @param styleSheet
	 *            an extensible style sheet (XSLT) to transform and render the
	 *            document
	 * @param css
	 *            cascading style sheet (CSS) to add style information for
	 *            rendering
	 */
	public CdaChLrqc(LanguageCode languageCode, String styleSheet, String css) {
		super(CHFactory.eINSTANCE.createCdaChLrqc().init(), languageCode, styleSheet, css);
		this.setLanguageCode(languageCode);
		// set the fixed laboratory Code
		final CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setCode("11502-2");
		ce.setCodeSystem("2.16.840.1.113883.6.1");
		ce.setCodeSystemName("LOINC");
		ce.setDisplayName("LABORATORY REPORT.TOTAL");
		getMdht().setCode(ce);
	}

	/**
	 * Constructor with the recommended elements for the LRPH document Header.
	 * The LegalAuthenticator will also be added as the author of this document.
	 *
	 * @param languageCode
	 *            the language code
	 * @param styleSheet
	 *            an extensible style sheet (XSLT) to transform and render the
	 *            document
	 * @param css
	 *            cascading style sheet (CSS) to add style information for
	 *            rendering
	 * @param custodian
	 *            the sender of this document (a laboratory)
	 * @param recipient
	 *            the recipient of this document (the 'Qualitätskontrollzentrum'
	 *            (QKZ)) this number will also be used to set the
	 *            ClinicalDocument/inFullfillmentOf/id[@root] element
	 * @param qkzOrderNumber
	 *            the order number of the QKZ
	 *            (ClinicalDocument/inFullfillmentOf/id[@extension])
	 * @param legalAuthenticator
	 *            the legal authenticator of this document (e.g. 'Laborleiter')
	 * @param id
	 *            the ID of the recordTarget. The ID identifies the specimen and
	 *            will be set in
	 *            ClinicalDocument/recordTarget[0]/patientRole/id. If the ID is
	 *            not known, use null
	 *            (ClinicalDocument/recordTarget[0]/patientRole/id =
	 *            NullFlavor.NA))
	 */
	public CdaChLrqc(LanguageCode languageCode, String styleSheet, String css,
			Organization custodian, QualabQcc recipient, String qkzOrderNumber,
			Author legalAuthenticator, Identificator id) {
		this(languageCode, styleSheet, css);
		setCustodian(custodian);
		addIntendedRecipient(recipient);
		addInFulfillmentOf(qkzOrderNumber);
		addAuthor(legalAuthenticator);
		setLegalAuthenticator(legalAuthenticator);
		setRecordTarget(id);
	}

	/**
	 * Instantiates a new cda ch lrqc.
	 *
	 * @param doc
	 *            mdht model document
	 */
	public CdaChLrqc(org.openhealthtools.mdht.uml.cda.ch.CdaChLrqc doc) {
		super(doc);
	}

	/**
	 * Adds a inFulfillmentOf element with a fixed root.
	 *
	 * @param qkzOrderNumber
	 *            the order number, given by the QKZ
	 */
	public void addInFulfillmentOf(String qkzOrderNumber) {
		addInFulfillmentOf(new Identificator(QualabQcc.CODE_SYSTEM_OID, qkzOrderNumber));
	}

	/**
	 * Adds the QualityControlCenter ('Qualtitätskontrollzentrum' QKZ).
	 *
	 * @param intendedRecipient
	 *            the intendedRecipient
	 */
	public void addIntendedRecipient(IntendedRecipient intendedRecipient) {
		getMdht().getInformationRecipients().add(intendedRecipient.getMdhtIntendedRecipient());
		final int nb = getMdht().getInformationRecipients().size() - 1;
		getMdht().getInformationRecipients().get(nb).setTypeCode(x_InformationRecipient.PRCP);
	}

	/**
	 * Adds the QualityControlCenter ('Qualtitätskontrollzentrum' QKZ). Will set
	 * the following elements:
	 * ClinicalDocument/informationRecipient/intendedRecipient/addr =
	 * NullFlavor.NASK
	 * ClinicalDocument/informationRecipient/intendedRecipient/telecom =
	 * NullFlavor.NASK
	 *
	 * @param qkz
	 *            the QKZ ID. Root (codeSystem) and extension (code) of the id
	 *            will be set in
	 *            ClinicalDocument/informationRecipient/intendedRecipient/id.
	 *            The DisplayName of the QKZ will be set in
	 *            ClinicalDocument/informationRecipient/intendedRecipient/
	 *            informationRecipient/name
	 */
	public void addIntendedRecipient(QualabQcc qkz) {
		final org.ehealth_connector.common.IntendedRecipient ir = new org.ehealth_connector.common.IntendedRecipient();

		// ID
		ir.getMdhtIntendedRecipient().getIntendedRecipient().getIds()
				.add(Identificator.convertToIdentificator(qkz.getCode()).getIi());
		// Name
		final PN pn = DatatypesFactory.eINSTANCE.createPN();
		pn.addText(qkz.getDisplayName());
		final Person p = CDAFactory.eINSTANCE.createPerson();
		p.getNames().add(pn);
		ir.getMdhtIntendedRecipient().getIntendedRecipient().setInformationRecipient(p);
		// Addr NASK
		final AD ad = DatatypesFactory.eINSTANCE.createAD();
		ad.setNullFlavor(NullFlavor.NASK);
		ir.getMdhtIntendedRecipient().getIntendedRecipient().getAddrs().add(ad);
		// Telecoms NASK
		final TEL tel = DatatypesFactory.eINSTANCE.createTEL();
		tel.setNullFlavor(NullFlavor.NASK);
		ir.getMdhtIntendedRecipient().getIntendedRecipient().getTelecoms().add(tel);

		getMdht().getInformationRecipients().add(ir.getMdhtIntendedRecipient());
		final int nb = getMdht().getInformationRecipients().size() - 1;
		getMdht().getInformationRecipients().get(nb).setTypeCode(x_InformationRecipient.PRCP);
	}

	/**
	 * Convenience function to add a Laboratory Battery Organizer and create the
	 * necessary elements, if they do not exist. If the elements exist, their
	 * contents will not be overwritten.
	 *
	 * These elements are: LaboratorySpecialtySection (section code is derived
	 * automatically from the LaboratoryObservation enum)
	 * LaboratoryReportProcessingEntry SpecimenAct with the given Laboratory
	 * Battery Organizer
	 *
	 * @param organizer
	 *            the LaboratoryBatteryOrganizer holding at least one
	 *            LaboratoryObservation
	 */
	public void addLaboratoryBatteryOrganizer(LaboratoryBatteryOrganizer organizer) {
		// Try to determine the right code from the LaboratoryObservation and
		// set it
		// in the Section
		final String section = getSpecialtySectionCodeFromLaboratoryObservationEnum(organizer);
		Code sectionCode = null;
		if (section != null) {
			sectionCode = SpecialtySections.getEnum(section).getCode();
		}

		SpecimenAct se;
		if (getSpecimenAct() == null) {
			se = new SpecimenAct();
		} else {
			se = getSpecimenAct();
		}
		se.addLaboratoryBatteryOrganizer(organizer);

		setSpecimenAct(se, sectionCode);
	}

	/**
	 * Adds the participant. <div class="en">Physicians performing examinations
	 * in the given laboratory MAY be added as participants.</div>
	 * <div class="de">Ärzte, welche in dem, in <CH-LRQC-CUST> angegebenen Labor
	 * Untersuchungen durchführen KÖNNEN als Participant hinzugefügt
	 * werden.</div>
	 *
	 * @param participant
	 *            the participant
	 */
	public void addParticipant(Participant participant) {
		getMdht().getParticipants().add(participant.getMdht());
	}

	/**
	 * Gets the intended recipients for LRQC.
	 *
	 * @return the intended recipients for LRQC
	 */
	public List<IntendedRecipient> getIntendedRecipientsLrqc() {
		final List<IntendedRecipient> il = new ArrayList<IntendedRecipient>();
		for (final InformationRecipient ir : getMdht().getInformationRecipients()) {
			if (ir instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient) {
				final org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient iheIr = (org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient) ir;
				il.add(new IntendedRecipient(iheIr));
			}
		}
		return il;
	}

	/**
	 * Convenience function to return all LaboratoryBatteryOrganizers directly
	 * from all underlying
	 * LaboratorySpecialtySection/LaboratoryReportDataProcessingEntry/
	 * SpecimenAct element.
	 *
	 * @return a list of LaboratoryBatteryOrganizers.
	 */
	public List<LaboratoryBatteryOrganizer> getLaboratoryBatteryOrganizerList() {
		final ArrayList<LaboratoryBatteryOrganizer> lbol = new ArrayList<LaboratoryBatteryOrganizer>();
		final LaboratorySpecialtySection lss = getLaboratorySpecialtySection();
		if (lss != null) {
			final LaboratoryReportDataProcessingEntry lrdpe = lss
					.getLaboratoryReportDataProcessingEntry();
			if (lrdpe != null) {
				final SpecimenAct se = lrdpe.getSpecimenAct();
				if (se != null) {
					lbol.addAll(se.getLaboratoryBatteryOrganizers());
				}
			}
		}
		return lbol;
	}

	/**
	 * Gets the laboratory specialty section.
	 *
	 * @return the laboratory specialty section
	 */
	public LaboratorySpecialtySection getLaboratorySpecialtySection() {
		if (!getMdht().getLaboratorySpecialtySections().isEmpty()) {
			return new LaboratorySpecialtySection(
					getMdht().getLaboratorySpecialtySections().get(0));
		}
		return null;
	}

	/**
	 * Returns all Participants with an GLN ID.
	 *
	 * @return the List of Participants
	 */
	public List<Participant> getParticipantsLrqc() {
		final ArrayList<Participant> al = new ArrayList<Participant>();
		for (final Participant1 p : getMdht().getParticipants()) {
			if (p.getAssociatedEntity() != null) {
				for (final II ii : p.getAssociatedEntity().getIds()) {
					if (ii.getRoot().equals(CodeSystems.GLN.getCodeSystemId())) {
						al.add(new Participant(p));
					}
				}
			}
		}
		return al;
	}

	/**
	 * Gets the ID of the specimen.
	 *
	 * @return the /recordTarget[0]/patientRole/id[0] element if present.
	 *         Otherwise null.
	 */
	public Identificator getRecordTargetId() {
		if (!getMdht().getRecordTargets().isEmpty()
				&& (getMdht().getRecordTargets().get(0).getPatientRole() != null)
				&& (getMdht().getRecordTargets().get(0).getPatientRole().getIds() != null)) {
			return new Identificator(
					getMdht().getRecordTargets().get(0).getPatientRole().getIds().get(0));
		}
		return null;
	}

	/**
	 * Convenience function to return the (LOINC) section code from a given
	 * LaboratoryObservation, which is hold in the given
	 * LaboratoryBatteryOrganizer.
	 *
	 * @param organizer
	 *            the LaboratoryBatteryOrganizer
	 * @return the section code
	 */
	private String getSpecialtySectionCodeFromLaboratoryObservationEnum(
			LaboratoryBatteryOrganizer organizer) {
		if (!organizer.getLaboratoryObservations().isEmpty()) {
			if (organizer.getLaboratoryObservations().get(0).getCodeAsEnum() != null) {
				// if present return LOINC Enum
				return organizer.getLaboratoryObservations().get(0).getCodeAsEnum()
						.getSectionCode();
			} else {
				// if present return SNOMED Enum
				if (organizer.getLaboratoryObservations().get(0).getCodeAsEnum() != null) {
					return organizer.getLaboratoryObservations().get(0).getCodeAsEnum()
							.getSectionCode();
				}
			}
		}
		return null;
	}

	/**
	 * Convenience function, which returns the SpecimenAct directly from the
	 * underlying LaboratorySpecialtySection/LaboratoryReportDataProcessingEntry
	 * element.
	 *
	 * @return the SpecimenAct. Returns null, if this element does not exist.
	 */
	public SpecimenAct getSpecimenAct() {
		if ((getLaboratorySpecialtySection() != null)
				&& (getLaboratorySpecialtySection()
						.getLaboratoryReportDataProcessingEntry() != null)
				&& (getLaboratorySpecialtySection().getLaboratoryReportDataProcessingEntry()
						.getSpecimenAct() != null)) {
			return getLaboratorySpecialtySection().getLaboratoryReportDataProcessingEntry()
					.getSpecimenAct();
		}
		return null;
	}

	/**
	 * Sets a LaboratorySpecialtySection.
	 *
	 * @param laboratorySpecialtySection
	 *            the section
	 */
	public void setLaboratorySpecialtySection(
			org.ehealth_connector.cda.ch.lab.lrqc.LaboratorySpecialtySection laboratorySpecialtySection) {
		// Create a new structured body
		if (getMdht().getLaboratorySpecialtySections().isEmpty()) {
			getMdht().addSection(laboratorySpecialtySection.copy());
		} else {
			// We need to create a new Structured Body element, as the section
			// list is
			// not modifiable
			final StructuredBody sb = CDAFactory.eINSTANCE.createStructuredBody();
			CdaUtil.addSectionToStructuredBodyAsCopy(sb, laboratorySpecialtySection.copy());
			getMdht().setStructuredBody(sb);
		}
	}

	/**
	 * Sets a recordTarget element for a non living subject (a specimen in this
	 * case).
	 *
	 * @param id
	 *            the ID of the specimen. If null, the
	 *            recordTarget/patientRole/id will be set to NullFlavor.NA
	 */
	public void setRecordTarget(Identificator id) {
		// Create a record target for a non-human subject
		final RecordTarget rt = CDAFactory.eINSTANCE.createRecordTarget();
		final PatientRole pr = CDAFactory.eINSTANCE.createPatientRole();
		final Patient p = CDAFactory.eINSTANCE.createPatient();

		// Patient
		p.setNullFlavor(NullFlavor.OTH);
		pr.setPatient(p);
		// Patient Role
		if (id != null) {
			pr.getIds().add(id.getIi());
		} else {
			final II ii = DatatypesFactory.eINSTANCE.createII();
			ii.setNullFlavor(NullFlavor.NA);
			pr.getIds().add(ii);
		}
		pr.setClassCode(RoleClass.PAT);
		rt.setPatientRole(pr);
		// Record Target
		final II tIi = DatatypesFactory.eINSTANCE.createII();
		tIi.setRoot("1.3.6.1.4.1.19376.1.3.3.1.2");
		rt.getTemplateIds().add(tIi);
		rt.setTypeCode(ParticipationType.RCT);
		getMdht().getRecordTargets().clear();
		getMdht().getRecordTargets().add(rt);
	}

	/**
	 * Convenience function to add a Specimen and create the necessary elements,
	 * if they do not exist. If the elements exist, their contents will NOT be
	 * overwritten. These elements are: LaboratorySpecialtySection (will be
	 * initialized with the given code), LaboratoryReportDataProcessigEntry
	 *
	 * @param act
	 *            the specimenAct
	 * @param sectionCode
	 *            the section code
	 */
	public void setSpecimenAct(SpecimenAct act, Code sectionCode) {
		LaboratorySpecialtySection laboratorySpecialtySection;
		if (getLaboratorySpecialtySection() == null) {
			if (sectionCode != null) {
				laboratorySpecialtySection = new LaboratorySpecialtySection(sectionCode,
						getLanguageCode());
			} else {
				laboratorySpecialtySection = new LaboratorySpecialtySection();
			}
		} else {
			laboratorySpecialtySection = getLaboratorySpecialtySection();
		}

		LaboratoryReportDataProcessingEntry lrdpe;
		if (laboratorySpecialtySection.getLaboratoryReportDataProcessingEntry() == null) {
			lrdpe = new LaboratoryReportDataProcessingEntry();
		} else {
			lrdpe = laboratorySpecialtySection.getLaboratoryReportDataProcessingEntry();
		}

		if (sectionCode != null) {
			act.setCode(sectionCode);
		}

		lrdpe.setSpecimenAct(act);
		laboratorySpecialtySection.setLaboratoryReportDataProcessingEntry(lrdpe);
		setLaboratorySpecialtySection(laboratorySpecialtySection);
	}
}
