package org.opencps.esb.api.utils;

import com.google.common.collect.Lists;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.agency.model.GetAgencyRequest;
import com.inet.api.iws.services.agency.model.GetAgencyResult;
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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.Response;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.esb.api.controller.impl.ESBEgovManagementImpl;
import org.opencps.esb.api.service.CollaborationServices;

public class BuildEdXMLUtils {

	private static final Log _log = LogFactoryUtil.getLog(BuildEdXMLUtils.class);

	public static Content buildEdXMLSender(Dossier dossier, long groupId) {
		Content content = null;
		try {
			Ed ed = new Ed();
			String govAgencyCode = dossier.getGovAgencyCode();
			System.out.println("govAgencyCode: "+govAgencyCode);
			String govAgencyName = dossier.getGovAgencyName();
			
			//DictItem
			DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY",
					groupId);
			DictItem item = null;
			if (collection != null) {
				item = DictItemLocalServiceUtil.fetchByF_dictItemCode(govAgencyCode,
						collection.getDictCollectionId(), groupId);
			}

			System.out.println("item: "+JSONFactoryUtil.looseSerialize(item));
			JSONObject jsonData = Validator.isNotNull(item.getMetaData()) ? JSONFactoryUtil.createJSONObject(item.getMetaData()) : null;
			System.out.println("jsonData: "+JSONFactoryUtil.looseSerialize(jsonData));
			
			// Get info Organization from
			AgencyServiceAsync agencyService = CollaborationServices.getAgencyService();
			GetAgencyResult agencyResult = agencyService.getAgency(new GetAgencyRequest());
			System.out.println("Agency result [" + agencyResult + "]");
			Organization from = new Organization(agencyResult.getAgency().getCode(), agencyResult.getAgency().getName(),
						agencyResult.getAgency().getName(), "", agencyResult.getAgency().getMail(), "", "", "");


//			new Organization(govAgencyFrom, govAgencyName, govAgencyName,
//					jsonData.has("BN_address") ? jsonData.getString("BN_address") : "",
//					jsonData.has("BN_email") ? jsonData.getString("BN_email") : "",
//					jsonData.has("BN_telephone") ? jsonData.getString("BN_telephone") : "", "", "");
			Organization to1 = new Organization("000.00.00.H20", "Ủy ban nhân dân tỉnh Đồng Tháp",
					"Ủy ban nhân dân tỉnh Đồng Tháp", "Ủy ban nhân dân tỉnh Đồng Tháp", "info@hochiminh.gov.vn",
					"0909888888", "055851111", "www.hochiminh.gov.vn");

			List<Organization> toes = Lists.newArrayList(to1);
			Code code = new Code(dossier.getDossierNo(), "Hồ sơ liên thông");
			PromulgationInfo promulgationInfo = new PromulgationInfo(govAgencyName, dossier.getCreateDate());
			DocumentType docType = new DocumentType(1, "Cong van");
			//SignerInfo signerInfo = new SignerInfo("TL. Chủ tịch", "Chánh văn phòng UBND thành phố", "Nguyen Thanh");
			//OtherInfo otherInfo = new OtherInfo(0, "", "", 1, 1, "", false, dossier.getDossierNo());

			MessageHeader headerEd = new MessageHeader(from, toes, code, promulgationInfo, docType,
					dossier.getGovAgencyCode() + StringPool.FORWARD_SLASH + dossier.getApplicantName()
							+ StringPool.FORWARD_SLASH + dossier.getDossierNo(),
					Validator.isNotNull(dossier.getDossierName()) ? dossier.getDossierName() : dossier.getServiceName(),
					null, dossier.getDueDate(), null, null);
			headerEd.setApplicationType("MCDT_DT");
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

			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.findByDID(dossier.getDossierId());
			if (dossierFileList != null && dossierFileList.size() > 0) {
				for (DossierFile dossierFile : dossierFileList) {
					File file = null;
					if (dossierFile.getFileEntryId() > 0) {
						try {
							FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
									dossierFile.getFileEntryId());
							file = DLFileEntryLocalServiceUtil.getFile(
									fileEntry.getFileEntryId(), fileEntry.getVersion(), true);
						} catch (PortalException e) {
							e.printStackTrace();
						}
					}

					if (file != null) {
						ed.addAttachment(new Attachment(String.valueOf(dossierFile.getDossierFileId()),
								dossierFile.getDisplayName(), dossierFile.getDisplayName(), file));
					}
				}
			}
			// Attach file
			//ed.addAttachment(new Attachment("209", "esb_slide_v1.pptx", "esb_slide_v1.pptx",
			//		new File("/Users/thoangtran/Desktop/vinhlong/esb_slide_v1.pptx")));
			// TAO FILE EDXML
			content = EdXmlBuilder.build(ed, "/opt/");
		} catch (Exception e) {
			_log.info(e);
		}

		return content;
	}
}
