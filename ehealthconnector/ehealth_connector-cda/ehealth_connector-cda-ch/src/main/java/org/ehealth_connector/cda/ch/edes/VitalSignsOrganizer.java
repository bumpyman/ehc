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
package org.ehealth_connector.cda.ch.edes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ehealth_connector.cda.AbstractVitalSignObservation;
import org.ehealth_connector.cda.AbstractVitalSignsOrganizer;
import org.ehealth_connector.cda.ch.AbstractCdaCh;
import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.enums.NullFlavor;
import org.openhealthtools.ihe.utils.UUID;
import org.openhealthtools.mdht.uml.hl7.vocab.ActRelationshipHasComponent;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;

/**
 * The Class VitalSignsOrganizer. <div class="en">This element groups different
 * vital sign measures, which have been aquired in the same point in time from
 * the same physician.</div> <div class="de">Dieses Element gruppiert
 * verschiedene Messwerte zu Vitalzeichen, welche zum gleichen Zeitpunkt durch
 * denselben Behandelnden gemessen wurden.</div>
 */
public class VitalSignsOrganizer extends AbstractVitalSignsOrganizer {

	/**
	 * Instantiates a new vital signs organizer.
	 */
	public VitalSignsOrganizer() {
		super();
		setEffectiveTimeNullFlavor(NullFlavor.NOT_APPLICABLE);
	}

	/**
	 * Instantiates the class with the required elements.
	 *
	 * @param effectiveTime
	 *            point in time of the measurement.
	 * @param author
	 *            the author
	 * @param observation
	 *            the observation
	 * @param id
	 *            the id
	 */
	public VitalSignsOrganizer(Date effectiveTime, Author author, VitalSignObservation observation,
			Identificator id) {
		this();
		setEffectiveTime(effectiveTime);
		addAuthor(author);
		addVitalSignsObservation(observation);
	}

	/**
	 * Instantiates the class with the required elements. An ID with the
	 * CdaChLrtp root and a generated extension will be added automatically.
	 *
	 * @param effectiveTime
	 *            point in time of the measurement.
	 * @param observation
	 *            the observation
	 */
	public VitalSignsOrganizer(Date effectiveTime, VitalSignObservation observation) {
		this();
		setEffectiveTime(effectiveTime);
		addVitalSignsObservation(observation);
		addId(null);
	}

	/**
	 * Instantiates a new vital signs organizer.
	 *
	 * @param mdht
	 *            the mdht
	 */
	public VitalSignsOrganizer(org.openhealthtools.mdht.uml.cda.ihe.VitalSignsOrganizer mdht) {
		super(mdht);
	}

	/**
	 * Adds the author.
	 *
	 * @param author
	 *            the author
	 */
	public void addAuthor(Author author) {
		getMdht().getAuthors().add(author.copyMdhtAuthor());
		final int nb = getMdht().getAuthors().size() - 1;
		getMdht().getAuthors().get(nb).setTypeCode(ParticipationType.AUT);
	}

	/**
	 * Adds an ID.
	 *
	 * @param id
	 *            the id. If null, an ID with the CdaChLrtp root and a generated
	 *            extension will be created
	 * @see org.ehealth_connector.cda.AbstractVitalSignsOrganizer#addId(org.ehealth_connector.common.Identificator)
	 */
	@Override
	public void addId(Identificator id) {
		if (id == null) {
			id = new Identificator(AbstractCdaCh.OID_MAIN, UUID.generate());
		}
		getMdht().getIds().add(id.getIi());
	}

	/**
	 * Adds the vital signs observation.
	 *
	 * @param vitalSignObservation
	 *            the observation
	 */
	public void addVitalSignsObservation(VitalSignObservation vitalSignObservation) {
		getMdht().addObservation(vitalSignObservation.getMdhtCopy());
		final int nb = getMdht().getComponents().size() - 1;
		getMdht().getComponents().get(nb).setTypeCode(ActRelationshipHasComponent.COMP);
	}

	/**
	 * Gets the authors.
	 *
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		final List<Author> al = new ArrayList<Author>();
		for (final org.openhealthtools.mdht.uml.cda.Author mdht : getMdht().getAuthors()) {
			final Author ehc = new Author(mdht);
			al.add(ehc);
		}
		return al;
	}

	/**
	 * Gets the vital signs observations.
	 *
	 * @return the vital signs observations
	 */
	public List<AbstractVitalSignObservation> getVitalSignsObservations() {
		final List<AbstractVitalSignObservation> vsl = new ArrayList<AbstractVitalSignObservation>();
		for (final org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation mdht : getMdht()
				.getVitalSignObservations()) {
			final AbstractVitalSignObservation ehc = new VitalSignObservation(mdht);
			vsl.add(ehc);
		}
		return vsl;
	}
}
