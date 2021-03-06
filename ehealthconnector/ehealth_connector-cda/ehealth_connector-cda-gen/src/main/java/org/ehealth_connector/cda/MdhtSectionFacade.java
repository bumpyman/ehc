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
package org.ehealth_connector.cda;

import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.mdht.uml.cda.Section;

/**
 * MdhtEntryObservationFacade is a facade for extending the mdht objects
 * generated by the model The design enables that all derived convenience
 * objects can use the underlying mdht model but the exposing api of the classes
 * is independent of the mdht implementation.
 *
 * @param <E>
 *            the model type to provide for implemting the facade to it,
 *            extending an Act
 */
public class MdhtSectionFacade<E extends Section> extends MdhtFacade<E> {

	/**
	 * Instantiates a new facade for the provided mdht object.
	 *
	 * @param mdht
	 *            the mdht model object
	 */
	protected MdhtSectionFacade(E mdht) {
		super(mdht, null, null);
	}

	/**
	 * Instantiates a new facade for the provided mdht object.
	 *
	 * @param mdht
	 *            the mdht model object
	 */
	protected MdhtSectionFacade(E mdht, String templateIdRoot, String templateIdExtension) {
		super(mdht, templateIdRoot, templateIdExtension);
	}

	public Code getCode() {
		return new Code(getMdht().getCode());
	}

	public String getText() {
		if (getMdht().getText() != null) {
			return getMdht().getText().getText();
		}
		return null;
	}

	public String getTitle() {
		if (this.getMdht().getTitle() != null) {
			return this.getMdht().getTitle().getText();
		}
		return null;
	}

	public void setCode(Code code) {
		getMdht().setCode(code.getCE());
	}

	public void setText(String value) {
		getMdht().createStrucDocText(value);
	}

	public void setTitle(String title) {
		getMdht().setTitle(Util.st(title));
	}

}
