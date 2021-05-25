package org.opencps.dossiermgt.action.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

public class DossierContentGenerator {
	private static final Log _log = LogFactoryUtil.getLog(DossierContentGenerator.class);
	
	public static String getDossierNote(long groupId, long dossierId) {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		if (Validator.isNotNull(dossier)) {

			ProcessOption option = getProcessOption(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(),
					dossier.getDossierTemplateNo());

			String source = option != null ? option.getInstructionNote() : StringPool.BLANK;

			// TODO add keys and values that need to be replaced

			String[] keys = new String[] {};
			String[] values = new String[] {};

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

			String source = option != null ? option.getSubmissionNote() : StringPool.BLANK;

			// TODO add keys and values that need to be replaced

			String[] keys = new String[] {};
			String[] values = new String[] {};

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
			_log.debug(e);
			//_log.error(e);
			return null;
		}

	}

	private static final String BRIEFNOTE_PATTERN = "\\[\\$(.*?)\\$\\]";
	private static final String VARIABLE = "$variable";
	private static final String VARIABLE_SUBMISSION = "$variable";
	private static final String SUBMISSION_PATTERN = "\\[\\$(.*?)\\$\\]";
	
	public static String getBriefNote(long groupId, long dossierId, String briefNotePattern) {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		String briefNote;

		LinkedHashMap<String, String> patternContentMaps = new LinkedHashMap<String, String>();

		if (Validator.isNotNull(dossier) && Validator.isNotNull(briefNotePattern)) {

			String pattern = BRIEFNOTE_PATTERN;

			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(briefNotePattern);

			int count = 0;

			while (m.find()) {
				briefNotePattern = briefNotePattern.replace(m.group(0), VARIABLE + count + StringPool.DOLLAR);
				patternContentMaps.put(VARIABLE + count + StringPool.DOLLAR, m.group(1));
				m = r.matcher(briefNotePattern);
				count++;
			}

			for (Map.Entry<String, String> entry : patternContentMaps.entrySet()) {
				String tmpKey = entry.getKey();
				String patternContent = entry.getValue();
				String[] textSplit = StringUtil.split(patternContent, StringPool.AT);
				if (textSplit == null || textSplit.length < 2) {
					briefNotePattern = briefNotePattern.replace(tmpKey, StringPool.BLANK);
				} else {
					String dataKey = textSplit[0];
					String fileTemplateNo = textSplit[1];
					
					DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
							fileTemplateNo, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));
					
					
					if (dossierFile == null) {
						briefNotePattern = briefNotePattern.replace(tmpKey, StringPool.BLANK);
					} else {
						
						String formData = dossierFile.getFormData();
						if (Validator.isNull(formData)) {
							briefNotePattern = briefNotePattern.replace(tmpKey, StringPool.BLANK);
						} else {
							try {
								String value = StringPool.BLANK;
								
								JSONObject object = JSONFactoryUtil.createJSONObject(formData);
								if(object.has(dataKey)){
									value = object.getString(dataKey);
								}
								
								briefNotePattern = briefNotePattern.replace(tmpKey,
										Validator.isNotNull(value) ? value : StringPool.BLANK);
							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
								briefNotePattern = briefNotePattern.replace(tmpKey, StringPool.BLANK);
							}
						}
					}
				}
			}

			briefNote = briefNotePattern;

			return briefNote;

		} else {
			return StringPool.BLANK;
		}
	}
	public static String getSubmissionNote(long dossierId, String submissionNotePattern) {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		String submissionNote;

		LinkedHashMap<String, String> patternContentMaps = new LinkedHashMap<String, String>();
		JSONObject jsonObject = null;

		if (Validator.isNotNull(dossier) && Validator.isNotNull(submissionNotePattern)) {

			String pattern = SUBMISSION_PATTERN;

			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(submissionNotePattern);

			int count = 0;

			while (m.find()) {
				submissionNotePattern = submissionNotePattern.replace(m.group(0), VARIABLE_SUBMISSION + count + StringPool.DOLLAR);
				patternContentMaps.put(VARIABLE_SUBMISSION + count + StringPool.DOLLAR, m.group(1));
				m = r.matcher(submissionNotePattern);
				count++;
			}
			try {
				 jsonObject = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier));
			}catch (Exception e){
				_log.error(e);
				return e.getMessage();
			}
			for (Map.Entry<String, String> entry : patternContentMaps.entrySet()) {
				String tmpKey = entry.getKey();
				String patternContent = entry.getValue();
				String[] textSplit = StringUtil.split(patternContent, StringPool.AT);
				if (textSplit == null || textSplit.length < 2) {
					Iterator<String> keys = jsonObject.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						String value = jsonObject.getString(key);
						if(key.equals(patternContent)){
							if(key.equals(DossierTerm.DOSSIER_FILE)){
								List<DossierFile> lstFile = DossierFileLocalServiceUtil.findByDID_GROUP(dossier.getGroupId(), dossierId);
								if(lstFile != null && !lstFile.isEmpty()){
									String fileName = "";
									int countFile = 0;
									if(lstFile.size() > 1) {
										for (DossierFile item : lstFile) {
											countFile++;
											fileName  += ConstantUtils.HTML_OPEN_SPAN + " " + countFile + "." + item.getDisplayName() + ConstantUtils.HTML_CLOSE_SPAN;
										}
										submissionNotePattern = submissionNotePattern.replace(tmpKey, fileName);
									}else{
										fileName = lstFile.get(0).getDisplayName();
										submissionNotePattern = submissionNotePattern.replace(tmpKey, fileName);
									}
								}
							}else {
								submissionNotePattern = submissionNotePattern.replace(tmpKey, value);
							}
							break;
						}
					}
				} else {
					String dataKey = textSplit[0];
					String fileTemplateNo = textSplit[1];

					DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
							fileTemplateNo, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));


					if (dossierFile == null) {
						submissionNotePattern = submissionNotePattern.replace(tmpKey, StringPool.BLANK);
					} else {

						String formData = dossierFile.getFormData();
						if (Validator.isNull(formData)) {
							submissionNotePattern = submissionNotePattern.replace(tmpKey, StringPool.BLANK);
						} else {
							try {
								String value = StringPool.BLANK;

								JSONObject object = JSONFactoryUtil.createJSONObject(formData);
								if(object.has(dataKey)){
									value = object.getString(dataKey);
								}

								submissionNotePattern = submissionNotePattern.replace(tmpKey,
										Validator.isNotNull(value) ? value : StringPool.BLANK);
							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
								submissionNotePattern = submissionNotePattern.replace(tmpKey, StringPool.BLANK);
							}
						}
					}
				}
			}

			submissionNote = submissionNotePattern;

			return submissionNote;

		} else {
			return StringPool.BLANK;
		}
	}
}
