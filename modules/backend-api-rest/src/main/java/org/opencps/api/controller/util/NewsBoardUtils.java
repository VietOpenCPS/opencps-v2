package org.opencps.api.controller.util;

import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.newsbroad.model.NewsBoardDetailModel;
import org.opencps.api.newsbroad.model.NewsBoardModel;
import org.opencps.dossiermgt.model.NewsBoard;

public class NewsBoardUtils {

	public static List<NewsBoardModel> mappingForGetList(List<NewsBoard> dataList) {
		List<NewsBoardModel> ouputs = new ArrayList<NewsBoardModel>();

		if (dataList != null && dataList.size() > 0) {
			for (NewsBoard data : dataList) {
				NewsBoardModel model = new NewsBoardModel();
				
				model.setNewBoardId(data.getNewsBoardId());
				model.setGroupId(data.getGroupId());
				model.setCreateDate(data.getCreateDate() != null ? data.getCreateDate().getTime() : 0l);
				model.setModifiedDate(data.getModifiedDate() != null ? data.getModifiedDate().getTime() : 0l);
				model.setNewsTitle(data.getNewsTitle());
				model.setNewsContent(data.getNewsContent());
				model.setNewsStatus(data.getNewsStatus());

				ouputs.add(model);
			}
		}
		return ouputs;
	}

	public static NewsBoardDetailModel mappingForGetDetail(NewsBoard data) {

		NewsBoardDetailModel model = new NewsBoardDetailModel();

		if (Validator.isNull(data)) {
			return model;
		}

		model.setNewBoardId(data.getNewsBoardId());
		model.setGroupId(data.getGroupId());
		model.setCreateDate(data.getCreateDate() != null ? data.getCreateDate().getTime() : 0l);
		model.setModifiedDate(data.getModifiedDate() != null ? data.getModifiedDate().getTime() : 0l);
		model.setNewsTitle(data.getNewsTitle());
		model.setNewsContent(data.getNewsContent());
		model.setNewsStatus(data.getNewsStatus());

		return model;
	}

}
