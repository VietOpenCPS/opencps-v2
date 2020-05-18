package org.opencps.usermgt.action.impl;

import java.util.List;

import org.opencps.usermgt.action.FileItemActions;
import org.opencps.usermgt.model.FileItem;
import org.opencps.usermgt.service.FileItemLocalServiceUtil;

public class FileItemActionsImpl implements FileItemActions {

	@Override
	public List<FileItem> getFileItems(long groupId, int status) {
		return FileItemLocalServiceUtil.findByG_S(groupId, status);
	}

}
