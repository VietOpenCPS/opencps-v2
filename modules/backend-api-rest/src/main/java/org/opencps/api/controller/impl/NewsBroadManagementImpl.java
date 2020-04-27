package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.NewsBroadManagement;
import org.opencps.api.controller.util.NewsBoardUtils;
import org.opencps.api.newsbroad.model.NewsBoardDetailModel;
import org.opencps.api.newsbroad.model.NewsBoardInputModel;
import org.opencps.api.newsbroad.model.NewsBoardResultsModel;
import org.opencps.api.newsbroad.model.NewsBoardSearchModel;
import org.opencps.dossiermgt.action.NewsBoardActions;
import org.opencps.dossiermgt.action.impl.NewsBoardActionsImpl;
import org.opencps.dossiermgt.model.NewsBoard;
import org.opencps.dossiermgt.service.NewsBoardLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class NewsBroadManagementImpl implements NewsBroadManagement{

	private static final Log _log = LogFactoryUtil.getLog(NewsBroadManagementImpl.class);

	@Override
	public Response getListNewsBoard(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NewsBoardSearchModel search) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (Validator.isNull(search.getEnd()) || search.getEnd() == 0) {
				search.setStart(-1);
				search.setEnd(-1);
			}

			NewsBoardResultsModel results = new NewsBoardResultsModel();

			NewsBoardActions actions = new NewsBoardActionsImpl();
			JSONObject jsonData = actions.getListNewsBoard(groupId, search.getStart(), search.getEnd());

			int total = jsonData.getInt("total");
			results.setTotal(total);

			if (total > 0) {
				results.getData().addAll(NewsBoardUtils.mappingForGetList((List<NewsBoard>) jsonData.get("data")));
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getNewsBoardDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			
			NewsBoard newsBoard = NewsBoardLocalServiceUtil.fetchNewsBoard(id);;
			
			NewsBoardDetailModel result = NewsBoardUtils.mappingForGetDetail(newsBoard);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addNewsBoard(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NewsBoardInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();

		//BackendAuth auth = new BackendAuthImpl();
		//EFormInputModel eFromInputModel = new EFormInputModel();
		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			NewsBoardActions actions = new NewsBoardActionsImpl();

			String newsTitle = Validator.isNotNull(input.getNewsTitle()) ? input.getNewsTitle() : StringPool.BLANK;
			String newsContent = Validator.isNotNull(input.getNewsContent()) ? input.getNewsContent()
					: StringPool.BLANK;
			int newsStatus = input.getNewsStatus();


			NewsBoard newsBoard = actions.createNewsBoard(groupId, userId, newsTitle, newsContent, newsStatus, serviceContext);

			NewsBoardDetailModel result = NewsBoardUtils.mappingForGetDetail(newsBoard);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateNewsBoard(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NewsBoardInputModel input, long id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();
		long newsBoardId = GetterUtil.getLong(id);

		try {

			NewsBoard newsBoard = NewsBoardLocalServiceUtil.fetchNewsBoard(newsBoardId);

			if (newsBoard != null) {
				NewsBoardActions actions = new NewsBoardActionsImpl();

				String newsTitle = Validator.isNotNull(input.getNewsTitle()) ? input.getNewsTitle() : StringPool.BLANK;
				String newsContent = Validator.isNotNull(input.getNewsContent()) ? input.getNewsContent()
						: StringPool.BLANK;
				int newsStatus = input.getNewsStatus();

				newsBoard = actions.updateNewsBoard(newsBoardId, groupId, userId, newsTitle, newsContent, newsStatus, serviceContext);
			}

			NewsBoardDetailModel result = NewsBoardUtils.mappingForGetDetail(newsBoard);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteNewsBoard(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			NewsBoard newsBoard = NewsBoardLocalServiceUtil.deleteNewsBoard(id);
			
			NewsBoardDetailModel result = NewsBoardUtils.mappingForGetDetail(newsBoard);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
