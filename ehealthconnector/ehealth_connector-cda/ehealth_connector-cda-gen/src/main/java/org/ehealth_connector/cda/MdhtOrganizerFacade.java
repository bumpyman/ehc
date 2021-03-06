package org.ehealth_connector.cda;

import java.util.Date;

import org.ehealth_connector.common.enums.StatusCode;
import org.ehealth_connector.common.utils.DateUtil;
import org.openhealthtools.mdht.uml.cda.Organizer;

public class MdhtOrganizerFacade<E extends Organizer> extends MdhtFacade<E> {

	protected MdhtOrganizerFacade(E mdht) {
		super(mdht);
	}

	public Date getEffectiveTime() {
		if (getMdht() != null && getMdht().getEffectiveTime() != null) {
			return DateUtil.parseIVL_TSVDateTimeValue(getMdht().getEffectiveTime());
		}
		return null;
	}

	public StatusCode getStatusCode() {
		if (getMdht() != null && getMdht().getStatusCode() != null) {
			return StatusCode.getEnum(getMdht().getStatusCode().getCode());
		}
		return null;
	}

	public void setEffectiveTime(Date date) {
		getMdht().setEffectiveTime(DateUtil.convertDateToIvlTsyyyyMMddHHmmssZZZZ(date));
	}

	public void setStatusCode(StatusCode statusCode) {
		getMdht().setStatusCode(statusCode.getCS());
	}
}
