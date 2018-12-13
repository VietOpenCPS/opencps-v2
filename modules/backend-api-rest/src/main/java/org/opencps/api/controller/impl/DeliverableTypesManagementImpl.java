package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DeliverableTypesManagement;
import org.opencps.api.controller.util.DeliverableTypesUtils;
import org.opencps.api.deliverabletype.model.DeliverableTypeDetailModel;
import org.opencps.api.deliverabletype.model.DeliverableTypeInputModel;
import org.opencps.api.deliverabletype.model.DeliverableTypesResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DeliverableTypesManagementImpl implements DeliverableTypesManagement {
//	private Log _log = LogFactoryUtil.getLog(DeliverableTypesManagementImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Response getDeliverableTypes(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		// TODO Get All Deliverable Type
		BackendAuth auth = new BackendAuthImpl();
		int start = 0, end = 0;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesResultsModel results = new DeliverableTypesResultsModel();

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			JSONObject deliverableTypeJsonObject = action.getDeliverableTypes(groupId, start, end);

			List<DeliverableType> lstDeliverableType = (List<DeliverableType>) deliverableTypeJsonObject
					.get("lstDeliverableType");

			results.setTotal(deliverableTypeJsonObject.getInt("total"));
			results.getData().addAll(DeliverableTypesUtils.mappingToDeliverableTypesResultsModel(lstDeliverableType));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDeliverableType(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DeliverableTypeInputModel input) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String counter = String.valueOf(input.getCounter());

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.addDeliverableType(groupId, input.getDeliverableName(),
					input.getDeliverableType(), input.getCodePattern(), GetterUtil.getInteger(counter), input.getFormScript(),
					input.getFormReport(), input.getMappingData(), serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDeliverableType(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DeliverableTypeInputModel model,
			long deliverableTypeId) {
		// TODO Update Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String counter = String.valueOf(model.getCounter());

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableType(groupId, deliverableTypeId,
					model.getDeliverableName(), model.getDeliverableType(), model.getCodePattern(), GetterUtil.getInteger(counter),
					model.getFormScript(), model.getFormReport(), model.getMappingData(), serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDeliverabletypes(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Remove Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.removeDeliverableType(groupId, id);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormScriptByDeliverableTypeId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId) {
		// TODO Get FormScript of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableType(deliverableTypeId);

			return Response.status(200).entity(deliverableType.getFormScript()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeFormScript(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String formScript) {
		// TODO Update FormScript of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeFormScript(groupId, deliverableTypeId,
					formScript, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormReportByDeliverableTypeId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId) {
		// TODO Get FormReport of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableType(deliverableTypeId);

			return Response.status(200).entity(deliverableType.getFormReport()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeFormReport(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String formReport) {
		// TODO Update FormReport of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeFormReport(groupId, deliverableTypeId,
					formReport, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getMappingDataByDeliverableTypeId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId) {
		// TODO Get MappingData of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableType(deliverableTypeId);

			return Response.status(200).entity(deliverableType.getMappingData()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeMappingData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String mappingData) {
		// TODO Update FormReport of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeMappingData(groupId, deliverableTypeId,
					mappingData, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDeliverabletypebyId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Get Deliverable Type by Id or typeCode
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableTypebyId(groupId, id);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getGenerateCodeById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableTypebyId(groupId, id);

			String deliverableNumber = StringPool.BLANK;
			if (deliverableType != null) {
				deliverableNumber = DeliverableNumberGenerator.generateDeliverableNumber(groupId, deliverableType.getCompanyId(), deliverableType.getDeliverableTypeId());				
			}
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("deliverableCode", deliverableNumber);
			
			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
