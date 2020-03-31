package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
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
import org.opencps.api.notarization.model.NotarizationResultsModel;
import org.opencps.api.notarization.model.NotarizationSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.NotarizationActions;
import org.opencps.dossiermgt.action.impl.NotarizationActionsImpl;
import org.opencps.dossiermgt.action.util.NotarizationCounterNumberGenerator;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil;
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
			if (notarizationNo == 0) {
				ConfigCounter cc = ConfigCounterLocalServiceUtil.fetchByCountrCode(groupId, NOTARIZATION_COUNTER);
				String notarizationNoText = NotarizationCounterNumberGenerator.generateCounterNumber(groupId, company.getCompanyId(), notarization.getNotarizationId(), 
						cc.getPatternCode(), cc, new LinkedHashMap<String, Object>(), new SearchContext());
				notarization.setNotarizationNo(Long.valueOf(notarizationNoText));
				notarization = NotarizationLocalServiceUtil.updateNotarization(notarization);
			}
			result = NotarizationUtils.mappingToNotarizationDetailModel(notarization);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	private static final String NOTARIZATION_COUNTER = "SO_CHUNG_THUC_BAN_SAO";
	
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
				if (dossierId == 0) {
					dossierId = oldNotarization.getDossierId();
				}
				if (notarizationNo == 0) {
					notarizationNo = oldNotarization.getNotarizationNo();
				}
				if (notarizationYear == 0) {
					notarizationYear = oldNotarization.getNotarizationYear();
				}
//				if (notarizationDate == 0) {
//					notarizationDate = oldNotarization.getNotarizationDate().getTime();
//				}
				if (Validator.isNull(signerName)) {
					signerName = oldNotarization.getSignerName();
				}
				if (Validator.isNull(signerPosition)) {
					signerPosition = oldNotarization.getSignerPosition();
				}
				if (Validator.isNull(statusCode)) {
					statusCode = oldNotarization.getStatusCode();
				}
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
			String fileName = query.getFileName();
			int totalRecord = query.getTotalRecord();
			int totalPage = query.getTotalPage() != null ? query.getTotalPage() : 0;
			int totalCopy = query.getTotalCopy() != null ? query.getTotalCopy() : 0;
			long totalFee = query.getTotalFee() != null ? query.getTotalFee() : 0;
			String notarizationNo = query.getNotarizationNo();
			int notarizationYear = query.getNotarizationYear() != null ? query.getNotarizationYear() : 0;
			String notarizationDate = query.getNotarizationDate();
			String signerName = query.getSignerName();
			String signerPosition = query.getSignerPosition();
			String statusCode = query.getStatusCode();
			int start = query.getStart() != 0 ? query.getStart() : QueryUtil.ALL_POS;
			int end = query.getEnd() != 0 ? query.getEnd() : QueryUtil.ALL_POS;
			
			results.setTotal(NotarizationLocalServiceUtil.countByAdvancedSearch(groupId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDate, signerName, signerPosition, statusCode));
			
			List<Notarization> lstNotarizations = NotarizationLocalServiceUtil.findByAdvancedSearch(groupId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDate, signerName, signerPosition, statusCode, start, end);
			
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
