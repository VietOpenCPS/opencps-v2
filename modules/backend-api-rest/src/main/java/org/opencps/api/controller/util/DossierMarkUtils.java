
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossiermark.model.DossierMarkModel;
import org.opencps.api.dossiermark.model.DossierMarkResultDetailModel;
import org.opencps.dossiermgt.model.DossierMark;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DossierMarkUtils {

	private static final Log _log = LogFactoryUtil.getLog(DossierMarkUtils.class);

	public static DossierMarkResultDetailModel mappingDossierMarkDetailModel(DossierMark dossierMark) {
		if (dossierMark == null) {
			return null;
		}

		DossierMarkResultDetailModel model = new DossierMarkResultDetailModel();
		try {
			model.setDossierMarkId(dossierMark.getDossierMarkId());
			model.setFileCheck(dossierMark.getFileCheck());
			model.setFileMark(dossierMark.getFileMark());
			model.setFileComment(dossierMark.getFileComment());
			model.setRecordCount(dossierMark.getRecordCount());
		} catch (Exception e) {
			_log.error(e);
		}
		return model;
	}

	public static List<DossierMarkModel> mappingDossierMarks(List<DossierMark> lstDossierMark){
		List<DossierMarkModel> result = new ArrayList<DossierMarkModel>();
		try {
			
			for(DossierMark dossierMark : lstDossierMark){
				DossierMarkModel model = new DossierMarkModel();
				
				model.setDossierId(dossierMark.getDossierId());
				model.setDossierPartNo(dossierMark.getDossierPartNo());
				model.setFileCheck(dossierMark.getFileCheck());
				model.setFileMark(dossierMark.getFileMark());
				model.setFileComment(dossierMark.getFileComment());
				model.setRecordCount(dossierMark.getRecordCount());
				
				result.add(model);
			}
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}
}
