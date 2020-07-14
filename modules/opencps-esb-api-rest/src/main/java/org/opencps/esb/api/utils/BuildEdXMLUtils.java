package org.opencps.esb.api.utils;

import com.google.common.collect.Lists;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.agency.model.GetAgencyRequest;
import com.inet.api.iws.services.agency.model.GetAgencyResult;
import com.inet.xml.base.Content;
import com.inet.xml.base.attachment.Attachment;
import com.inet.xml.base.header.Bussiness;
import com.inet.xml.base.header.BussinessDocumentInfo;
import com.inet.xml.base.header.Header;
import com.inet.xml.base.header.Organization;
import com.inet.xml.base.header.SignReference;
import com.inet.xml.base.header.SignerInfo;
import com.inet.xml.base.header.TraceHeaderList;
import com.inet.xml.base.util.DateUtils;
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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemMappingLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.esb.api.service.CollaborationServices;

public class BuildEdXMLUtils {

	private static final Log _log = LogFactoryUtil.getLog(BuildEdXMLUtils.class);

	public static Content buildEdXMLSender(Dossier dossier, String dossierNo, long groupId) {
		Content content = null;
		try {
			Ed ed = new Ed();
			//String govAgencyCode = dossier.getGovAgencyCode();
			//String govAgencyLT = Validator.isNotNull(dossier.getDelegateIdNo()) ? dossier.getDelegateIdNo() : dossier.getGovAgencyCode();
			//System.out.println("govAgencyCode: "+govAgencyCode);
			//System.out.println("govAgencyLT: "+govAgencyLT);
			String govAgencyName = dossier.getGovAgencyName();
			if (Validator.isNull(dossierNo)) {
				dossierNo = dossier.getDossierNo();
			}
			//DictItem
			//DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY",
			//		groupId);
			//DictItemMapping itemMappingFrom = null;
			//DictItemMapping itemMappingTo = null;
//			if (collection != null) {
//				//itemMappingFrom = DictItemMappingLocalServiceUtil.fetchByF_GID_IC_CID(groupId, govAgencyLT, collection.getDictCollectionId());
//				itemMappingTo = DictItemMappingLocalServiceUtil.fetchByF_GID_IC_CID(groupId, govAgencyCode, collection.getDictCollectionId());
//			}

			//System.out.println("itemLT: "+JSONFactoryUtil.looseSerialize(itemMappingFrom));
			//System.out.println("itemVPT: "+JSONFactoryUtil.looseSerialize(itemMappingTo));

			// Get info Organization from
			AgencyServiceAsync agencyService = CollaborationServices.getAgencyService();
			GetAgencyResult agencyResult = agencyService.getAgency(new GetAgencyRequest());
			System.out.println("Agency result [" + agencyResult + "]");
			Organization from = new Organization(agencyResult.getAgency().getCode(), agencyResult.getAgency().getName(),
						agencyResult.getAgency().getName(), "", agencyResult.getAgency().getMail(), "", "", "");

			Organization to1 = new Organization("000.00.01.H20", "Ủy ban nhân dân tỉnh Đồng Tháp",
					"Ủy ban nhân dân tỉnh Đồng Tháp", "Ủy ban nhân dân tỉnh Đồng Tháp", "",
					"", "", "");

			List<Organization> toes = Lists.newArrayList(to1);
			Code code = new Code(dossierNo, "Hồ sơ liên thông");
			PromulgationInfo promulgationInfo = new PromulgationInfo(govAgencyName, dossier.getCreateDate());
			DocumentType docType = new DocumentType(1, "Cong van");
			//SignerInfo signerInfo = new SignerInfo("TL. Chủ tịch", "Chánh văn phòng UBND thành phố", "Nguyen Thanh");
			//OtherInfo otherInfo = new OtherInfo(0, "", "", 1, 1, "", false, dossier.getDossierNo());

			MessageHeader headerEd = new MessageHeader(from, toes, code, promulgationInfo, docType,
					dossier.getServiceCode() + StringPool.FORWARD_SLASH + dossier.getApplicantName()
							+ StringPool.FORWARD_SLASH + dossierNo,
					Validator.isNotNull(dossier.getDossierName()) ? dossier.getDossierName() : dossier.getServiceName(),
					null, dossier.getDueDate(), null, null);
			headerEd.setApplicationType("MCDT_DT");
			headerEd.addToPlace("Cac bo va co quan ngang bo");
			headerEd.addToPlace("Uy ban nhan dan cac tinh, TP");
			// header.setResponseFor(new ResponseFor("00.99.H29", "33/2015/QĐ-UBND", new
			// Date()));
			headerEd.setSteeringType(1);
			OtherInfo otherInfo = new OtherInfo();
			otherInfo.setPriority(0);
			otherInfo.setReferenceCodes(dossierNo);
			headerEd.setOtherInfo(otherInfo);
			TraceHeaderList trList = new TraceHeaderList();

			Bussiness bussiness = new Bussiness();
			bussiness.setPaper(1);
			//bussiness.setStaffInfo(new StaffInfo("IT", "IT01", "Nguyen Van A", "a.nguyen"));
			bussiness.setBussinessDocReason(dossier.getDossierName());
//			bussiness.setBussinessDocReason("Lý do cập nhật");
			bussiness.setDocumentId(dossierNo);
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

			SignReference signReference = new SignReference("", "http://www.w3.org/2000/09/xmldsig#sha1",
					"FwgIqsSYJshUS2+wlOM61L+q7Aw=");
			signReference.addToTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
			signReference.addToTransform("http://www.w3.org/TR/xml-exc-c14n#");
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
			content = EdXmlBuilder.build(ed, "/opt/mcdt_dt/data/");
		} catch (Exception e) {
			_log.info(e);
		}

		return content;
	}

