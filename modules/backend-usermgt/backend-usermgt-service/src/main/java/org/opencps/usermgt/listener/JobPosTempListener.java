package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class JobPosTempListener extends BaseModelListener<JobPos> {

	@Override
	public void onAfterCreate(JobPos model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(JobPos model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(JobPos model) throws ModelListenerException {
//		try {
//			model.setJobPosCode(StringEscapeUtils.escapeHtml4(model.getJobPosCode()));
//			model.setTitle(StringEscapeUtils.escapeHtml4(model.getTitle()));
//			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(JobPos model) throws ModelListenerException {
//		try {
//			model.setJobPosCode(StringEscapeUtils.escapeHtml4(model.getJobPosCode()));
//			model.setTitle(StringEscapeUtils.escapeHtml4(model.getTitle()));
//			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(JobPosTempListener.class.getName());
}
