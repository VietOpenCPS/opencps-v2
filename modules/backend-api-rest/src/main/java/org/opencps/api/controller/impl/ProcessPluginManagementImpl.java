package org.opencps.api.controller.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ProcessPluginManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.ProcessPlugin;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

public class ProcessPluginManagementImpl implements ProcessPluginManagement {

	@Override
	public Response getPlugins(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);

					String stepCode = dossierAction.getStepCode();

					List<ProcessPlugin> plugins = ProcessPluginLocalServiceUtil
							.getProcessPlugins(dossierAction.getServiceProcessId(), stepCode);

					JSONObject results = JSONFactoryUtil.createJSONObject();

					int total = plugins.size();

					results.put("total", total);

					JSONArray dataArr = JSONFactoryUtil.createJSONArray();

					for (ProcessPlugin plugin : plugins) {
						JSONObject elm = JSONFactoryUtil.createJSONObject();

						elm.put("processPluginId", plugin.getPrimaryKey());
						elm.put("pluginName", plugin.getPluginName());

						dataArr.put(elm);
					}

					results.put("data", dataArr);

					return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);

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
	public Response getFormData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, long pluginid) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					String formData = plugin.getSampleData();

					if (formData.startsWith("#")) {
						return Response.status(200).entity(formData).build();

					} else {

						JSONObject result = JSONFactoryUtil.createJSONObject(plugin.getSampleData());

						return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);

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
			User user, ServiceContext serviceContext, String id, long pluginid) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					String formData = plugin.getPluginForm();

					if (formData.startsWith("#")) {
						return Response.status(200).entity(JSONFactoryUtil.looseSerialize(formData)).build();

					} else {

						JSONObject result = JSONFactoryUtil.createJSONObject(plugin.getPluginForm());

						return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);

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

	private Dossier getDossier(String id, long groupId) {
		Dossier dossier = null;

		long dossierId = GetterUtil.getLong(id);

		try {
			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.getDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
		} catch (Exception e) {
			_log.info("Cant get dossier_" + id);
		}

		return dossier;
	}

	@Override
	public Response getPreview(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, long pluginid) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					// String formReport = plugin.getPluginForm();

					String formCode = plugin.getSampleData();

					boolean autoRun = plugin.getAutoRun();

					String formData = StringPool.BLANK;
					String formReport = StringPool.BLANK;

					if (formCode.startsWith("#")) {
						formData = _getFormData(groupId, formCode, dossier.getDossierId(), autoRun,
								dossier.getDossierTemplateNo());

						formReport = _getFormScript(formCode, dossier.getDossierId());
					}

					Message message = new Message();

					message.put("formReport", formReport);

					message.put("formData", formData);
					
					_log.info("formREPORT"+ formReport);
					_log.info("formDATA"+ formData);

					message.setResponseId(String.valueOf(dossier.getPrimaryKeyObj()));
					message.setResponseDestinationName("jasper/engine/preview/callback");

					try {
						String previewResponse = (String) MessageBusUtil
								.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

						JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(previewResponse)) {
							// jsonObject =
							// JSONFactoryUtil.createJSONObject(previewResponse);
						}

						String fileDes = jsonObject.getString("fileDes");

						File file = new File(previewResponse);

						ResponseBuilder responseBuilder = Response.ok((Object) file);

						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + file.getName() + "\"");
						responseBuilder.header("Content-Type", "application/pdf");

						return responseBuilder.build();

					} catch (MessageBusException e) {
						throw new Exception("Preview rendering not avariable");
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);

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

	private String _getFormData(long groupId, String fileTemplateNo, long dossierId, boolean autoRun,
			String dossierTemplateNo) {

		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		ServiceContext serviceContext = new ServiceContext();

		try {
			// Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
					fileTemplateNo, false, new DossierFileComparator(false, "createDate", Date.class));

			DossierFileActions actions = new DossierFileActionsImpl();

			if (Validator.isNull(dossierFile)) {
				DossierPart dossierPart = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId, fileTemplateNo);

				formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossierId, serviceContext);

				if (autoRun) {
					// create DossierFile


					dossierFile = actions.addDossierFile(groupId, dossierId, PortalUUIDUtil.generate(), dossierTemplateNo,
							dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L,
							null, StringPool.BLANK, String.valueOf(false), serviceContext);
					
					actions.updateDossierFileFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData, serviceContext);

				}

			} else {
				formData = dossierFile.getFormData();
				
				actions.updateDossierFileFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData, serviceContext);

			}

		} catch (Exception e) {
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

		return formData;
	}

	private String _getFormScript(String fileTemplateNo, long dossierId) {
		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		try {

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(dossier.getGroupId(), fileTemplateNo);


			formData = part.getFormReport();


		} catch (Exception e) {
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

		return formData;
	}
	
	private String _getFormHtml(String fileTemplateNo, long dossierId) {
		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		try {

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(dossier.getGroupId(), fileTemplateNo);


			formData = part.getFormScript();


		} catch (Exception e) {
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

		return formData;
	}


	Log _log = LogFactoryUtil.getLog(ProcessPluginManagementImpl.class);

	@Override
	public Response getPreviewHtml(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, long pluginid) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					// String formReport = plugin.getPluginForm();

					String formCode = plugin.getSampleData();

					boolean autoRun = plugin.getAutoRun();

					String formData = StringPool.BLANK;
					String formReport = StringPool.BLANK;

					if (formCode.startsWith("#")) {
						formData = _getFormData(groupId, formCode, dossier.getDossierId(), autoRun,
								dossier.getDossierTemplateNo());

						formReport = _getFormHtml(formCode, dossier.getDossierId());
					}

					JSONObject result = JSONFactoryUtil.createJSONObject();
					
					result.put("formReport", formReport);
					result.put("formData", formData);
					
					return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);

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
