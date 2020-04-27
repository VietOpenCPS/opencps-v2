package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.dossiermgt.action.NewsBoardActions;
import org.opencps.dossiermgt.model.NewsBoard;
import org.opencps.dossiermgt.service.NewsBoardLocalServiceUtil;

public class NewsBoardActionsImpl implements NewsBoardActions {

	private static final Log _log = LogFactoryUtil.getLog(NewsBoardActionsImpl.class);

	@Override
	public JSONObject getListNewsBoard(long groupId, int start, int end) {
		
		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		//
		List<NewsBoard> newsBoardList = NewsBoardLocalServiceUtil.getNewsBoardList(groupId, start, end);
		if (newsBoardList != null && newsBoardList.size() > 0) {
			jsonData.put("data", newsBoardList);
			//
			int total = NewsBoardLocalServiceUtil.countByNewsBoardList(groupId);
			jsonData.put("total", total);
		} else {
			jsonData.put("total", 0);
		}
		return jsonData;
	}

	@Override
	public NewsBoard getNewsBoard(long newBoardId) {
		return NewsBoardLocalServiceUtil.fetchNewsBoard(newBoardId);
	}

	@Override
	public NewsBoard createNewsBoard(long groupId, long userId, String newsTitle, String newsContent, int newsStatus,
			ServiceContext serviceContext) {
		return NewsBoardLocalServiceUtil.createNewsBoard(groupId, userId, newsTitle, newsContent, newsStatus, serviceContext);
	}

	@Override
	public NewsBoard updateNewsBoard(long newsBoardId, long groupId, long userId, String newsTitle, String newsContent,
			int newsStatus, ServiceContext serviceContext) {
		return NewsBoardLocalServiceUtil.updateNewsBoard(newsBoardId, groupId, userId, newsTitle, newsContent,
				newsStatus, serviceContext);
	}

	@Override
	public void deleteNewsBoard(long newsBoardId) {
		try {
			NewsBoardLocalServiceUtil.deleteNewsBoard(newsBoardId);
		} catch (PortalException e) {
			_log.debug(e);
		}
	}

}
