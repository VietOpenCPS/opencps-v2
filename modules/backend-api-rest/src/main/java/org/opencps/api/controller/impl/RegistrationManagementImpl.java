package org.opencps.api.controller.impl;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.RegistrationManagement;
import org.opencps.api.controller.util.RegistrationUtils;
import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.api.registration.model.RegistrationInputModel;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.impl.RegistrationImpl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationManagementImpl implements RegistrationManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationManagementImpl.class);

	@Override
	public Response getList(String stage, String agency, String keyword, String owner, String sort, String submitting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response add(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, RegistrationInputModel input) {
		RegistrationDetailModel result = null;
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Registration registration =   action.insert(groupId, input.getApplicantName(), input.getApplicantIdType(), input.getApplicantIdNo(),
					input.getApplicantIdDate(), input.getAddress(), input.getCityCode(), input.getCityName(),
					input.getDistrictCode(), input.getDistrictName(), input.getWardCode(), input.getWardName(),
					input.getContactName(), input.getContactTelNo(), input.getContactEmail(), input.getGovAgencyCode(),
					input.getGovAgencyName(), input.getRegistrationState(), input.getRegistrationClass(),
					input.isSubmitting(), serviceContext);
			
			result = RegistrationUtils.mappingToRegistrationDetailModel(registration);
			
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity(result).build();
	}

	@Override
	public Response getDetail(Long id) {
		Registration detail = null;
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			detail = action.getDetail(id);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity(detail).build();
	}

	@Override
	public Response update(RegistrationInputModel input, Long id) {
		RegistrationDetailModel result = null;
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			Registration model = new RegistrationImpl();
			Date now = new Date();
			model.setRegistrationId(id);
			
			model.setModifiedDate(now);
			
			model.setApplicantName(input.getApplicantName());
			model.setApplicantIdType(input.getApplicantIdType());
			model.setApplicantIdNo(input.getApplicantIdNo());
			model.setAddress(input.getAddress());
			model.setCityCode(input.getCityCode());
			model.setCityName(input.getCityName());
			model.setDistrictCode(input.getDistrictCode());
			model.setDistrictName(input.getDistrictName());
			model.setWardCode(input.getWardCode());
			model.setWardName(input.getWardName());
			model.setContactName(input.getContactName());
			model.setContactTelNo(input.getContactTelNo());
			model.setContactEmail(input.getContactEmail());
			model.setRegistrationClass(input.getRegistrationClass());
			model.setRegistrationState(input.getRegistrationState());
			model.setSubmitting(input.isSubmitting());
			
			Registration registration = action.update(model);
			
			result = RegistrationUtils.mappingToRegistrationDetailModel(registration);
			
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity(result).build();
	}

	@Override
	public Response delete(Long id) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			action.delete(id);
		} catch (Exception e) {
			_log.error(e);
		}
		return Response.status(200).entity("Success").build();
	}

}
