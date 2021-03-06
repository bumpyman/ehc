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

package org.ehealth_connector.cda.ch.vacd;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.ch.CodedResults;
import org.ehealth_connector.cda.ch.utils.CdaChUtil;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.utils.DateUtil;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.cda.ch.CodedResultsSection;
import org.openhealthtools.mdht.uml.cda.ch.GestationalAgeDaysSimpleObservation;
import org.openhealthtools.mdht.uml.cda.ch.GestationalAgeWeeksSimpleObservation;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.PQ;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

/**
 * <div class="en">Class gestational age. You can set the gestational age if
 * it´s relevant for the immunization.</div> <div class="de">Klasse
 * Gestationsalter. Dies ist der Zeitraum vom 1. Tag der letzten Regelblutung
 * der Mutter bis zur Geburt des Kindes. Hier kann Gestationsalter eines Kindes
 * angegeben werden, sofern das rund um Impfungen am Kind relevant ist. </div>
 * <div class="fr"></div> <div class="it"></div>
 */
public class GestationalAge extends CodedResults {

	private final GestationalAgeWeeksSimpleObservation mWeeks;
	private final GestationalAgeDaysSimpleObservation mDays;
	private II mIi;

	/**
	 * Instantiates a new gestational age.
	 *
	 */
	public GestationalAge() {
		super(CHFactory.eINSTANCE.createCodedResultsSection().init());
		mWeeks = CHFactory.eINSTANCE.createGestationalAgeWeeksSimpleObservation().init();
		mDays = CHFactory.eINSTANCE.createGestationalAgeDaysSimpleObservation().init();

		getCrs().addObservation(mWeeks);
		getCrs().addObservation(mDays);

		// Create Id
		mIi = CdaChUtil.createUniqueIiFromString(null);

		// CreateEmpty Procedure Entry
		getCrs().addProcedure(createEmptyProcedureEntry());
	}

	/**
	 * Instantiates a new gestational age.
	 *
	 * @param codedResultsSection
	 *            <div class="en">Instantiates the object on the basis of an
	 *            MDHT CodedResultsSection</div><div class="de">Instantiiert das
	 *            Objekt auf Basis einer MDHT CodedResultsSection</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public GestationalAge(CodedResultsSection codedResultsSection) {
		setCrs(codedResultsSection);
		mWeeks = (GestationalAgeWeeksSimpleObservation) codedResultsSection
				.getGestationalAgeWeeksSimpleObservations();
		mDays = (GestationalAgeDaysSimpleObservation) codedResultsSection
				.getGestationalAgeDaysSimpleObservations();
	}

	/**
	 * Instantiates a new gestational age.
	 *
	 * @param days
	 *            <div class="en">gestation age in days (not in weeks AND
	 *            days)</div> <div class="de">Gestationsalter in Tagen (nicht in
	 *            Wochen UND Tagen)</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public GestationalAge(int days) {
		this(days / 7, days % 7);
	}

	/**
	 * Instantiates a new gestational age.
	 *
	 * @param weeks
	 *            <div class="en">gestational age in weeks and days. This
	 *            parameter represents the weeks.</div>
	 *            <div class="de">Gestationsalter in Wochen und Tagen. Dieser
	 *            Parameter gibt die Anzahl der Wochen an.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 * @param weeksDays
	 *            <div class="en">gestational age in weeks and days. This
	 *            parameter represents the days.</div>
	 *            <div class="de">Gestationsalter in Wochen und Tagen. Dieser
	 *            Parameter gibt die Anzahl der Tage an.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public GestationalAge(int weeks, int weeksDays) {
		// create and add the MDHT Objects to the section
		this();

		// Set payload
		setWeeksAndDays(weeks, weeksDays);
	}

	/**
	 * <div class="en">Copy mdht gestational age days observation.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return the gestational age days simple observation
	 */
	public GestationalAgeDaysSimpleObservation copyMdhtGestationalAgeDaysObservation() {
		return EcoreUtil.copy(mDays);
	}

	/**
	 * <div class="en">Copy mdht gestational age weeks observation.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return the gestational age weeks simple observation
	 */
	public GestationalAgeWeeksSimpleObservation copyMdhtGestationalAgeWeeksObservation() {
		return EcoreUtil.copy(mWeeks);
	}

	/**
	 * Gets the absolute days.
	 *
	 * @return <div class="en">Gets the gestational age in absolute days
	 *         (without weeks)</div><div class="de">Gibt das Gestationsalter in
	 *         absoluten Tagen (ohne Wochen) zurück.</div>
	 *         <div class="fr"></div> <div class="it"></div>
	 */
	public int getAbsoluteDays() {
		return (getWeeksOfWeeksAndDays() * 7) + getDaysOfWeeksAndDays();
	}

	/**
	 * Gets the gestational age text.
	 *
	 * @return <div class="en">the gestational age text</div><div class="de">Das
	 *         Gestationsalter in Satz-form:
	 *         "Das Gestationsalter beträgt: X Wochen und Y Tage"</div>
	 *         <div class="fr"></div> <div class="it"></div>
	 */
	public String getCodedResultsText() {
		final String gestationalText = "Das Gestationsalter beträgt: "
				+ String.valueOf(getWeeksOfWeeksAndDays()) + " Wochen und "
				+ String.valueOf(getDaysOfWeeksAndDays()) + " Tage";
		return gestationalText;
	}

