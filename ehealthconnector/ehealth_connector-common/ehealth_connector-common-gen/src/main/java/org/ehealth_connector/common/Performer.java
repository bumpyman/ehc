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

package org.ehealth_connector.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.utils.DateUtil;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;

/**
 * A Person or Organization performing an action
 *
 */
public class Performer {

	private org.openhealthtools.mdht.uml.cda.Performer2 mPerfomer;
	private org.openhealthtools.mdht.uml.cda.AssignedEntity mAsEntity;
	private org.openhealthtools.mdht.uml.cda.Person mPerson;

	/**
	 * Default constructor to instanciate the object
	 */
	public Performer() {
		mPerfomer = CDAFactory.eINSTANCE.createPerformer2();
		mAsEntity = CDAFactory.eINSTANCE.createAssignedEntity();
		mPerson = CDAFactory.eINSTANCE.createPerson();

		mAsEntity.setAssignedPerson(mPerson);
		mPerfomer.setAssignedEntity(mAsEntity);

		// add time
		setTimeValue(new Date());
	}

	/**
	 * Default constructor to instanciate the object
	 *
	 * @param name
	 *            the name of the performer
	 */
	public Performer(Name name) {
		this();
		addName(name);
	}

	/**
	 * Erstellt einen neuen Perfomer (Dieser Konstruktor wird oft gebraucht für
	 * Gesundheitsfachpersonen
	 *
	 * @param name
	 *            Name
	 * @param gln
	 *            Global Location Number (GLN)
	 */
	public Performer(Name name, String gln) {
		this(name);

		// Create and fill Person Name and GLN
		final II id = DatatypesFactory.eINSTANCE.createII();
		id.setRoot(CodeSystems.GLN.getCodeSystemId());
		id.setExtension(gln);

		mAsEntity.getIds().add(id);
	}

	/**
	 * Erstellt ein eHealthconnector-Author Objekt mittels eines MDHT-Performer
	 * Objekts.
	 *
	 * @param performerMdht
	 *            the MDHT Author Object
	 */
	public Performer(org.openhealthtools.mdht.uml.cda.Performer2 performerMdht) {
		mPerfomer = performerMdht;
		mAsEntity = performerMdht.getAssignedEntity();
		mPerson = performerMdht.getAssignedEntity().getAssignedPerson();
	}

	/**
	 * Default constructor to instanciate the object
	 *
	 * @param organization
	 *            the organization of the performer
	 */
	public Performer(Organization organization) {
		mPerfomer = CDAFactory.eINSTANCE.createPerformer2();
		mAsEntity = CDAFactory.eINSTANCE.createAssignedEntity();
		mPerfomer.setAssignedEntity(mAsEntity);

		// add time
		setTimeValue(new Date());

		setOrganization(organization);
		mAsEntity.getIds().addAll(organization.copyMdhtOrganization().getIds());
	}

	/**
	 * Weist dem Autor eine Postadresse zu.
	 *
	 * @param address
	 *            Die Postadresse des Autors
	 */
	public void addAddress(Address address) {
		mAsEntity.getAddrs().add(address.copyMdhtAdress());
	}

	/**
	 * Weist dem Autoren eine ID zu.
	 *
	 * @param identificator
	 *            Kombination von eigentlicher ID und der OID der verwaltenden
	 *            Domäne
	 */
	public void addId(Identificator identificator) {
		mAsEntity.getIds().add(identificator.getIi());
	}

	/**
	 * <div class="en">Adds the name.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param name
	 *            name
	 */
	public void addName(Name name) {
		mAsEntity.getAssignedPerson().getNames().add(name.getMdhtPn());
	}

	/**
	 * <div class="en">Copy mdht author.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return org.openhealthtools.mdht.uml.cda.author
	 */
	public org.openhealthtools.mdht.uml.cda.Performer2 copyMdhtPerfomer() {
		return EcoreUtil.copy(mPerfomer);
	}

	/**
	 * <div class="en">Gets the address.</div> <div class="de">Liefert die
	 * (erste) Adresse.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the address</div>
	 */
	public Address getAddress() {
		final Address address = new Address(mAsEntity.getAddrs().get(0));
		return address;
	}

	/**
	 * <div class="en">Gets the addresses.</div> <div class="de">Liefert alle
	 * Adressen.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the addresses</div>
	 */
	public List<Address> getAddresses() {
		final List<Address> al = new ArrayList<Address>();
		for (final AD mAddress : mAsEntity.getAddrs()) {
			final Address address = new Address(mAddress);
			al.add(address);
		}
		return al;
	}

