package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.PaymentConfigManagement;
import org.opencps.api.controller.util.PaymentConfigUtils;
import org.opencps.api.paymentconfig.model.PaymentConfigInputModel;
import org.opencps.api.paymentconfig.model.PaymentConfigResultsModel;
import org.opencps.api.paymentconfig.model.PaymentConfigSearchModel;
import org.opencps.api.paymentconfig.model.PaymentConfigSingleInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class PaymentConfigManagementImpl implements PaymentConfigManagement {
//	private static final Log _log = LogFactoryUtil.getLog(PaymentConfigManagementImpl.class);
	@Override
	public Response getPaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentConfigSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(serviceContext.getCompanyId());

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			Hits hits = PaymentConfigLocalServiceUtil.searchLucene(params, sorts, query.getStart(), query.getEnd(),
					searchContext);

			long total = PaymentConfigLocalServiceUtil.countLucene(params, searchContext);

			PaymentConfigResultsModel results = new PaymentConfigResultsModel();

			results.setTotal(GetterUtil.getInteger(total));

			results.getData().addAll(PaymentConfigUtils.mappingDataModel(hits.toList()));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addPaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentConfigInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String govAgencyName = HtmlUtil.escape(input.getGovAgencyName());
			String govAgencyTaxNo = HtmlUtil.escape(input.getGovAgencyTaxNo());
			String invoiceTemplateNo = HtmlUtil.escape(input.getInvoiceTemplateNo());
			String invoiceIssueNo = HtmlUtil.escape(input.getInvoiceIssueNo());
			String invoiceLastNo = HtmlUtil.escape(input.getInvoiceLastNo());
			String bankInfo = HtmlUtil.escape(input.getBankInfo());
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updatePaymentConfig(groupId, 0,
					govAgencyCode, govAgencyName, govAgencyTaxNo,
					invoiceTemplateNo, invoiceIssueNo, invoiceLastNo, StringPool.BLANK,
					bankInfo, StringPool.BLANK, serviceContext);

			PaymentConfigInputModel result = PaymentConfigUtils.mappingToModel(paymentConfig);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getPaymentConfigDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfig(id);
			
			PaymentConfigInputModel result = PaymentConfigUtils.mappingToModel(paymentConfig);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updatePaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String govAgencyName = HtmlUtil.escape(input.getGovAgencyName());
			String govAgencyTaxNo = HtmlUtil.escape(input.getGovAgencyTaxNo());
			String invoiceTemplateNo = HtmlUtil.escape(input.getInvoiceTemplateNo());
			String invoiceIssueNo = HtmlUtil.escape(input.getInvoiceIssueNo());
			String invoiceLastNo = HtmlUtil.escape(input.getInvoiceLastNo());
			String bankInfo = HtmlUtil.escape(input.getBankInfo());
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updatePaymentConfig(groupId, id,
					govAgencyCode, govAgencyName, govAgencyTaxNo,
					invoiceTemplateNo, invoiceIssueNo, invoiceLastNo, StringPool.BLANK,
					bankInfo, StringPool.BLANK, serviceContext);

			PaymentConfigInputModel result = PaymentConfigUtils.mappingToModel(paymentConfig);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removePaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.removePaymentConfig(id);
			
			PaymentConfigInputModel result = PaymentConfigUtils.mappingToModel(paymentConfig);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfig(id);
			
			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getInvoiceForm());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updateInvoidForm(id, input.getValue(), serviceContext);

			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getInvoiceForm());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updateInvoidForm(id, input.getValue(), serviceContext);

			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getInvoiceForm());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}


	}

	@Override
	public Response getEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfig(id);
			
			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getEpaymentConfig());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String epaymentConfig = HtmlUtil.escape(input.getValue());
			
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updateEConfig(id, epaymentConfig, serviceContext);

			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getEpaymentConfig());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String key, PaymentConfigSingleInputModel input) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}
			
			String eConfig = PaymentConfigLocalServiceUtil.getPaymentConfig(id).getEpaymentConfig();
			
			JSONObject jsEConfig = JSONFactoryUtil.createJSONObject(eConfig);
			
			jsEConfig.put(key, input.getValue());

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.updateEConfig(id, jsEConfig.toString(), serviceContext);

			PaymentConfigSingleInputModel result = new PaymentConfigSingleInputModel();
			
			result.setValue(paymentConfig.getEpaymentConfig());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
