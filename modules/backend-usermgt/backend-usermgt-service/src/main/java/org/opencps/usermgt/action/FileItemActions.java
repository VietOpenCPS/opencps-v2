package org.opencps.usermgt.action;

import java.util.List;

import org.opencps.usermgt.model.FileItem;

public interface FileItemActions {
	public List<FileItem> getFileItems(long groupId, int status);

}
