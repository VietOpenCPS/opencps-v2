package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ProcessPluginManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.constants.DeliverableTypesTerm;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.ProcessPlugin;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

import backend.auth.api.exception.BusinessExceptionImpl;

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

			if (dossier != null) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);

					String stepCode = dossierAction.getStepCode();
					_log.debug("STEP CODE: " + stepCode);
					_log.debug("SERVICE PROCESS ID: " + dossierAction.getServiceProcessId());
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
			return BusinessExceptionImpl.processException(e);
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

			if (dossier != null) {

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
			return BusinessExceptionImpl.processException(e);
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

			if (dossier != null) {

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
			return BusinessExceptionImpl.processException(e);
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
			_log.error(e);
//			_log.info("Cant get dossier_" + id);
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

			if (dossier != null) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					// String formReport = plugin.getPluginForm();

					String formCode = plugin.getSampleData();

					String pluginForm = plugin.getPluginForm();

					String[] splipPluginForms = StringUtil.split(pluginForm, StringPool.AT);

					boolean original = false;

					if (splipPluginForms.length == 3 && splipPluginForms[2].contentEquals("original")) {
						original = true;
					}

					boolean autoRun = plugin.getAutoRun();

					String formData = StringPool.BLANK;
					String formReport = StringPool.BLANK;

					if (formCode.startsWith("#")) {
						formData = _getFormData(groupId, formCode, dossier.getDossierId(), autoRun,
								dossier.getDossierTemplateNo(), original, serviceContext);

						formReport = _getFormScript(formCode, dossier.getDossierId());
					}

					//_log.info("Form data to preview: " + formData);
					Message message = new Message();

					message.put("formReport", formReport);

					message.put("formData", formData);

					message.setResponseId(String.valueOf(dossier.getPrimaryKeyObj()));
					message.setResponseDestinationName("jasper/engine/preview/callback");

					try {
						String previewResponse = (String) MessageBusUtil
								.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

						// JSONObject jsonObject =
						// JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(previewResponse)) {
							// jsonObject =
							// JSONFactoryUtil.createJSONObject(previewResponse);
						}

						// String fileDes = jsonObject.getString("fileDes");

						File file = new File(previewResponse);

						ResponseBuilder responseBuilder = Response.ok((Object) file);

						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + file.getName() + "\"");
						responseBuilder.header("Content-Type", "application/pdf");

						return responseBuilder.build();

					} catch (MessageBusException e) {
						_log.error(e);
						throw new Exception("Preview rendering not avariable");
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	private String _getFormData(long groupId, String fileTemplateNo, long dossierId, boolean autoRun,
			String dossierTemplateNo, boolean original, ServiceContext context) {

		_log.info("RUN TO GET FORM DATA");

		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		
		try {
			// Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
					fileTemplateNo, false, new DossierFileComparator(false, "createDate", Date.class));

//			DossierPart dossierPart = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId, fileTemplateNo);
			DossierPart dossierPart = DossierPartLocalServiceUtil.getByTempAndFileTempNo(groupId, dossier != null ? dossier.getDossierTemplateNo() : StringPool.BLANK,  fileTemplateNo);
			if (!original) {
				formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossierId, context);
			}
			else {
				formData = dossierFile.getFormData();
			}
			_log.info(formData);
			_log.info("ORIGINAL PLUGIN: " + original);
			if (Validator.isNotNull(dossierPart.getDeliverableType())) {
				DeliverableType dlt = DeliverableTypeLocalServiceUtil.getByCode(groupId, dossierPart.getDeliverableType());
				if (dlt != null) {					
			if (original) {
						String mappingData = dlt.getMappingData();
						JSONObject mappingDataObj = JSONFactoryUtil.createJSONObject(mappingData);
						if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
							String deliverables = mappingDataObj.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
							_log.info("--------DELIVERABLES----------" + deliverables);
				if (Validator.isNotNull(dossierFile)) {
					formData = dossierFile.getFormData();
				}

							JSONObject formDataObj = JSONFactoryUtil.createJSONObject(formData);
							JSONArray deliverableListArr = JSONFactoryUtil.createJSONArray();
														
							if (Validator.isNull(deliverables)) {
								_log.info("Form data plugin: " + formData);
							}
							else {
								if (formDataObj.has(deliverables)) {
									_log.info("----GENERATE MANY DELIVERABLES----:" + formDataObj.toJSONString());
									
									JSONArray deliverablesArr = JSONFactoryUtil.createJSONArray(formDataObj.getString(deliverables));

									for (int i = 0; i < deliverablesArr.length(); i++) {
										JSONObject newFormDataObj = JSONFactoryUtil.createJSONObject();

										Iterator<?> keys = formDataObj.keys();

										while( keys.hasNext() ) {
										    String key = (String)keys.next();
										    if (!key.equals(deliverables)) {
										    	newFormDataObj.put(key, formDataObj.get(key));
										    }
										}							

										JSONObject deliverableObj = deliverablesArr.getJSONObject(i);
										
										keys = deliverableObj.keys();

										while( keys.hasNext() ) {
										    String key = (String)keys.next();
										    newFormDataObj.put(key, deliverableObj.get(key));
										}																	
										
										deliverableListArr.put(newFormDataObj);
									}
								}
								formData = deliverableListArr.toJSONString();
							}							
						}
						else {
							
						}

			} else {
						String mappingData = dlt.getMappingData();
						JSONObject mappingDataObj = JSONFactoryUtil.createJSONObject(mappingData);
						if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
							String deliverables = mappingDataObj.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
							_log.info("--------DELIVERABLES----------" + deliverables);
							
							if (Validator.isNull(deliverables)) {
							}
						}
						
				DossierFileActions actions = new DossierFileActionsImpl();

				if (Validator.isNull(dossierFile)) {

					if (autoRun) {
						// create DossierFile

						dossierFile = actions.addDossierFile(groupId, dossierId, PortalUUIDUtil.generate(),
								dossierTemplateNo, dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(),
								StringPool.BLANK, 0L, null, StringPool.BLANK, String.valueOf(false), context);

						_log.info("UPDATED DOSSIERFILE");

						actions.updateDossierFileFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData,
								context);

					} else {
						// add temp File

					}

				} else {
					// formData = dossierFile.getFormData();

					actions.updateDossierFileFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData,
							context);

				}
					}									
				}
			}

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

		return formData;
	}

	private String _getFormScript(String fileTemplateNo, long dossierId) {
		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		try {

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

//			DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(dossier.getGroupId(), fileTemplateNo);
			DossierPart part = DossierPartLocalServiceUtil.getByTempAndFileTempNo(dossier.getGroupId(), dossier.getDossierTemplateNo(), fileTemplateNo);

			formData = part.getFormReport();

		} catch (Exception e) {
			_log.error(e);
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

		return formData;
	}

	private String _getFormHtml(String fileTemplateNo, long dossierId) {
		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		  try {

		   //Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
		   
		   DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileTemplateNo(dossierId, fileTemplateNo);

		   //DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(dossier.getGroupId(), fileTemplateNo);

		   formData = dossierFile.getFormScript();

		} catch (Exception e) {
			_log.debug(e);
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

			if (dossier != null) {

				long dossierActionId = dossier.getDossierActionId();

				if (dossierActionId != 0) {

					ProcessPlugin plugin = ProcessPluginLocalServiceUtil.getProcessPlugin(pluginid);

					// String formReport = plugin.getPluginForm();

					String formCode = plugin.getSampleData();

					boolean autoRun = plugin.getAutoRun();

					String pluginForm = plugin.getPluginForm();

					String[] splipPluginForms = StringUtil.split(pluginForm, StringPool.AT);

					boolean original = false;

					if (splipPluginForms.length == 3 && splipPluginForms[2].contentEquals("original")) {
						original = true;
					}

					String formData = StringPool.BLANK;
					String formReport = StringPool.BLANK;

					if (formCode.startsWith("#")) {
						formData = _getFormData(groupId, formCode, dossier.getDossierId(), autoRun,
								dossier.getDossierTemplateNo(), original, serviceContext);

						formReport = _getFormHtml(formCode, dossier.getDossierId());
					}

					JSONObject result = JSONFactoryUtil.createJSONObject();

					String fileTemplateNo = StringUtil.replaceFirst(formCode, "#", StringPool.BLANK);

					DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(
							dossier.getDossierId(), fileTemplateNo, false,
							new DossierFileComparator(false, "createDate", Date.class));

					DossierPart dossierPart = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId, fileTemplateNo);

					long dossierFileId = 0;
					String partNo = StringPool.BLANK;

					if (Validator.isNotNull(dossierFile)) {
						dossierFileId = dossierFile.getDossierFileId();
					}
					
					if (Validator.isNotNull(dossierPart)) {
						partNo = dossierPart.getPartNo();
					}
					
					result.put("partNo", partNo);
					result.put("dossierFileId", dossierFileId);
					result.put("formReport", formReport);
					result.put("formData", formData);

					return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result))
							.build();

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
