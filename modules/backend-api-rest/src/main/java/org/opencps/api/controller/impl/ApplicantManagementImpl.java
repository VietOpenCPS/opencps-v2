package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ApplicantManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ApplicantUtils;
import org.opencps.api.usermgt.model.ApplicantInputModel;
import org.opencps.api.usermgt.model.ApplicantInputUpdateModel;
import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.ApplicantResultsModel;
import org.opencps.api.usermgt.model.ApplicantSearchModel;
import org.opencps.api.usermgt.model.ProfileInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ApplicantManagementImpl implements ApplicantManagement {

	@Override
	public Response register(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, ApplicantInputModel input) {

		ApplicantActions actions = new ApplicantActionsImpl();

		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			
			String cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
			String districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
			String wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());
			
			Applicant applicant = actions.register(serviceContext, groupId, input.getApplicantName(), input.getApplicantIdType(),
					input.getApplicantIdNo(), input.getApplicantIdDate(), input.getContactEmail(), input.getAddress(),
					input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getPassword());

			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {


			ErrorMsg error = new ErrorMsg();
			_log.error(e);


			error.setMessage("Register unsuccessfully");
			error.setCode(500);
			error.setDescription(e.getMessage());

			

			return Response.status(500).entity(error).build();
		}

	}
	
	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

			return it.getItemName();

		} else {
			return StringPool.BLANK;
		}

	}
	
	private static String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";

	Log _log = LogFactoryUtil.getLog(ApplicantManagementImpl.class);

	@Override
	public Response getApplicants(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ApplicantSearchModel query) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantResultsModel results = new ApplicantResultsModel();
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put("type", query.getType());
			params.put("lock", query.getLock());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getApplicants(serviceContext, serviceContext.getUserId(),
					serviceContext.getCompanyId(), groupId, params, sorts, query.getStart(), query.getEnd(),
					serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData().addAll(ApplicantUtils.mappingToApplicantResults((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

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
	public Response getApplicantDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.getApplicantDetail(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response updateApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ApplicantInputUpdateModel input) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.updateApplicant(serviceContext, id, input.getApplicantName(), input.getAddress(), input.getCityCode(),
						input.getCityName(), input.getDistrictCode(), input.getDistrictName(), input.getWardCode(),
						input.getWardName(), input.getContactName(), input.getContactTelNo(), input.getContactEmail());

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response deleteApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			}

			if (isAllowed) {
				applicant = actions.removeApplicant(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response getApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.getApplicantDetail (serviceContext, id);

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());
				
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response addApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProfileInputModel input) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.updateProfile(serviceContext, id, input.getValue());

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());

				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error.");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response updateApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String key, ProfileInputModel input) {
		// TODO Auto-generated method stub
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				
				Applicant applicantUpdated = ApplicantLocalServiceUtil.getApplicant(id);
				
				JSONObject profile = JSONFactoryUtil.createJSONObject(applicantUpdated.getProfile());
				
				profile.put(key, input.getValue());
				
				actions.updateProfile(serviceContext, id, profile.toString());

				JSONObject result = JSONFactoryUtil.createJSONObject();

				result.put("key", key);
				result.put("value", input.getValue());

				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}

	@Override
	public Response lockApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		ApplicantModel results = new ApplicantModel();

		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.lockApplicant(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();

			} else {
				throw new UnauthorizationException();
			}

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}

	}

	@Override
	public Response activateApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		
		long applicantId = 0;
		
		try {
			ApplicantLocalServiceUtil.getApplicant(id);
			
			applicantId = id;
			
		} catch (Exception e) {
			try {
				Applicant applc = ApplicantLocalServiceUtil.fetchByMappingID(id);
				
				if (Validator.isNotNull(applc)) {
					applicantId = applc.getApplicantId();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}

		Applicant applicant = null;
		try {
			
			

			applicant = actions.activationApplicant(serviceContext, applicantId, code);

			results = ApplicantUtils.mappingToApplicantModel(applicant);
			
			JSONObject resultObj = JSONFactoryUtil.createJSONObject();
			
			resultObj.put("email", applicant.getContactEmail());
			resultObj.put("token", applicant.getTmpPass());

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(resultObj)).build();

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

					if (e instanceof NoSuchUserException) {
						error.setMessage("Not Found");
						error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
						error.setDescription("Not Found");

						return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

					} else {
						error.setMessage("Internal Server Error");
						error.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}

				}
			}
		}
	}
	


}
