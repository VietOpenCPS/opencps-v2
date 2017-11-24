package org.opencps.api.controller.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ServerConfigManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ServerConfigUtils;
import org.opencps.api.serverconfig.model.ServerConfigDetailModel;
import org.opencps.api.serverconfig.model.ServerConfigInputModel;
import org.opencps.api.serverconfig.model.ServerConfigResultsModel;
import org.opencps.api.serverconfig.model.ServerConfigSearchModel;
import org.opencps.api.serverconfig.model.ServerConfigSingleInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ServerConfigManagementImpl implements ServerConfigManagement {

	@Override
	public Response getServerConfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServerConfigSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			int count = ServerConfigLocalServiceUtil.getServerConfigsCount();

			List<ServerConfig> configs = ServerConfigLocalServiceUtil.getServerConfigs(query.getStart(),
					query.getEnd());

			ServerConfigResultsModel results = new ServerConfigResultsModel();

			results.setTotal(count);
			results.getData().addAll(ServerConfigUtils.mappingTOData(configs));

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
	public Response addServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServerConfigInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, 0l, input.getServerNo(),
					input.getServerName(), input.getProtocol(), StringPool.BLANK, new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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
	public Response getServerConfigDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			ServerConfig config = null;
			long serverId = GetterUtil.getLong(id);

			if (serverId != 0) {
				config = ServerConfigLocalServiceUtil.fetchServerConfig(serverId);

				if (Validator.isNull(config)) {
					config = ServerConfigLocalServiceUtil.getByCode(id);
				}

			} else {
				config = ServerConfigLocalServiceUtil.getByCode(id);

			}

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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
	public Response updateServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServerConfigInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id, input.getServerNo(),
					input.getServerName(), input.getProtocol(), StringPool.BLANK, new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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
	public Response removeServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.deleteServerConfig(id);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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
	public Response getConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.getServerConfig(id);

			String configStr = config.getConfigs();

			ServerConfigSingleInputModel result = new ServerConfigSingleInputModel();

			result.setValue(configStr);

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

	@Override
	public Response addConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, ServerConfigSingleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig oldConfig = ServerConfigLocalServiceUtil.getServerConfig(id);

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id, oldConfig.getServerNo(),
					oldConfig.getServerName(), oldConfig.getProtocol(), input.getValue(), new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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
	public Response updateConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServerConfigSingleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig oldConfig = ServerConfigLocalServiceUtil.getServerConfig(id);

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id, oldConfig.getServerNo(),
					oldConfig.getServerName(), oldConfig.getProtocol(), input.getValue(), new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

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

}
