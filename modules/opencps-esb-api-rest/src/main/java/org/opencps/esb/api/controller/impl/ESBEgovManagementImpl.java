package org.opencps.esb.api.controller.impl;

import com.google.common.collect.Lists;
import com.inet.api.AuthClientException;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.agency.model.GetAgencyRequest;
import com.inet.api.iws.services.agency.model.GetAgencyResult;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsync;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsyncClient;
import com.inet.api.iws.services.knobstick.model.CheckSendKnobstickStatusRequest;
import com.inet.api.iws.services.knobstick.model.CheckSendKnobstickStatusResult;
import com.inet.api.iws.services.knobstick.model.DeliverKnobstickRequest;
import com.inet.api.iws.services.knobstick.model.SendKnobstickRequest;
import com.inet.api.iws.services.knobstick.model.SendKnobstickResult;
import com.inet.api.iws.services.slot.SlotServiceAsync;
import com.inet.api.iws.services.slot.SlotServiceAsyncClient;
import com.inet.api.iws.services.slot.model.GetSlotRequest;
import com.inet.api.iws.services.slot.model.GetSlotResult;
import com.inet.xml.base.Content;
import com.inet.xml.base.attachment.Attachment;
import com.inet.xml.base.builder.BuildException;
import com.inet.xml.base.header.Bussiness;
import com.inet.xml.base.header.BussinessDocumentInfo;
import com.inet.xml.base.header.Header;
import com.inet.xml.base.header.Organization;
import com.inet.xml.base.header.TraceHeaderList;
import com.inet.xml.ed.Ed;
import com.inet.xml.ed.builder.EdXmlBuilder;
import com.inet.xml.ed.header.Code;
import com.inet.xml.ed.header.DocumentType;
import com.inet.xml.ed.header.MessageHeader;
import com.inet.xml.ed.header.OtherInfo;
import com.inet.xml.ed.header.PromulgationInfo;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
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
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.esb.api.controller.ESBEgovManagement;
import org.opencps.esb.api.service.ClientConfigurationProvider;
import org.opencps.esb.api.service.CollaborationServices;
import org.opencps.esb.api.service.HoraeCredentialsProvider;


public class ESBEgovManagementImpl implements ESBEgovManagement{

