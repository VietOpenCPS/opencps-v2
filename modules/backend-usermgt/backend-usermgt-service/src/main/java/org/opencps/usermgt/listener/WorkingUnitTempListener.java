package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class WorkingUnitTempListener extends BaseModelListener<WorkingUnit> {

	@Override
	public void onAfterCreate(WorkingUnit model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(WorkingUnit model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(WorkingUnit model) throws ModelListenerException {
//		try {
//			model.setName(HtmlUtil.escape(model.getName()));
//			model.setEnName(HtmlUtil.escape(model.getEnName()));
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setFaxNo(HtmlUtil.escape(model.getFaxNo()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setWebsite(HtmlUtil.escape(model.getWebsite()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(WorkingUnit model) throws ModelListenerException {
//		try {
//			model.setName(HtmlUtil.escape(model.getName()));
//			model.setEnName(HtmlUtil.escape(model.getEnName()));
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setTelNo(HtmlUtil.escape(model.getTelNo()));
//			model.setFaxNo(HtmlUtil.escape(model.getFaxNo()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setWebsite(HtmlUtil.escape(model.getWebsite()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(JobPosTempListener.class.getName());
}
