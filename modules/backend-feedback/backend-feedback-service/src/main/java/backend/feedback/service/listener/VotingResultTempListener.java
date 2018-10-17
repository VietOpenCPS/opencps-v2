package backend.feedback.service.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.osgi.service.component.annotations.Component;

import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingResultLocalServiceUtil;

@Component(immediate = true, service = ModelListener.class)
public class VotingResultTempListener extends BaseModelListener<VotingResult> {

	@Override
	public void onAfterCreate(VotingResult model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(VotingResult model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(VotingResult model) throws ModelListenerException {
//		try {
//			model.setVotingId(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getVotingId()))));
//			model.setFullname(HtmlUtil.escape(model.getFullname()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setComment(HtmlUtil.escape(model.getComment()));
//			model.setSelected(HtmlUtil.escape(model.getSelected()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(VotingResult model) throws ModelListenerException {
//		try {
//			model.setVotingId(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getVotingId()))));
//			model.setFullname(HtmlUtil.escape(model.getFullname()));
//			model.setEmail(HtmlUtil.escape(model.getEmail()));
//			model.setComment(HtmlUtil.escape(model.getComment()));
//			model.setSelected(HtmlUtil.escape(model.getSelected()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(VotingResultTempListener.class.getName());
}
