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

package org.ehealth_connector.cda.textbuilder;

import org.ehealth_connector.cda.enums.ContentIdPrefix;

public class SimpleTextBuilder extends TextBuilder {
	public static final String contentTagStr = "<content";

	public static int countMatches(final String str, final String sub) {
		if ("".equals(str) || "".equals(sub)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	private String contentIdPrefix;
	private String text;

	private int currentContentId;

	public SimpleTextBuilder(ContentIdPrefix section, String newText) {
		contentIdPrefix = section.getContentIdPrefix();
		text = newText;
		addContent(text, contentIdPrefix, 1);
		currentContentId = 1;
	}

	public SimpleTextBuilder(ContentIdPrefix section, String newText, String oldText) {
		contentIdPrefix = section.getContentIdPrefix();

		// Calculate the current content ID
		final int contentTagOccurences = countMatches(oldText, contentTagStr);
		currentContentId = contentTagOccurences + 1;
		append(oldText);
		addContent(newText, contentIdPrefix, currentContentId);
	}

	public int getNewTextContentIDNr() {
		return currentContentId;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
