package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.controller.DossierTemplateManagement;
import org.opencps.api.controller.util.DossierTemplateUtils;
import org.opencps.api.dossiertemplate.model.DossierPartContentInputUpdateModel;
import org.opencps.api.dossiertemplate.model.DossierPartInputModel;
import org.opencps.api.dossiertemplate.model.DossierPartResultsModel;
import org.opencps.api.dossiertemplate.model.DossierPartSearchModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateDetailModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateInputModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateResultsModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierTemplateActions;
import org.opencps.dossiermgt.action.impl.DossierTemplateActionsImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierTemplateManagementImpl implements DossierTemplateManagement {

	private static Log _log = LogFactoryUtil.getLog(DossierTemplateManagementImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierTemplates(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierTemplateSearchModel query) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierTemplateResultsModel results = new DossierTemplateResultsModel();

		try {
			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getDossierTemplates(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(DossierTemplateUtils.mappingToDossierTemplateList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossierTemplate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierTemplateInputModel input) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			String templateName = HtmlUtil.escape(input.getTemplateName());
			String templateNo = HtmlUtil.escape(input.getTemplateNo());
			String description = HtmlUtil.escape(input.getDescription());
			String newFormScript = input.getNewFormScript();
			
//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			DossierTemplate dossierTemplate = actions.updateDossierTemplate(groupId, 0l, templateName,
					templateNo, description, newFormScript, serviceContext);

			DossierTemplateInputModel result = DossierTemplateUtils.mappingForTemplatePOST(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getDossierTemplateDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, Request requestCC) {
		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierTemplateDetailModel result = new DossierTemplateDetailModel();

		try {

			long dossierTemplateId = GetterUtil.getLong(id);

			DossierTemplate dossierTemplate = null;
			try {
				dossierTemplate = actions.getDossierTemplate(dossierTemplateId);
			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				dossierTemplate = actions.getDossierTemplate(groupId, id);
			}

			if (Validator.isNull(dossierTemplate)) {
				throw new NotFoundException("NotFoundException");
			}

			result = DossierTemplateUtils.mappingForTemplateGetDetail(dossierTemplate);
			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				builder = Response.status(200);
				CacheControl cc = new CacheControl();
				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
				cc.setPrivate(true);	
				return builder.entity(result).cacheControl(cc).build();
			}
			else {
				return Response.status(200).entity(result).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierTemplateDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, DossierTemplateInputModel input) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			String templateName = HtmlUtil.escape(input.getTemplateName());
			String templateNo = HtmlUtil.escape(input.getTemplateNo());
			String description = HtmlUtil.escape(input.getDescription());
			String newFormScript = input.getNewFormScript();

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			DossierTemplate dossierTemplate = actions.updateDossierTemplate(groupId, id, templateName,
					templateNo, description, newFormScript, serviceContext);

			DossierTemplateDetailModel result = DossierTemplateUtils.mappingForTemplateGetDetail(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeDossierTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		DossierTemplateActions actions = new DossierTemplateActionsImpl();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			DossierTemplate dossierTemplate = actions.removeDossierTemplate(id);

			DossierTemplateInputModel result = DossierTemplateUtils.mappingForTemplatePOST(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierParts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierPartSearchModel query) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierPartResultsModel results = new DossierPartResultsModel();

		try {
			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String templateNo;

			if (id != 0) {
				DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(id);

				if (Validator.isNotNull(dossierTemplate)) {
					templateNo = dossierTemplate.getTemplateNo();
				} else {
					throw new Exception("No DossierTemplate was found");
				}
			} else {
				templateNo = StringPool.BLANK;
			}
			
			String partType = query.getPartType();
			String multiple = query.getMultiple();
			String required = query.getRequired();
			String eSign = query.getESign();

			
			params.put(DossierPartTerm.TEMPLATE_ID, id);
			
			
			if (Validator.isNotNull(templateNo)) {
				params.put(DossierPartTerm.TEMPLATE_NO, templateNo);
			}

			if (Validator.isNotNull(partType)) {
				params.put(DossierPartTerm.PART_TYPE, partType);
			}

			if (Validator.isNotNull(eSign)) {
				params.put(DossierPartTerm.ESIGN, eSign);
			}

			if (Validator.isNotNull(multiple)) {
				params.put(DossierPartTerm.ESIGN, multiple);
			}

			if (Validator.isNotNull(required)) {
				params.put(DossierPartTerm.REQUIRED, required);
			}

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getDossierParts(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(DossierTemplateUtils.mappingToDossierPartList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierParts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierPartInputModel query) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String partNo = HtmlUtil.escape(query.getPartNo());
			String partName = HtmlUtil.escape(query.getPartName());
			String partTip = HtmlUtil.escape(query.getPartTip());
			String multiple = HtmlUtil.escape(String.valueOf(query.getMultiple()));
			String required = HtmlUtil.escape(String.valueOf(query.getRequired()));
			String fileTemplateNo = HtmlUtil.escape(query.getFileTemplateNo());
			String eSign = HtmlUtil.escape(String.valueOf(query.getEsign()));
			
//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPartInputModel result;

/*			DossierPart dossierPart = actions.updateDossierPart(groupId, 0l, dossierTemplate.getTemplateNo(),
					query.getPartNo(), query.getPartName(), query.getPartTip(), query.getPartType(),
					GetterUtil.getBoolean(query.getMultiple()), StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					GetterUtil.getBoolean(query.getRequired()), query.getFileTemplateNo(),
					GetterUtil.getBoolean(query.getEsign()), serviceContext);
*/
			
			DossierPart dossierPart = actions.updateDossierPart(groupId, 0l,
					dossierTemplate.getTemplateNo(), partNo, partName, partTip,
					query.getPartType(), GetterUtil.getBoolean(multiple), StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, GetterUtil.getBoolean(required), fileTemplateNo,
					GetterUtil.getBoolean(eSign), query.getTypeCode(), query.getDeliverableAction(),
					serviceContext);

			if (dossierPart != null) {
				dossierPart.setEForm(GetterUtil.getBoolean(query.geteForm()));
				DossierPartLocalServiceUtil.updateDossierPart(dossierPart);
			}
			
			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierParts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo, DossierPartInputModel query) {
		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}
			String newPartNo = HtmlUtil.escape(query.getPartNo());
			String partName = HtmlUtil.escape(query.getPartName());
			String partTip = HtmlUtil.escape(query.getPartTip());
			String multiple = HtmlUtil.escape(String.valueOf(query.getMultiple()));
			String required = HtmlUtil.escape(String.valueOf(query.getRequired()));
			String fileTemplateNo = HtmlUtil.escape(query.getFileTemplateNo());
			String eSign = HtmlUtil.escape(String.valueOf(query.getEsign()));

			DossierPartInputModel result;

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPart partUpdate = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId,
					dossierTemplate.getTemplateNo(), partNo);

/*			DossierPart dossierPart = actions.updateDossierPart(groupId, partUpdate.getPrimaryKey(),
					dossierTemplate.getTemplateNo(), query.getPartNo(), query.getPartName(), query.getPartTip(),
					query.getPartType(), GetterUtil.getBoolean(query.getMultiple()), StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, GetterUtil.getBoolean(query.getRequired()), query.getFileTemplateNo(),
					GetterUtil.getBoolean(query.getEsign()), serviceContext);
*/			
			DossierPart dossierPart = actions.updateDossierPart(groupId, partUpdate.getPrimaryKey(),
					dossierTemplate.getTemplateNo(), newPartNo, partName, partTip,
					query.getPartType(), GetterUtil.getBoolean(multiple), StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, GetterUtil.getBoolean(required), fileTemplateNo,
					GetterUtil.getBoolean(eSign), query.getTypeCode(), query.getDeliverableAction(),
					serviceContext);

			if (dossierPart != null) {
				dossierPart.setEForm(GetterUtil.getBoolean(query.geteForm()));
				DossierPartLocalServiceUtil.updateDossierPart(dossierPart);
			}

			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossierParts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo) {
		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			DossierPartInputModel result;

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPart partRemove = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId,
					dossierTemplate.getTemplateNo(), partNo);

			DossierPart dossierPart = actions.removeDossierPart(partRemove.getPrimaryKey());

			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getFormScript(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String partNo) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierTempId = GetterUtil.getLong(id);
		_log.info("dossierTempId: "+dossierTempId+"|id: "+id+"|partNo: "+partNo);
		

//		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {

			if (dossierTempId == 0) {
				DossierTemplate dossierTemp = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, id);
				if (dossierTemp != null) {
					dossierTempId = dossierTemp.getDossierTemplateId();
				}
			}
			String content = actions.getFormScript(groupId, dossierTempId, partNo);
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("value", content);

//			result.setValue(content);

			return Response.status(200).entity(content).build();
//			return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(result)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getFormReport(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {

			String content = actions.getFormReport(groupId, id, partNo);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getSampleData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {

			String content = actions.getSample(groupId, id, partNo);

			HtmlUtil.escape(content);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateFormScript(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo,
			DossierPartContentInputUpdateModel input) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			String content = actions.updateFormScript(groupId, id, partNo, input.getValue(), serviceContext);

			HtmlUtil.escape(content);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateFormReport(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo,
			DossierPartContentInputUpdateModel input) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			String content = actions.updateFormReport(groupId, id, partNo, input.getValue(), serviceContext);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateSampleDate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo,
			DossierPartContentInputUpdateModel input) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			String content = actions.updateSample(groupId, id, partNo, input.getValue(), serviceContext);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getDossierPart(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String fileTemplateNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierPart dossierPart = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId, fileTemplateNo);

			String content = dossierPart.getPartName();

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
