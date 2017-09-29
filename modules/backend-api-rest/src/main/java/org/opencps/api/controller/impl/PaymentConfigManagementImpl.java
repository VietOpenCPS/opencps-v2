package org.opencps.api.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.PaymentConfigManagement;
import org.opencps.api.paymentconfig.model.PaymentConfigInputModel;
import org.opencps.api.paymentconfig.model.PaymentConfigSearchModel;
import org.opencps.api.paymentconfig.model.PaymentConfigSingleInputModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

public class PaymentConfigManagementImpl implements PaymentConfigManagement{

	@Override
	public Response getPaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentConfigSearchModel query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addPaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentConfigInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getPaymentConfigDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updatePaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removePaymentConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateInvoiceForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, PaymentConfigSingleInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateEpaymentconfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long key, PaymentConfigSingleInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

}