	  public static Content getContent() throws Exception{
		    Ed ed = new Ed();
		    Organization from = new Organization(
		        "000.00.14.H20",
		        "Sở Thông tin và Truyền thông - tỉnh Đồng Tháp",
		        "Sở Thông tin và Truyền thông - tỉnh Đồng Tháp",
		        "Sở Thông tin và Truyền thông - tỉnh Đồng Tháp", "info@hochiminh.gov.vn", "0909888888", "055851111", "www.hochiminh.gov.vn"
		    );

		    Organization to1 = new Organization(
		        "000.00.00.H20",
		        "Uỷ ban nhân dân Tỉnh Đồng tháp",
		        "Uỷ ban nhân dân Tỉnh Đồng tháp",
		        "Tỉnh Đồng tháp", "motcuadichvucong@gmail.com", "024.668.22443", "024.668.22443", "https://motcua.dongthap.gov.vn"
		    );


		    List<Organization> toes = Lists.newArrayList(to1);
		    //List<Organization> toes = Lists.newArrayList(org4,org5,org6,org7,org8,org9,org10, org11, org12,org13);
		    Code code = new Code("000.00.14.H20-200703-0002", "Hồ sơ liên thông");
		    PromulgationInfo promulgationInfo = new PromulgationInfo("Tỉnh Đồng Tháp", DateUtils.parse("2020/06/01"));
		    DocumentType docType = new DocumentType(1, "Cong van");
		    SignerInfo signerInfo = new SignerInfo("TL. Chủ tịch", "Chánh văn phòng UBND thành phố", "Nguyen Thanh");
		    
		    MessageHeader header = new MessageHeader(
		      from, toes, code, promulgationInfo, docType,
		           "STTTT_XBI05/NGUYỄN THỊ DIỄM MI/000.00.14.H20-200703-0002", "Cấp giấy phép xuất bản tài liệu không kinh doanh",
		           null, new Date(), null, null);
		    header.setApplicationType("MCDT_DT");
		    //header.setDocumentId("VPCP-00021");
		    header.addToPlace("Cac bo va co quan ngang bo");
		    header.addToPlace("Uy ban nhan dan cac tinh, TP");
		    //header.setResponseFor(new ResponseFor("00.99.H29", "33/2015/QĐ-UBND", new Date()));
		    header.setSteeringType(1);
		    OtherInfo otherInfo = new OtherInfo();
		    otherInfo.setPriority(0);
		    otherInfo.setReferenceCodes("000.00.14.H20-200703-0002");
		    header.setOtherInfo(otherInfo);
		    TraceHeaderList trList = new TraceHeaderList();

		    Bussiness bussiness = new Bussiness();
		    bussiness.setPaper(1);
//		    bussiness.setStaffInfo(new StaffInfo("IT","IT01","Nguyen Van A","a.nguyen"));
//		    bussiness.setBussinessDocReason("Lý do cập nhật");
//		    bussiness.setDocumentId("000.00.01.H08,2018,10");
//		    BussinessDocumentInfo bussinessDocumentInfo = new BussinessDocumentInfo();
//		    bussinessDocumentInfo.setDocumentReceiver(1);
//		    bussinessDocumentInfo.setDocumentInfo(1);
//		    bussinessDocumentInfo.setReceiverList(new ReceiverList().adReceiver(new com.inet.xml.base.header.Receiver(1, "000.01.01.H08")).adReceiver(new Receiver(0, "000.02.01.H08")));
//		    bussiness.setBussinessDocumentInfo(bussinessDocumentInfo);
//		    bussiness.setBussinessDocType(1);
//		    ReplacementInfoList replacementInfoList = new ReplacementInfoList();
//		    replacementInfoList.addReplacementInfo(new ReplacementInfo("000.00.01.H01,2018,10", new OrganIdList().addOrganId("000.01.01.H01").addOrganId("000.02.01.H01")));
//		    bussiness.setReplacementInfoList(replacementInfoList);
		  //bussiness.setStaffInfo(new StaffInfo("IT", "IT01", "Nguyen Van A", "a.nguyen"));
			bussiness.setBussinessDocReason("Lý do cập nhật");
			bussiness.setDocumentId("000.00.14.H20-200703-0002");
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

		    SignReference
		      signReference = new SignReference("","http://www.w3.org/2000/09/xmldsig#sha1", "FwgIqsSYJshUS2+wlOM61L+q7Aw=");
		    signReference.addToTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
		    signReference.addToTransform("http://www.w3.org/TR/xml-exc-c14n#");
		    
		   // signedInfo.addReference(signReference);
		   // signedInfo.addReference(new SignReference("208","http://www.w3.org/2000/09/xmldsig#sha1", "d84Dd9770Rt3sfStVMpA6l7yhJA="));
		   // signedInfo.addReference(new SignReference("209","http://www.w3.org/2000/09/xmldsig#sha1", "d84Dd9770Rt3sfStVMpA6l7yhJA="));
		    
		    //KeyInfo keyInfo = new KeyInfo(new X509Data("CN=user05", "MIIFqTCCBJG"));

		    //create signature from 3 parameter
		    //Signature signature = new Signature(signedInfo, "FXM4QWgcX3Eb0fdB+p50Kh", keyInfo);
		    //set the header
		    ed.setHeader(new Header(header, trList, null));
		    
		    // Attach file

		   //ed.addAttachment(new Attachment("200","esb_slide_v1.pptx","esb_slide_v1.pptx", new File("/Users/GIAHUY/Downloads/b8331c849ade618038cf.jpg")));
		   //ed.addAttachment(new Attachment("210","3.DTA2015_TTr THAM DINH DU TOAN CT KHDT 2016.doc","3.DTA2015_TTr THAM DINH DU TOAN CT KHDT 2016.doc", new File("/home/thoangtd/Desktop/delete/tl/3.DTA2015_TTr THAM DINH DU TOAN CT KHDT 2016.doc")));
		   // ed.addAttachment(new Attachment("210", "tin dinh kem.pdf", "tin dinh kem.pdf", new File("/home/thoangtd/Desktop/delete/van ban phap luat/851cc438-75be-4698-93d5-b667cf0a835c.pdf")));
		    //ed.addAttachment(new Attachment("209","user-zip", new File("/home/thoangtd/Desktop/ESB/user-zip.zip")));
		    
		    return EdXmlBuilder.build(ed, "/opt/");
		  }
}