	/**
	 * <div class="en">Gets the complete name.</div> <div class="de">Liefert den
	 * ganzen Namen (z.B. "Dr. Allzeit Bereit der Dritte")</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the complete name</div>
	 */
	public String getCompleteName() {
		// Search for the author name. If it isn´t there, try to use the
		// organisation name.
		String retVal = "";
		if ((mAsEntity != null) && (mAsEntity.getAssignedPerson() != null)) {
			if (mAsEntity.getAssignedPerson().getNames() != null) {
				final Name name = new Name(mAsEntity.getAssignedPerson().getNames().get(0));
				retVal = name.getCompleteName();
			} else {
				if ((mAsEntity.getRepresentedOrganizations().get(0) != null)
						&& (mAsEntity.getRepresentedOrganizations().get(0).getNames() != null)) {
					final Name name = new Name(
							mAsEntity.getRepresentedOrganizations().get(0).getNames().get(0));
					retVal = name.getCompleteName();
				}
			}
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the gln (identification of the author)</div>
	 * <div class="de">Liefert die GLN (ID des Autors)</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the gln</div>
	 */
	public String getGln() {
		final Identificator gln = Identificator.getIdentificator(mAsEntity.getIds(),
				CodeSystems.GLN.getCodeSystemId());
		return gln.getExtension();
	}

	/**
	 * <div class="en">Gets the gln as identificator.</div>
	 * <div class="de">Liefert gln (ID des Autoren) als Identificator
	 * Objekt.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the gln as identificator</div>
	 */
	public Identificator getGlnAsIdentificator() {
		final II ii = mAsEntity.getIds().get(0);
		final Identificator id = new Identificator(ii);
		return id;
	}

	/**
	 * <div class="en">Gets all ids of the author</div> <div class="de">Liefert
	 * alle IDs des Autoren</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the ids</div>
	 */
	public List<Identificator> getIds() {
		return Util.convertIds(mAsEntity.getIds());
	}

	/**
	 * <div class="en">Gets the (first) family name.</div>
	 * <div class="de">Liefert den (ersten) Nachnamen</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the name</div>
	 */
	public Name getName() {
		final Name name = new Name(mAsEntity.getAssignedPerson().getNames().get(0));
		return name;
	}

	/**
	 * <div class="en">Gets the names.</div> <div class="de">Liefert alle
	 * Nachnamen</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the names</div>
	 */
	public List<Name> getNames() {
		final List<Name> nl = new ArrayList<Name>();
		for (final PN mName : mAsEntity.getAssignedPerson().getNames()) {
			final Name name = new Name(mName);
			nl.add(name);
		}
		return nl;
	}

	/**
	 * <div class="en">Gets the organization (RepresentedOrganization)</div>
	 * <div class="de">Liefert die Organisation (RepresentedOrganization), der
	 * der Autor angehört (z.B. ein Krankenhaus)</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @return organization <div class="en">the organization</div>
	 *         <div class="de">die Organisation</div> <div class="fr"></div>
	 *         <div class="it"></div>
	 */
	public Organization getOrganization() {
		final Organization o = new Organization(mAsEntity.getRepresentedOrganizations().get(0));
		return o;
	}

	/**
	 * <div class="en">Gets the author mdht.</div> <div class="de">Liefert
	 * author mdht.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return org.openhealthtools.mdht.uml.cda.Author <div class="en">the
	 *         author mdht</div>
	 */
	public org.openhealthtools.mdht.uml.cda.Performer2 getPerformerMdht() {
		return EcoreUtil.copy(mPerfomer);
	}

	/**
	 * <div class="en">Gets the telecoms.</div> <div class="de">Liefert
	 * telecoms.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return Telecoms <div class="en">the telecoms</div>
	 */
	public Telecoms getTelecoms() {
		final Telecoms telecoms = new Telecoms(mAsEntity.getTelecoms());
		return telecoms;
	}

	/**
	 * <div class="en">Sets the gln.</div> <div class="de">Setzt die GLN (ID des
	 * Autoren).</div> <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param gln
	 *            <div class="en">the new gln</div> <div class="de">das neue
	 *            gln.</div> <div class="fr"></div> <div class="it"></div>
	 */
	public void setGln(String gln) {
		addId(new Identificator(CodeSystems.GLN.getCodeSystemId(), gln));
	}

	/**
	 * <div class="en">Sets the organization (RepresentedOrganization)</div>
	 * <div class="de">Setzt eine Organisation (RepresentedOrganization), der
	 * der Autor angehört (z.B. ein Krankenhaus)</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @param organization
	 *            <div class="en">the new organization</div> <div class="de">die
	 *            neue Organisation</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public void setOrganization(Organization organization) {
		mAsEntity.getRepresentedOrganizations().clear();
		mAsEntity.getRepresentedOrganizations().add(organization.copyMdhtOrganization());
	}

	/**
	 * <div class="en">Sets the telecoms.</div> <div class="de">Setzt
	 * telecoms.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @param telecoms
	 *            <div class="en">the new telecoms</div> <div class="de">das
	 *            neue telecoms.</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public void setTelecoms(Telecoms telecoms) {
		mAsEntity.getTelecoms().addAll(telecoms.getMdhtTelecoms());
	}

	/**
	 * Sets the Time Value
	 *
	 * @param eurDate
	 *            the date
	 */
	public void setTimeValue(Date eurDate) {
		try {
			mPerfomer.setTime(DateUtil.createIVL_TSFromEuroDate(eurDate));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
	}
}
