package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.fileitemmgt.model.FileItemModel;
import org.opencps.kernel.util.APIDateTimeUtil;
import org.opencps.usermgt.model.FileItem;

public class FileItemUtils {
	public static List<FileItemModel> mappingToFileItemResults(List<FileItem> lstFileItems) {

		List<FileItemModel> data = new ArrayList<FileItemModel>();

		for (FileItem fileItem : lstFileItems) {
			FileItemModel model = new FileItemModel();
			
			model.setFileItemId(fileItem.getFileItemId());
			model.setCreateDate(APIDateTimeUtil.convertDateToString(fileItem.getCreateDate(), APIDateTimeUtil._VNDATE));
			model.setModifiedDate(APIDateTimeUtil.convertDateToString(fileItem.getModifiedDate(), APIDateTimeUtil._VNDATE));
			model.setFileTemplateNo(fileItem.getFileTemplateNo());
			model.setGroupId(fileItem.getGroupId());
			model.setName(fileItem.getName());
			model.setSize(fileItem.getSize());
			model.setStatus(fileItem.getStatus());
			model.setUserId(fileItem.getUserId());
			model.setUserName(fileItem.getUserName());
			model.setFileType(fileItem.getFileType());
			
			data.add(model);
		}

		return data;
	}
}
