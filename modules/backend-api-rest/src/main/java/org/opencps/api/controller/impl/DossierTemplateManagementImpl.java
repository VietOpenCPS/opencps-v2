package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierTemplateManagement;
import org.opencps.api.controller.exception.ErrorMsg;
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
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.action.DossierTemplateActions;
import org.opencps.dossiermgt.action.impl.DossierTemplateActionsImpl;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierTemplateManagementImpl implements DossierTemplateManagement {

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierTemplate dossierTemplate = actions.updateDossierTemplate(groupId, 0l, input.getTemplateName(),
					input.getTemplateNo(), input.getDescription(), serviceContext);

			DossierTemplateInputModel result = DossierTemplateUtils.mappingForTemplatePOST(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	@Override
	public Response getDossierTemplateDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierTemplateDetailModel result = new DossierTemplateDetailModel();

		try {

			long dossierTemplateId = GetterUtil.getLong(id);

			DossierTemplate dossierTemplate = null;
			try {
				dossierTemplate = actions.getDossierTemplate(dossierTemplateId);
			} catch (Exception e) {
				dossierTemplate = actions.getDossierTemplate(groupId, id);
			}

			if (Validator.isNull(dossierTemplate)) {
				throw new NotFoundException("NotFoundException");
			}

			result = DossierTemplateUtils.mappingForTemplateGetDetail(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierTemplate dossierTemplate = actions.updateDossierTemplate(groupId, id, input.getTemplateName(),
					input.getTemplateNo(), input.getDescription(), serviceContext);

			DossierTemplateDetailModel result = DossierTemplateUtils.mappingForTemplateGetDetail(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierTemplate dossierTemplate = actions.removeDossierTemplate(id);

			DossierTemplateInputModel result = DossierTemplateUtils.mappingForTemplatePOST(dossierTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

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

			String templateNo = StringPool.BLANK;

			if (id != 0) {
				DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(id);

				if (Validator.isNotNull(dossierTemplate))
					templateNo = dossierTemplate.getTemplateNo();
			}

			String partType = query.getPartType();
			String multiple = query.getMultiple();
			String required = query.getRequired();
			String eSign = query.getESign();

			if (Validator.isNotNull(templateNo)) {
				params.put(DossierPartTerm.TEMPLATE_NO, templateNo);
			}

			if (Validator.isNotNull(partType)) {
				params.put(DossierPartTerm.PART_TYPE, partType);
			}

			if (Validator.isNotNull(eSign)) {
				params.put(DossierPartTerm.TEMPLATE_NO, templateNo);
			}

			if (Validator.isNotNull(multiple)) {
				params.put(DossierPartTerm.ESIGN, eSign);
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPartInputModel result = new DossierPartInputModel();

			DossierPart dossierPart = actions.updateDossierPart(groupId, 0l, dossierTemplate.getTemplateNo(),
					query.getPartNo(), query.getPartName(), query.getPartTip(), query.getPartType(),
					GetterUtil.getBoolean(query.getMultiple()), StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					GetterUtil.getBoolean(query.getRequired()), query.getFileTemplateNo(),
					GetterUtil.getBoolean(query.getEsign()), serviceContext);

			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierPartInputModel result = new DossierPartInputModel();

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPart partUpdate = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId,
					dossierTemplate.getTemplateNo(), partNo);

			DossierPart dossierPart = actions.updateDossierPart(groupId, partUpdate.getPrimaryKey(),
					dossierTemplate.getTemplateNo(), query.getPartNo(), query.getPartName(), query.getPartTip(),
					query.getPartType(), GetterUtil.getBoolean(query.getMultiple()), StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, GetterUtil.getBoolean(query.getRequired()), query.getFileTemplateNo(),
					GetterUtil.getBoolean(query.getEsign()), serviceContext);

			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			DossierPartInputModel result = new DossierPartInputModel();

			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(id);

			DossierPart partRemove = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId,
					dossierTemplate.getTemplateNo(), partNo);

			DossierPart dossierPart = actions.removeDossierPart(partRemove.getPrimaryKey());

			result = DossierTemplateUtils.mappingForPartPOST(dossierPart);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	@Override
	public Response getFormScript(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String partNo) {

		DossierTemplateActions actions = new DossierTemplateActionsImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DossierPartContentInputUpdateModel result = new DossierPartContentInputUpdateModel();

		try {

			String content = actions.getFormScript(groupId, id, partNo);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String content = actions.updateFormScript(groupId, id, partNo, input.getValue(), serviceContext);

			HtmlUtil.escape(content);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String content = actions.updateFormReport(groupId, id, partNo, input.getValue(), serviceContext);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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

			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String content = actions.updateSample(groupId, id, partNo, input.getValue(), serviceContext);

			result.setValue(content);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {

				error.setMessage("Internal Server Error");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

			}
		}
	}

}
