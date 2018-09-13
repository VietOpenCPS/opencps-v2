package org.opencps.api.controller.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.UserManagement;
import org.opencps.api.controller.util.UserUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.jobpos.model.JobposPermissionResults;
import org.opencps.api.user.model.UserAccountModel;
import org.opencps.api.user.model.UserModel;
import org.opencps.api.user.model.UserProfileModel;
import org.opencps.api.user.model.UserResults;
import org.opencps.api.user.model.UserRolesResults;
import org.opencps.api.user.model.UserSitesResults;
import org.opencps.usermgt.action.JobposInterface;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public class UserManagementImpl implements UserManagement {

	private static final Log _log = LogFactoryUtil.getLog(UserManagementImpl.class);

	@Override
	public Response getUserPhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();

		try {

			File file = actions.getPhoto(id, serviceContext);

			String type = actions.getType(id, serviceContext);

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
					.header("Content-Type", "image/" + type);

			return responseBuilder.build();

		} catch (Exception e) {
			_log.error(e);

			ErrorMsg error = new ErrorMsg();
			error.setMessage("file not found!");
			error.setCode(404);
			error.setDescription("file not found!");
			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response uploadPhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		DataHandler dataHandler = attachment.getDataHandler();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			inputStream = dataHandler.getInputStream();
			
			

			File file = actions.uploadPhoto(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, "USERPHOTO/", "USERPHOTO file upload", serviceContext);

			String type = actions.getType(id, serviceContext);

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
					.header("Content-Type", "image/" + type);

			return responseBuilder.build();
		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response getUserProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserProfileModel result = new UserProfileModel();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Document document = actions.getUserProfile(id, groupId, serviceContext);

			result = UserUtils.mapperUserProfileModel(document);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getSites(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserSitesResults result = new UserSitesResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonData = actions.getSites(id, groupId, serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getUserSitesModel().addAll(UserUtils.mapperUserSitesList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getRoles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserRolesResults result = new UserRolesResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonData = actions.getRoles(id, groupId, serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getUserRolesModel().addAll(UserUtils.mapperUserRolesList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getPreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String result = actions.getPreference(id, groupId, serviceContext);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getPreferenceByKey(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String key) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String result = actions.getPreferenceByKey(id, groupId, key, serviceContext);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addPreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String preferences) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String result = actions.addPreferences(id, groupId, preferences, serviceContext);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response updatePreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String key, String value) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String result = actions.updatePreferences(id, groupId, key, value, serviceContext);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addChangepass(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			_log.info("groupId: "+groupId+ "|company.getCompanyId(): "+company.getCompanyId()+"|id: "+id
					+"oldPass: "+oldPassword+ "|newPassword: "+newPassword);
			int flagNo = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword,
					serviceContext);

			return Response.status(200).entity(String.valueOf(flagNo)).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);

			if (e instanceof DigestException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		}
	}

	@Override
	public Response getPermissions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String full) {
		JobposInterface actions = new JobposActions();
		JobposPermissionResults result = new JobposPermissionResults();
		try {

			JSONObject jsonData = actions.getJobposPermissions();

			result.setTotal(jsonData.getLong("total"));
			result.getJobposPermissionModel()
					.addAll(UserUtils.mapperUsersPermissionsList((String[]) jsonData.get("data"), id, serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getForgot(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String screenname_email) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Document document = actions.getForgot(groupId, company.getCompanyId(), screenname_email, serviceContext);

			UserAccountModel userAccountModel = UserUtils.mapperUserAccountModel(document);

			return Response.status(200).entity(userAccountModel).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getForgotConfirm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String screenname_email, String code) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Document document = actions.getForgotConfirm(groupId, company.getCompanyId(), screenname_email, code,
					serviceContext);

			UserAccountModel userAccountModel = UserUtils.mapperUserAccountModel(document);

			return Response.status(200).entity(userAccountModel).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);

			if (e instanceof DigestException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		}
	}

	@Override
	public Response getUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext) {
		UserInterface actions = new UserActions();
		UserResults result = new UserResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonData = actions.getUsers(groupId, serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getUserModel().addAll(UserUtils.mapperUserList((List<User>) jsonData.get("data"), groupId));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getUserById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserModel userModel = new UserModel();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			User userCustom = actions.getUserById(groupId, company.getCompanyId(), id, serviceContext);

			userModel = UserUtils.mapperUserModel(userCustom, groupId);

			return Response.status(200).entity(userModel).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getCheckpass(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = actions.getCheckpass(groupId, company.getCompanyId(), id, password, serviceContext);

			return Response.status(200).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);

			if (e instanceof DigestException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		}
	}

	@Override
	public Response getUserWorks(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response uploadEsign(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {

		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		DataHandler dataHandler = attachment.getDataHandler();

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {
			// long groupId =
			// GetterUtil.getLong(header.getHeaderString("groupId"));
			
			inputStream = dataHandler.getInputStream();
			BufferedImage image = ImageIO.read(inputStream);
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "png";
			File targetFile = new File(buildFileName);

			ImageIO.write(image, "png", targetFile);
			
			_log.info("Absolute Path buildFileName " + buildFileName);
			
			//FileUtils.copyInputStreamToFile(inputStream, targetFile);
			
			EmployeeLocalServiceUtil.updatePayload(id, groupId, 0, 0, StringPool.BLANK, buildFileName, serviceContext);

			return Response.status(200).entity(buildFileName).build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response uploadEsignCert(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {

//		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		DataHandler dataHandler = attachment.getDataHandler();

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {

			// long groupId =
			// GetterUtil.getLong(header.getHeaderString("groupId"));

			inputStream = dataHandler.getInputStream();
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);

			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "cer";
			File targetFile = new File(buildFileName);

			try (FileOutputStream outStream = new FileOutputStream(targetFile)) {

				int bytesRead = -1;
				byte[] buffer = new byte[4096];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
	
				outStream.close();
				inputStream.close();
			}
			return Response.status(200).entity(buildFileName).build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}

	}

	@Override
	public Response getUserEsign(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

//		UserInterface actions = new UserActions();

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {


			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "png";
			File targetFile = new File(buildFileName);

			ResponseBuilder responseBuilder = Response.ok((Object) targetFile);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + targetFile.getName() + "\"")
					.header("Content-Type", "image/png");

			return responseBuilder.build();
			
			
		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		}

	}

	@Override
	public Response getUserEsignCert(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {


			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "png";
			File targetFile = new File(buildFileName);

			ResponseBuilder responseBuilder = Response.ok((Object) targetFile);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + targetFile.getName() + "\"")
					.header("Content-Type", "application/x-x509-user-cert");

			return responseBuilder.build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		}
	}

	@Override
	public Response addChangepassApplication(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		
		UserInterface actions = new UserActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			_log.info("groupId: "+groupId+ "|company.getCompanyId(): "+company.getCompanyId()+"|id: "+id
					+"oldPass: "+oldPassword+ "|newPassword: "+newPassword);
			boolean flag = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword, 0,
					serviceContext);

			return Response.status(200).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);

			if (e instanceof DigestException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		}
	}

	@Override
	public Response addChangepassEmployee(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		
		UserInterface actions = new UserActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword, 1,
					serviceContext);

			return Response.status(200).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);

			if (e instanceof DigestException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		}
	}

}
