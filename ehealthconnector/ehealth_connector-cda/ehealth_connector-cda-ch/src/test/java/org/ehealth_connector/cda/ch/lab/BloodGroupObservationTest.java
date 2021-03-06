package org.ehealth_connector.cda.ch.lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;

import org.ehealth_connector.cda.SectionAnnotationCommentEntry;
import org.ehealth_connector.cda.enums.epsos.BloodGroup;
import org.ehealth_connector.cda.testhelper.TestUtils;
import org.junit.Test;
import org.w3c.dom.Document;

public class BloodGroupObservationTest extends TestUtils {
	@Test
	public void testModel() throws XPathExpressionException {
		super.init();
		BloodGroupObservation o = new BloodGroupObservation();

		Document document = o.getDocument();
		assertTrue(xExist(document, "//statusCode[@code='completed']"));
		assertTrue(xExist(document,
				"//templateId[@root='2.16.756.5.30.1.1.1.1.3.4.1' and @extension='CDA-CH.LRTP.Body.StudiesSummaryL3.Bloodgroup']"));

		// text contentId Reference
		o.setContentIdReference("TestRef");
		assertEquals("#TestRef", o.getContentIdReference());

		// effective Time
		o.setEffectiveTime(endDate);
		assertEquals(endDate, o.getEffectiveTime());

		// value
		o.setValue(BloodGroup.BLOOD_GROUP_0);
		assertEquals(BloodGroup.BLOOD_GROUP_0.getCE().getCode(),
				o.getValueEnum().getCE().getCode());
		document = o.getDocument();
		assertTrue(xExist(document, "//value[@type='CE']"));

		// author
		o.addAuthor(author1);
		assertTrue(isEqual(author1, o.getAuthorList().get(0)));
		// assertTrue(author1.equals(o.getAuthorList().get(0)));

		// Comment
		o.setComment(new SectionAnnotationCommentEntry());
		assertNotNull(o.getComment());
		document = o.getDocument();
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.5.3.1.4.2']"));
		assertTrue(
				xExist(document, "//entryRelationship[@typeCode='SUBJ' and @inversionInd='true']"));
	}
}
