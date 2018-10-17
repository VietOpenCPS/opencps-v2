package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.DossierPart;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierPartTempListener extends BaseModelListener<DossierPart> {

	@Override
	public void onAfterCreate(DossierPart model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(DossierPart model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(DossierPart model) throws ModelListenerException {
//		try {
//			model.setTemplateNo(HtmlUtil.escape(model.getTemplateNo()));
//			model.setPartNo(HtmlUtil.escape(model.getPartNo()));
//			model.setPartName(HtmlUtil.escape(model.getPartName()));
//			model.setPartTip(HtmlUtil.escape(model.getPartTip()));
//			model.setPartType(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getPartType()))));
//			model.setMultiple(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getMultiple()))));
//			model.setRequired(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRequired()))));
//			model.setFileTemplateNo(HtmlUtil.escape(model.getFileTemplateNo()));
//			model.setESign(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getESign()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(DossierPart model) throws ModelListenerException {
//		try {
//			model.setTemplateNo(HtmlUtil.escape(model.getTemplateNo()));
//			model.setPartNo(HtmlUtil.escape(model.getPartNo()));
//			model.setPartName(HtmlUtil.escape(model.getPartName()));
//			model.setPartTip(HtmlUtil.escape(model.getPartTip()));
//			model.setPartType(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getPartType()))));
//			model.setMultiple(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getMultiple()))));
//			model.setRequired(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRequired()))));
//			model.setFileTemplateNo(HtmlUtil.escape(model.getFileTemplateNo()));
//			model.setESign(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getESign()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DossierTemplateTempListener.class.getName());
}
