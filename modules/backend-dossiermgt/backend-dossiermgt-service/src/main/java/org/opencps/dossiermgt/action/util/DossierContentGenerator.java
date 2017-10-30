package org.opencps.dossiermgt.action.util;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierContentGenerator {

	public static String getDossierNote(long groupId, long dossierId) {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		if (Validator.isNotNull(dossier)) {

			ProcessOption option = getProcessOption(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(),
					dossier.getDossierTemplateNo());
			
			String source = option.getInstructionNote();
			
			//TODO add keys and values that need to be replaced
			
			String [] keys = new String [] {};
			String [] values = new String [] {};
			
			return replateContent(source, keys, values);

		} else {
			return StringPool.BLANK;
		}
	}

	public static String getSubmissionNote(long groupId, long dossierId) {
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		if (Validator.isNotNull(dossier)) {

			ProcessOption option = getProcessOption(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(),
					dossier.getDossierTemplateNo());
			
			String source = option.getSubmissionNote();
			
			//TODO add keys and values that need to be replaced
			
			String [] keys = new String [] {};
			String [] values = new String [] {};
			
			return replateContent(source, keys, values);

		} else {
			return StringPool.BLANK;
		}

	}

	private static String replateContent(String strSource, String[] keys, String[] values) {
		return StringUtil.replace(strSource, keys, values);
	}

	private static ProcessOption getProcessOption(long groupId, String serviceCode, String govAgencyCode,
			String dossierTemplateNo) {
		try {
			ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode, govAgencyCode);

			return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
					config.getServiceConfigId());
		} catch (Exception e) {
			return null;
		}

	}
}
