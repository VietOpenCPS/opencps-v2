package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.PaymentFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.PaymentFileUtils;
import org.opencps.api.dossierfile.model.DossierFileResultsModel;
import org.opencps.api.paymentFile.model.EpaymentProfileJsonModel;
import org.opencps.api.paymentFile.model.PaymentFileInputModel;
import org.opencps.api.paymentFile.model.PaymentFileResultModel;
import org.opencps.api.paymentFile.model.PaymentFileSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

public class PaymentFileManagementImpl implements PaymentFileManagement{

	@Override
	public Response getPaymentFilesByPaymentId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
//		// TODO Auto-generated method stub
//		PaymentFileResultModel results = new PaymentFileResultModel();
//
//		// TODO: check user is login
//		BackendAuth auth = new BackendAuthImpl();
//
//		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			List<PaymentFile> paymentFiles = PaymentFileLocalServiceUtil.getPaymentFilesByPaymentId(id);
//
//			results.setTotal(paymentFiles.size());
//			results.getData().addAll(PaymentFileUtils.mappingToPaymentFileData(paymentFiles));
//
//			return Response.status(200).entity(results).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
		return null;
	}

	@Override
	public Response addPaymentFileByPaymentId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, PaymentFileInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getPaymentFileByReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, String referenceUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getEpaymentProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addEpaymentProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid, EpaymentProfileJsonModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updatePaymentFileConfirm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updatePaymentFileApproval(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response downloadConfirmFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long id, String referenceUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response downloadInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long id, String referenceUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getPaymentFiles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentFileSearchModel search) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response processException(Exception e) {
		ErrorMsg error = new ErrorMsg();

		if (e instanceof UnauthenticationException) {
			error.setMessage("Non-Authoritative Information.");
			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
			error.setDescription("Non-Authoritative Information.");

			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		} else {
			if (e instanceof UnauthorizationException) {
				error.setMessage("Unauthorized.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Unauthorized.");

				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

			} else {

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}
}
