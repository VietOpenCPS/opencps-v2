package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.HtmlUtil;

import org.opencps.usermgt.model.Question;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ModelListener.class)
public class QuestionTempListener extends BaseModelListener<Question> {

	@Override
	public void onBeforeCreate(Question question) throws ModelListenerException {
		try {
			question.setEmail(HtmlUtil.escape(question.getEmail()));
			question.setContent(HtmlUtil.escape(question.getContent()));
			question.setDomainCode(HtmlUtil.escape(question.getDomainCode()));
			question.setDomainName(HtmlUtil.escape(question.getDomainName()));
			question.setGovAgencyCode(HtmlUtil.escape(question.getGovAgencyCode()));
			question.setGovAgencyName(HtmlUtil.escape(question.getGovAgencyName()));
			question.setQuestionType(HtmlUtil.escape(question.getQuestionType()));
			question.setSubDomainCode(HtmlUtil.escape(question.getSubDomainCode()));
			question.setSubDomainName(HtmlUtil.escape(question.getSubDomainName()));
			question.setPhone(HtmlUtil.escape(question.getPhone()));
			question.setAddress(HtmlUtil.escape(question.getAddress()));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(Question question) throws ModelListenerException {
		try {
			question.setEmail(HtmlUtil.escape(question.getEmail()));
			question.setContent(HtmlUtil.escape(question.getContent()));
			question.setDomainCode(HtmlUtil.escape(question.getDomainCode()));
			question.setDomainName(HtmlUtil.escape(question.getDomainName()));
			question.setGovAgencyCode(HtmlUtil.escape(question.getGovAgencyCode()));
			question.setGovAgencyName(HtmlUtil.escape(question.getGovAgencyName()));
			question.setQuestionType(HtmlUtil.escape(question.getQuestionType()));
			question.setSubDomainCode(HtmlUtil.escape(question.getSubDomainCode()));
			question.setSubDomainName(HtmlUtil.escape(question.getSubDomainName()));
			question.setPhone(HtmlUtil.escape(question.getPhone()));
			question.setAddress(HtmlUtil.escape(question.getAddress()));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(QuestionTempListener.class.getName());
}
