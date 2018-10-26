package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.DocumentType;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DocumentTypeTempListener extends BaseModelListener<DocumentType> {

	@Override
	public void onAfterCreate(DocumentType model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(DocumentType model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(DocumentType model) throws ModelListenerException {
//		try {
//			model.setTypeCode(HtmlUtil.escape(model.getTypeCode()));
//			model.setTemplateClass(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getTemplateClass()))));
//			model.setDocumentName(HtmlUtil.escape(model.getDocumentName()));
//			model.setCodePattern(HtmlUtil.escape(model.getCodePattern()));
//			model.setDocumentScript(HtmlUtil.escape(model.getDocumentScript()));
//			model.setDocSync(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDocSync()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(DocumentType model) throws ModelListenerException {
//		try {
//			model.setTypeCode(HtmlUtil.escape(model.getTypeCode()));
//			model.setTemplateClass(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getTemplateClass()))));
//			model.setDocumentName(HtmlUtil.escape(model.getDocumentName()));
//			model.setCodePattern(HtmlUtil.escape(model.getCodePattern()));
//			model.setDocumentScript(HtmlUtil.escape(model.getDocumentScript()));
//			model.setDocSync(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDocSync()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DocumentTypeTempListener.class.getName());
}
