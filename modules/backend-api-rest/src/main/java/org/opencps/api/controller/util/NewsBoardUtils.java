package org.opencps.api.controller.util;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.booking.model.BookingDataModel;
import org.opencps.api.newsbroad.model.NewsBoardDetailModel;
import org.opencps.api.newsbroad.model.NewsBoardModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.model.NewsBoard;

public class NewsBoardUtils {

	public static List<NewsBoardModel> mappingForGetList(List<NewsBoard> dataList) {
		List<NewsBoardModel> ouputs = new ArrayList<NewsBoardModel>();

		if (dataList != null && dataList.size() > 0) {
			for (NewsBoard data : dataList) {
				NewsBoardModel model = new NewsBoardModel();
				
				model.setNewBoardId(data.getNewBoardId());
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

		model.setNewBoardId(data.getNewBoardId());
		model.setGroupId(data.getGroupId());
		model.setCreateDate(data.getCreateDate() != null ? data.getCreateDate().getTime() : 0l);
		model.setModifiedDate(data.getModifiedDate() != null ? data.getModifiedDate().getTime() : 0l);
		model.setNewsTitle(data.getNewsTitle());
		model.setNewsContent(data.getNewsContent());
		model.setNewsStatus(data.getNewsStatus());

		return model;
	}

}
