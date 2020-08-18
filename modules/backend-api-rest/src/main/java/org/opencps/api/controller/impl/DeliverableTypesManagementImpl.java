package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.liferay.portal.kernel.util.Validator;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DeliverableTypesManagement;
import org.opencps.api.controller.util.DeliverableTypesUtils;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.deliverabletype.model.DeliverableTypeDetailModel;
import org.opencps.api.deliverabletype.model.DeliverableTypeInputModel;
import org.opencps.api.deliverabletype.model.DeliverableTypesResultsModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DeliverableTypeModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class DeliverableTypesManagementImpl implements DeliverableTypesManagement {
//	private Log _log = LogFactoryUtil.getLog(DeliverableTypesManagementImpl.class);
	Log _log = LogFactoryUtil.getLog(DeliverableTypesManagementImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Response getDeliverableTypes(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		// TODO Get All Deliverable Type
		BackendAuth auth = new BackendAuthImpl();
		int start = 0, end = 0;
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesResultsModel results = new DeliverableTypesResultsModel();

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			JSONObject deliverableTypeJsonObject = action.getDeliverableTypes(groupId, start, end);

			List<DeliverableType> lstDeliverableType = (List<DeliverableType>) deliverableTypeJsonObject
					.get(ConstantUtils.DELIVERABLE_LIST_DELIVERABLE_TYPE);

			results.setTotal(deliverableTypeJsonObject.getInt(ConstantUtils.TOTAL));
			results.getData().addAll(DeliverableTypesUtils.mappingToDeliverableTypesResultsModel(lstDeliverableType));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDeliverableType(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DeliverableTypeInputModel input) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDeliverabletypes(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Remove Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.removeDeliverableType(groupId, id);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(deliverableType.getFormScript()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeFormScript(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String formScript) {
		// TODO Update FormScript of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeFormScript(groupId, deliverableTypeId,
					formScript, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(deliverableType.getFormReport()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeFormReport(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String formReport) {
		// TODO Update FormReport of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeFormReport(groupId, deliverableTypeId,
					formReport, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(deliverableType.getMappingData()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateDeliverableTypeMappingData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long deliverableTypeId, String mappingData) {
		// TODO Update FormReport of Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableTypesActions action = new DeliverableTypesActionsImpl();

			DeliverableType deliverableType = action.updateDeliverableTypeMappingData(groupId, deliverableTypeId,
					mappingData, serviceContext);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDeliverabletypebyId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Get Deliverable Type by Id or typeCode
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getDeliverableTypebyId(groupId, id);

			DeliverableTypeDetailModel result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getGenerateCodeById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
			result.put(DeliverableTerm.DELIVERABLE_CODE, deliverableNumber);
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updatefileTemplateById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, Attachment file, String id) {
		BackendAuth auth = new BackendAuthImpl();
		DeliverableTypeDetailModel result = new DeliverableTypeDetailModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			DeliverableType deliverableType = null;
			long deliverableTypeId = GetterUtil.getLong(id);
			if (deliverableTypeId != 0) {
				deliverableType = DeliverableTypeLocalServiceUtil.fetchDeliverableType(deliverableTypeId);
			}
			_log.info("__file:" + file);
			DataHandler dataHandler =
					(file != null) ? file.getDataHandler() : null;
			if (dataHandler != null && dataHandler.getInputStream() != null) {
				_log.info("__Start add file at:" + new Date());
					try {
						FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
								user.getUserId(), groupId,
								dataHandler.getInputStream(),
								dataHandler.getName(), StringPool.BLANK, 0,
								serviceContext);

						if (fileEntry != null) {
							deliverableType.setFileTemplateId(
									fileEntry.getFileEntryId());
						}
					} catch (Exception e) {
						_log.debug(e);
					}
				if (Validator.isNotNull(deliverableType)) {
					DeliverableTypeLocalServiceUtil.updateDeliverableType(deliverableType);
					result = DeliverableTypesUtils.mappingToDeliverableTypesModel(deliverableType);
				}
			}
			_log.info("__End add file at:" + new Date());
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		}catch (Exception e){
			_log.info("EXCEPTION : " + e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}
}