	private static final Log _log = LogFactoryUtil.getLog(ESBEgovManagementImpl.class);
	@Override
	public Response getESBCounter(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = GetterUtil.getLong(id);
		Dossier dossier = null;
		if (dossierId > 0) {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		} else {
			dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
		}
		if (dossier != null) {
			Ed ed = new Ed();
			//String govAgencyCode = dossier.getGovAgencyCode();
			String govAgencyName = dossier.getGovAgencyName();
			
			//DictItem
			DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY",
					groupId);
			DictItem item = null;
			if (collection != null) {
				item = DictItemLocalServiceUtil.fetchByF_dictItemCode(dossier.getGovAgencyCode(),
						collection.getDictCollectionId(), groupId);
			}
			
			if (item == null) {
				return Response.status(500).entity("{error}").build();
			}

			JSONObject jsonData = null;
			try {
				jsonData = Validator.isNotNull(item.getMetaData()) ? JSONFactoryUtil.createJSONObject(item.getMetaData()) : null;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			Organization from = null;
			AgencyServiceAsync agencyService = CollaborationServices.getAgencyService();
			GetAgencyResult agencyResult = agencyService.getAgency(new GetAgencyRequest());
			System.out.println("Agency result [" + agencyResult + "]");
			if (jsonData != null) {
				from = new Organization(agencyResult.getAgency().getCode(), agencyResult.getAgency().getName(),
						agencyResult.getAgency().getName(), "", agencyResult.getAgency().getMail(), "", "", "");
			}


//			new Organization(govAgencyFrom, govAgencyName, govAgencyName,
//					jsonData.has("BN_address") ? jsonData.getString("BN_address") : "",
//					jsonData.has("BN_email") ? jsonData.getString("BN_email") : "",
//					jsonData.has("BN_telephone") ? jsonData.getString("BN_telephone") : "", "", "");
			Organization to1 = new Organization("000.00.46.H29", "Uỷ ban nhân dân thành phố Hồ Chí Minh",
					"Ủy ban nhân dân Quận 1 - TPHCM", "Dia chi TpHCM", "info@hochiminh.gov.vn", "0909888888",
					"055851111", "www.hochiminh.gov.vn");

			List<Organization> toes = Lists.newArrayList(to1);
			Code code = new Code(dossier.getDossierNo(), "TV-TEST");
			PromulgationInfo promulgationInfo = new PromulgationInfo(govAgencyName, dossier.getCreateDate());
			DocumentType docType = new DocumentType(1, "Cong van");
			//SignerInfo signerInfo = new SignerInfo("TL. Chủ tịch", "Chánh văn phòng UBND thành phố", "Nguyen Thanh");
			//OtherInfo otherInfo = new OtherInfo(0, "", "", 1, 1, "", false, dossier.getDossierNo());

			MessageHeader headerEd = new MessageHeader(from, toes, code, promulgationInfo, docType,
					Validator.isNotNull(dossier.getDossierName()) ? dossier.getDossierName() : dossier.getServiceName(),
					"", null, dossier.getDueDate(), null, null);
			headerEd.setDocumentId("VPCP-00021");
			headerEd.addToPlace("Cac bo va co quan ngang bo");
			headerEd.addToPlace("Uy ban nhan dan cac tinh, TP");
			// header.setResponseFor(new ResponseFor("00.99.H29", "33/2015/QĐ-UBND", new
			// Date()));
			headerEd.setSteeringType(0);
			OtherInfo otherInfo = new OtherInfo();
			otherInfo.setPriority(0);
			otherInfo.setReferenceCodes(dossier.getDossierNo());
			headerEd.setOtherInfo(otherInfo);
			TraceHeaderList trList = new TraceHeaderList();

			Bussiness bussiness = new Bussiness();
			bussiness.setPaper(1);
			//bussiness.setStaffInfo(new StaffInfo("IT", "IT01", "Nguyen Van A", "a.nguyen"));
			bussiness.setBussinessDocReason(dossier.getDossierName());
			bussiness.setDocumentId(dossier.getDossierNo());
			BussinessDocumentInfo bussinessDocumentInfo = new BussinessDocumentInfo();
			bussinessDocumentInfo.setDocumentReceiver(0);
			//bussinessDocumentInfo.setDocumentInfo(1);
			//bussinessDocumentInfo.setReceiverList(
			//		new ReceiverList().adReceiver(new com.inet.xml.base.header.Receiver(1, "000.01.01.H08"))
			//				.adReceiver(new Receiver(0, "000.02.01.H08")));
			bussiness.setBussinessDocumentInfo(bussinessDocumentInfo);
			bussiness.setBussinessDocType(0);
			//ReplacementInfoList replacementInfoList = new ReplacementInfoList();
			//replacementInfoList.addReplacementInfo(new ReplacementInfo("000.00.01.H01,2018,10",
			//		new OrganIdList().addOrganId("000.01.01.H01").addOrganId("000.02.01.H01")));
			//bussiness.setReplacementInfoList(replacementInfoList);

			trList.setBussiness(bussiness);

			//SignReference signReference = new SignReference("", "http://www.w3.org/2000/09/xmldsig#sha1",
			//		"FwgIqsSYJshUS2+wlOM61L+q7Aw=");
			//signReference.addToTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
			//signReference.addToTransform("http://www.w3.org/TR/xml-exc-c14n#");

			// signedInfo.addReference(signReference);
			// signedInfo.addReference(new
			// SignReference("208","http://www.w3.org/2000/09/xmldsig#sha1",
			// "d84Dd9770Rt3sfStVMpA6l7yhJA="));
			// signedInfo.addReference(new
			// SignReference("209","http://www.w3.org/2000/09/xmldsig#sha1",
			// "d84Dd9770Rt3sfStVMpA6l7yhJA="));

			//KeyInfo keyInfo = new KeyInfo(new X509Data("CN=user05", "MIIFqTCCBJG"));

			// create signature from 3 parameter
			// Signature signature = new Signature(signedInfo, "FXM4QWgcX3Eb0fdB+p50Kh",
			// keyInfo);
			// set the header
			ed.setHeader(new Header(headerEd, trList, null));

			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.findByDID(dossierId);
			if (dossierFileList != null && dossierFileList.size() > 0) {
				for (DossierFile dossierFile : dossierFileList) {
					File file = null;
					try {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
								dossierFile.getFileEntryId());
						file = DLFileEntryLocalServiceUtil.getFile(
								fileEntry.getFileEntryId(), fileEntry.getVersion(), true);
					} catch (PortalException e) {
						e.printStackTrace();
					}

					ed.addAttachment(new Attachment(String.valueOf(dossierFile.getDossierFileId()),
							dossierFile.getDisplayName(), dossierFile.getDisplayName(), file));
				}
			}
			// Attach file
			//ed.addAttachment(new Attachment("209", "esb_slide_v1.pptx", "esb_slide_v1.pptx",
			//		new File("/Users/thoangtran/Desktop/vinhlong/esb_slide_v1.pptx")));
			// ed.addAttachment(new Attachment("210","3.DTA2015_TTr THAM DINH DU TOAN CT
			// KHDT 2016.doc","3.DTA2015_TTr THAM DINH DU TOAN CT KHDT 2016.doc", new
			// File("/home/thoangtd/Desktop/delete/tl/3.DTA2015_TTr THAM DINH DU TOAN CT
			// KHDT 2016.doc")));
			// ed.addAttachment(new Attachment("210", "tin dinh kem.pdf", "tin dinh
			// kem.pdf", new File("/home/thoangtd/Desktop/delete/van ban phap
			// luat/851cc438-75be-4698-93d5-b667cf0a835c.pdf")));
			// ed.addAttachment(new Attachment("209","user-zip", new
			// File("/home/thoangtd/Desktop/ESB/user-zip.zip")));
			// TAO FILE EDXML
			Content content = null;
			try {
				content = EdXmlBuilder.build(ed, "/opt/");
			} catch (BuildException e) {
				e.printStackTrace();
			}
			//Send message
			HoraeCredentialsProvider credentialsProvider = new HoraeCredentialsProvider();
		    ClientConfigurationProvider clientConfigurationProvider = new ClientConfigurationProvider();
		    // send knobstick to server.
		    KnobstickServiceAsync knobstickService = new KnobstickServiceAsyncClient(credentialsProvider.get(),
		      clientConfigurationProvider.get());
		    // request slot.
		    SlotServiceAsync slotServiceAsync = new SlotServiceAsyncClient(credentialsProvider.get(),
		      clientConfigurationProvider.get());

		    GetSlotResult slotResult = slotServiceAsync.requestSlot(new GetSlotRequest().withType("edoc"));
		    System.out.println("Slot information = [" + slotResult + "]");
		    
		    
		    System.out.println(" Content: " + content.toString());
		    
			try {
				SendKnobstickResult result = knobstickService
						.sendKnobstick(new SendKnobstickRequest().withSlot(slotResult.getSlot().getId())
								.withKey(content.getContent().getName()).withContentSha256(content.getHashCode())
								.withContent(new FileInputStream(content.getContent())));
				// ESB TRA VE THONG TIN
				System.out.println("Result knobstick = [" + result + "]");

				// CHUYEN DU LIEU LIEN THONG DEN DON VI NHAN
				knobstickService
						.deliverKnobstick(new DeliverKnobstickRequest().withKnobstickId(result.getKnobstick().getId()));

				// check send
				CheckSendKnobstickStatusResult result111 = knobstickService.checkSendKnobstickStatus(
						new CheckSendKnobstickStatusRequest().withKnobstickId(result.getKnobstick().getId()));

				
			} catch (AuthClientException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return Response.status(200).entity("SUCCESS").build();
	}
}
