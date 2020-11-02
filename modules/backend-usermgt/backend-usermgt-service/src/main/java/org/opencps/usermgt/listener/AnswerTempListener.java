package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.HtmlUtil;

import org.opencps.usermgt.model.Answer;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class AnswerTempListener extends BaseModelListener<Answer> {

	@Override
	public void onBeforeCreate(Answer model) throws ModelListenerException {
		try {
			model.setContent(HtmlUtil.escape(model.getContent()));
			model.setClassName(HtmlUtil.escape(model.getClassName()));
			model.setClassPK(HtmlUtil.escape(model.getClassPK()));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(Answer model) throws ModelListenerException {
		try {
			model.setContent(HtmlUtil.escape(model.getContent()));
			model.setClassName(HtmlUtil.escape(model.getClassName()));
			model.setClassPK(HtmlUtil.escape(model.getClassPK()));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(AnswerTempListener.class.getName());
}