	/**
	 * Gets the days of weeks and days.
	 *
	 * @return <div class="en">the gestational age in weeks and days. Gets the
	 *         days.</div><div class="de">Das Gestationsalter in Wochen und
	 *         Tagen. Hier wird die Anzahl der Tage zurückgegeben.</div>
	 *         <div class="fr"></div> <div class="it"></div>
	 */
	public int getDaysOfWeeksAndDays() {
		for (final ANY any : mDays.getValues()) {
			final PQ pq = (PQ) any;
			if ("d".equals(pq.getUnit())) {
				return pq.getValue().intValue();
			}
		}
		return 0;
	}

	/**
	 * Gets the mdht gestational age days observation.
	 *
	 * @return the mdht gestational age days observation
	 */
	public GestationalAgeDaysSimpleObservation getMdhtGestationalAgeDaysObservation() {
		return mDays;
	}

	/**
	 * Gets the mdht gestational age weeks observation.
	 *
	 * @return the mdht gestational age weeks observation
	 */
	public GestationalAgeWeeksSimpleObservation getMdhtGestationalAgeWeeksObservation() {
		return mWeeks;
	}

	/**
	 * Gets the weeks of weeks and days.
	 *
	 * @return <div class="en">the gestational age in weeks and days. Gets the
	 *         weeks.</div><div class="de">Das Gestationsalter in Wochen und
	 *         Tagen. Hier wird die Anzahl der Wochen zurückgegeben.</div>
	 *         <div class="fr"></div> <div class="it"></div>
	 */
	public int getWeeksOfWeeksAndDays() {
		for (final ANY any : mWeeks.getValues()) {
			final PQ pq = (PQ) any;
			if ("wk".equals(pq.getUnit())) {
				return pq.getValue().intValue();
			}
		}
		return 0;
	}

	/**
	 * Sets the asbolute days.
	 *
	 * @param days
	 *            <div class="en">sets the gestational age in absolute days (not
	 *            in weeks AND days)</div> <div class="de">Setzt das
	 *            Gestationsalter in absoluten Tagen (nicht in Wochen UND
	 *            Tagen).</div> <div class="fr"></div> <div class="it"></div>
	 */
	public void setAsboluteDays(int days) {
		setWeeksAndDays(days / 7, days % 7);
	}

	/**
	 * Sets the days of weeks and days.
	 *
	 * @param days
	 *            <div class="en">the gestational age in weeks and days. Sets
	 *            the days.</div> <div class="de">Das Gestationsalter in Wochen
	 *            und Tagen. Hier wird die Anzahl der Tage gesetzt.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	private void setDaysOfWeeksAndDays(int days) {
		final PQ mDaysValue = DatatypesFactory.eINSTANCE.createPQ(days, "d");
		mDays.getValues().add(mDaysValue);
		mDays.getIds().add(EcoreUtil.copy(mIi));
		mDays.setEffectiveTime(DateUtil.createUnknownTime(NullFlavor.NA));
	}

	/**
	 * Set an Identificator for Gestational Age Day
	 *
	 * @param id
	 *            the Id to be used
	 */
	public void setGestationalAgeDaysId(Identificator id) {
		if (mDays != null) {
			mDays.getIds().clear();
			mDays.getIds().add(id.getIi());
		}
	}

	/**
	 * Set an Identificator for Gestational Age Week
	 *
	 * @param id
	 *            the Id to be used
	 */
	public void setGestationalAgeWeeksId(Identificator id) {
		if (mWeeks != null) {
			mWeeks.getIds().clear();
			mWeeks.getIds().add(id.getIi());
		}
	}

	/**
	 * Sets the days of weeks and days.
	 *
	 * @param weeks
	 *            <div class="en">the gestational age in weeks and days. Sets
	 *            the weeks.</div> <div class="de">Das Gestationsalter in Wochen
	 *            und Tagen. Hier wird die Anzahl der Wochen gesetzt.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 *
	 * @param days
	 *            <div class="en">the gestational age in weeks and days. Sets
	 *            the days.</div> <div class="de">Das Gestationsalter in Wochen
	 *            und Tagen. Hier wird die Anzahl der Tage gesetzt.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public void setWeeksAndDays(int weeks, int days) {
		mDays.getValues().clear();
		mDays.getIds().clear();
		mWeeks.getValues().clear();
		mWeeks.getIds().clear();
		setWeeksOfWeeksAndDays(weeks);
		setDaysOfWeeksAndDays(days);
	}

	/**
	 * Sets the weeks of weeks and days.
	 *
	 * @param weeks
	 *            <div class="en">the gestational age in weeks and days. Sets
	 *            the weeks.</div> <div class="de">Das Gestationsalter in Wochen
	 *            und Tagen. Hier wird die Anzahl der Wochen gesetzt.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	private void setWeeksOfWeeksAndDays(int weeks) {
		// create and the values, ids and effectiveTime for weeks and days
		final PQ mWeeksValue = DatatypesFactory.eINSTANCE.createPQ(weeks, "wk");
		mWeeks.getValues().add(mWeeksValue);
		mWeeks.getIds().add(EcoreUtil.copy(mIi));
		mWeeks.setEffectiveTime(DateUtil.createUnknownTime(NullFlavor.NA));
	}

}
