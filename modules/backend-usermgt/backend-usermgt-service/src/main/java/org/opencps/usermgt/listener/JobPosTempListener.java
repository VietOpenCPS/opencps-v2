package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import org.opencps.usermgt.model.JobPos;


//@Component(immediate = true, service = ModelListener.class)
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
//			model.setJobPosCode(HtmlUtil.escape(model.getJobPosCode()));
//			model.setTitle(HtmlUtil.escape(model.getTitle()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(JobPos model) throws ModelListenerException {
//		try {
//			model.setJobPosCode(HtmlUtil.escape(model.getJobPosCode()));
//			model.setTitle(HtmlUtil.escape(model.getTitle()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	//private Log _log = LogFactoryUtil.getLog(JobPosTempListener.class.getName());
}
