package org.ehealth_connector.communication.ch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.ehealth_connector.communication.DocumentMetadataTest;
import org.ehealth_connector.communication.ch.enums.ClassCode;
import org.ehealth_connector.communication.ch.enums.ConfidentialityCode;
import org.ehealth_connector.communication.ch.enums.HealthcareFacilityTypeCode;
import org.ehealth_connector.communication.ch.enums.LanguageCode;
import org.ehealth_connector.communication.ch.enums.MimeType;
import org.ehealth_connector.communication.ch.enums.PracticeSettingCode;
import org.ehealth_connector.communication.ch.enums.TypeCode;
import org.junit.Test;

public class DocumentMetadataChTest extends DocumentMetadataTest {

	DocumentMetadataCh m = new DocumentMetadataCh();

	@Test
	public void testClassCodeEnum() {
		m.setClassCode(ClassCode.ALERTS);
		assertTrue(isEqual(ClassCode.ALERTS.getCode(), m.getClassCodeEnum().getCode()));
	}

	@Test
	public void testCodedLanguageEnum() {
		m.setCodedLanguage(LanguageCode.FRANZOSISCH);
		assertEquals(LanguageCode.FRANZOSISCH, m.getLanguageCodeEnum());
	}

	@Test
	public void testConfidentialityCodeEnum() {
		m.addConfidentialityCode(ConfidentialityCode.ADMINISTRATIVE_DATEN);
		assertTrue(isEqual(ConfidentialityCode.ADMINISTRATIVE_DATEN.getCode(),
				m.getConfidentialityCodesEnum().get(0).getCode()));
	}

	@Test
	public void testHealthcareFacilityEnum() {
		m.setHealthcareFacilityTypeCode(
				HealthcareFacilityTypeCode.AMBULANTE_EINRICHTUNG_INKL_AMBULATORIUM);
		assertEquals(HealthcareFacilityTypeCode.AMBULANTE_EINRICHTUNG_INKL_AMBULATORIUM,
				m.getHealthcareFacilityTypeCodeEnum());
	}

	@Test
	public void testMimeTypeEnum() {
		m.setMimeType(MimeType.CDA_LEVEL_1_MULTIPART);
		assertEquals(MimeType.CDA_LEVEL_1_MULTIPART.getCodeValue(), m.getMimeType());
	}

	@Test
	public void testPracticeSettingEnum() {
		m.setPracticeSettingCode(PracticeSettingCode.ALLERGOLOGIE);
		assertEquals(PracticeSettingCode.ALLERGOLOGIE, m.getPracticeSettingCodeEnum());
	}

	@Test
	public void testTypeCodeEnum() {
		m.setTypeCode(TypeCode.ANDERE_NICHT_NAHER_SPEZIFIZIERT);
		assertEquals(TypeCode.ANDERE_NICHT_NAHER_SPEZIFIZIERT, m.getTypeCodeEnum());
	}
}
