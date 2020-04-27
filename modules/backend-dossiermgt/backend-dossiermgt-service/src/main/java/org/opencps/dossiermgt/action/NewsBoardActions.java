package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.dossiermgt.model.NewsBoard;

public interface NewsBoardActions {

	public JSONObject getListNewsBoard(long groupId, int start, int end);

	public NewsBoard getNewsBoard(long newsBoardId);

	public NewsBoard createNewsBoard(long groupId, long userId, String newsTitle, String newsContent, int newsStatus,
			ServiceContext serviceContext);

	public NewsBoard updateNewsBoard(long newsBoardId, long groupId, long userId, String newsTitle, String newsContent,
			int newsStatus, ServiceContext serviceContext);

	public void deleteNewsBoard(long newsBoardId);

}