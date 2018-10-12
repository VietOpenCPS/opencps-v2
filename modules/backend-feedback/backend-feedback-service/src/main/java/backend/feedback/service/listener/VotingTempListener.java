package backend.feedback.service.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.osgi.service.component.annotations.Component;

import backend.feedback.model.Voting;
import backend.feedback.service.VotingLocalServiceUtil;

@Component(immediate = true, service = ModelListener.class)
public class VotingTempListener extends BaseModelListener<Voting> {

	@Override
	public void onAfterCreate(Voting model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(Voting model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(Voting model) throws ModelListenerException {
		try {
			model.setClassName(StringEscapeUtils.escapeHtml4(model.getClassName()));
			model.setClassPK(StringEscapeUtils.escapeHtml4(model.getClassPK()));
			model.setSubject(StringEscapeUtils.escapeHtml4(model.getSubject()));
			model.setChoices(StringEscapeUtils.escapeHtml4(model.getChoices()));
			model.setTemplateNo(StringEscapeUtils.escapeHtml4(model.getTemplateNo()));
			model.setCommentable(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCommentable()))));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(Voting model) throws ModelListenerException {
		try {
			model.setClassName(StringEscapeUtils.escapeHtml4(model.getClassName()));
			model.setClassPK(StringEscapeUtils.escapeHtml4(model.getClassPK()));
			model.setSubject(StringEscapeUtils.escapeHtml4(model.getSubject()));
			model.setChoices(StringEscapeUtils.escapeHtml4(model.getChoices()));
			model.setTemplateNo(StringEscapeUtils.escapeHtml4(model.getTemplateNo()));
			model.setCommentable(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCommentable()))));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(VotingTempListener.class.getName());
}
