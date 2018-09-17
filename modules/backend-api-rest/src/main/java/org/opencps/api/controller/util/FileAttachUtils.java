package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.opencps.api.fileattach.model.FileAttachModel;
import org.opencps.api.fileattach.model.FileVersionModel;
import org.opencps.datamgt.constants.FileAttachTerm;
import org.opencps.datamgt.model.FileAttach;

import backend.utils.APIDateTimeUtils;

public class FileAttachUtils {

	static Log _log = LogFactoryUtil.getLog(FileAttachUtils.class);

	public static List<FileAttachModel> mapperFileAttachList(
		List<Document> list) {

		List<FileAttachModel> results = new ArrayList<>();

		FileAttachModel ett = null;

		try {
			for (Document doc : list) {
				ett = new FileAttachModel();

				ett.setFileAttachId(Long.valueOf(doc.get("entryClassPK")));

				ett.setCreateDate(
					Validator.isNotNull(doc.get(FileAttachTerm.CREATE_DATE))
						? APIDateTimeUtils.convertDateToString(
							doc.getDate(FileAttachTerm.CREATE_DATE),
							APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
					Validator.isNotNull(doc.getDate("modified"))
						? APIDateTimeUtils.convertDateToString(
							doc.getDate("modified"),
							APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setFullname(doc.get(FileAttachTerm.FULLNAME));
				ett.setEmail(doc.get(FileAttachTerm.EMAIL));
				String fileName = StringPool.BLANK;
				String fileType = StringPool.BLANK;
				String fileSize = StringPool.BLANK;
				String version = StringPool.BLANK;

				long fileEntryId =
					GetterUtil.get(doc.get(FileAttachTerm.FILE_ENTRY_ID), 0);

				if (fileEntryId > 0) {

					DLFileEntry dlFileEntry =
						DLFileEntryLocalServiceUtil.fetchDLFileEntry(
							fileEntryId);

					fileName = dlFileEntry.getFileName();
					fileType = dlFileEntry.getMimeType();
					fileSize = String.valueOf(dlFileEntry.getSize());
					version = dlFileEntry.getVersion();

				}

				ett.setFileName(fileName);
				ett.setFileType(fileType);
				ett.setFileSize(fileSize);
				ett.setVersion(version);

				ett.setSource(doc.get(FileAttachTerm.SOURCE));
				ett.setSourceUrl(doc.get(FileAttachTerm.SOURCE_URL));
				ett.setDocFileId(
					String.valueOf(doc.get(FileAttachTerm.DOCFILE_ID)));
				results.add(ett);
			}
		}
		catch (ParseException e) {
			_log.error(e);
		}

		return results;
	}

	public static FileAttachModel mapperFileAttachModel(FileAttach fileAttach) {

		FileAttachModel ett = new FileAttachModel();

		ett.setFileAttachId(fileAttach.getFileAttachId());
		ett.setCreateDate(
			Validator.isNotNull(fileAttach.getCreateDate())
				? APIDateTimeUtils.convertDateToString(
					fileAttach.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
				: StringPool.BLANK);
		ett.setModifiedDate(
			Validator.isNotNull(fileAttach.getModifiedDate())
				? APIDateTimeUtils.convertDateToString(
					fileAttach.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
				: StringPool.BLANK);
		ett.setFullname(fileAttach.getFullName());
		ett.setEmail(fileAttach.getEmail());

		// String fileName = StringPool.BLANK;
		String fileType = StringPool.BLANK;
		String fileSize = StringPool.BLANK;
		String version = StringPool.BLANK;

		long fileEntryId = fileAttach.getFileEntryId();

		if (fileEntryId > 0) {

			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);

			// fileName = dlFileEntry.getFileName();
			fileType = dlFileEntry.getMimeType();
			fileSize = String.valueOf(dlFileEntry.getSize());
			version = dlFileEntry.getVersion();

		}

		ett.setFileName(fileAttach.getFileName());
		ett.setFileType(fileType);
		ett.setFileSize(fileSize);
		ett.setVersion(version);

		ett.setSource(fileAttach.getSource());
		ett.setSourceUrl(fileAttach.getSourceUrl());
		ett.setDocFileId(String.valueOf(fileAttach.getDocFileId()));

		return ett;
	}

	public static List<FileVersionModel> mapperFileAttachVersionList(
		String fileName, JSONArray versions) {

		List<FileVersionModel> results = new ArrayList<FileVersionModel>();

		if (versions != null && versions.length() > 0) {
			try {
				for (int i = 0; i < versions.length(); i++) {
					JSONObject version = versions.getJSONObject(i);
					FileVersionModel fileAttachVersionModel =
						new FileVersionModel();

					fileAttachVersionModel.setFileEntryId(
						version.getLong("fileEntryId"));
					fileAttachVersionModel.setCreateDate(
						version.getString("createdDate"));
					fileAttachVersionModel.setFileName(fileName);
					fileAttachVersionModel.setUserId(version.getLong("userId"));
					fileAttachVersionModel.setUserName(
						version.getString("userName"));
					fileAttachVersionModel.setVersion(
						version.getString("version"));

					results.add(fileAttachVersionModel);
				}
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return results;
	}
}
