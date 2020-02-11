package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.NotarizationManagement;
import org.opencps.api.controller.util.NotarizationUtils;
import org.opencps.api.notarization.model.NotarizationDetailModel;
import org.opencps.api.notarization.model.NotarizationInputModel;
import org.opencps.api.notarization.model.NotarizationModel;
import org.opencps.api.notarization.model.NotarizationResultsModel;
import org.opencps.api.notarization.model.NotarizationSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.NotarizationActions;
import org.opencps.dossiermgt.action.impl.NotarizationActionsImpl;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.service.NotarizationLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class NotarizationManagementImpl implements NotarizationManagement {

	@Override
	public Response addNotarization(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotarizationInputModel input) {
		NotarizationActions actions = new NotarizationActionsImpl();

		NotarizationDetailModel result = new NotarizationDetailModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();


		try {
			if (!auth2.checkToken(request, header)) {
				throw new UnauthenticationException();
			}
			Long dossierId = input.getDossierId();
			String fileName = input.getFileName();
			int totalRecord = input.getTotalRecord();
			int totalPage = input.getTotalPage();
			int totalCopy = input.getTotalCopy();
			long totalFee = input.getTotalFee();
			long notarizationNo = input.getNotarizationNo();
			int notarizationYear = input.getNotarizationYear();
			long notarizationDate = input.getNotarizationDate();
			String signerName = input.getSignerName();
			String signerPosition = input.getSignerPosition();
			String statusCode = input.getStatusCode();
			Date notarizationDateFmt = new Date(notarizationDate);
			
			Notarization notarization = actions.createNotarization(groupId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDateFmt, signerName, signerPosition, statusCode, serviceContext);
			result = NotarizationUtils.mappingToNotarizationDetailModel(notarization);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateNotarization(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long notarizationId, NotarizationInputModel input) {
		NotarizationActions actions = new NotarizationActionsImpl();

		NotarizationDetailModel result = new NotarizationDetailModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		try {
			if (!auth2.checkToken(request, header)) {
				throw new UnauthenticationException();
			}
			Notarization oldNotarization = NotarizationLocalServiceUtil.fetchNotarization(notarizationId);
			
			Long dossierId = input.getDossierId();
			String fileName = input.getFileName();
			int totalRecord = input.getTotalRecord();
			int totalPage = input.getTotalPage();
			int totalCopy = input.getTotalCopy();
			long totalFee = input.getTotalFee();
			long notarizationNo = input.getNotarizationNo();
			int notarizationYear = input.getNotarizationYear();
			long notarizationDate = input.getNotarizationDate();
			String signerName = input.getSignerName();
			String signerPosition = input.getSignerPosition();
			String statusCode = input.getStatusCode();
			Date notarizationDateFmt = new Date(notarizationDate);
			
			if (oldNotarization == null) {
				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(result).build();
			}
			else {
				Notarization notarization = actions.updateNotarization(groupId, notarizationId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDateFmt, signerName, signerPosition, statusCode, serviceContext);
				result = NotarizationUtils.mappingToNotarizationDetailModel(notarization);

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();				
			}

		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(NotarizationManagementImpl.class);

	@Override
	public Response getNotarizations(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotarizationSearchModel query) {
		NotarizationResultsModel results = new NotarizationResultsModel();
		
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long dossierId = query.getDossierId();
			results.setTotal(NotarizationLocalServiceUtil.countByG_DID(groupId, query.getDossierId()));
			List<Notarization> lstNotarizations = NotarizationLocalServiceUtil.findByG_DID(groupId, dossierId);
			
			results.getData().addAll(NotarizationUtils.mappingToNotarizationResults(lstNotarizations));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getNotarizationDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		NotarizationDetailModel results = new NotarizationDetailModel();
		BackendAuth auth = new BackendAuthImpl();
		Notarization notarization = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			notarization = NotarizationLocalServiceUtil.fetchNotarization(id);

			results = NotarizationUtils.mappingToNotarizationDetailModel(notarization);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteNotarization(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		NotarizationDetailModel results = new NotarizationDetailModel();
		BackendAuth auth = new BackendAuthImpl();
		Notarization notarization = null;
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			notarization = NotarizationLocalServiceUtil.fetchNotarization(id);
			
			NotarizationLocalServiceUtil.removeNotarization(id);

			results = NotarizationUtils.mappingToNotarizationDetailModel(notarization);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}
}
