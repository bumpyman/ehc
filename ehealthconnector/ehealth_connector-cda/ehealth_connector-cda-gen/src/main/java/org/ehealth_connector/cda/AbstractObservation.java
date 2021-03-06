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

package org.ehealth_connector.cda;

import org.ehealth_connector.cda.enums.ActSite;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Value;
import org.ehealth_connector.common.utils.LangTexts;

public abstract class AbstractObservation {

	protected LangTexts myValueLangTexts = new LangTexts();
	protected LangTexts myTargetSiteLangTexts = new LangTexts();
	protected ActSite myActSite = null;

	public abstract Code getCode();

	public abstract String getCommentText();

	public LangTexts getLangTexts() {
		return myValueLangTexts;
	}

	public abstract Object getMdhtObservation();

	public ActSite getTargetSite() {
		return myActSite;
	}

	public abstract Value getValue();

	public abstract void setTargetSite(ActSite actSite);

	public void setTargetSite(ActSite actSite, LangTexts targetSiteLangTexts) {
		setTargetSite(actSite);
		myActSite = actSite;
		this.myTargetSiteLangTexts = targetSiteLangTexts;
	}

	public abstract void setValue(Value value);

	public void setValue(Value value, LangTexts valueLangTexts) {
		setValue(value);
		this.myValueLangTexts = valueLangTexts;
	}
}
