package backend.postal.api.rest.controller.impl;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.EInvoiceVNPTTerm;
import org.opencps.api.controller.util.ReadNumber;
import org.opencps.api.einvoice.vnpt.model.CustomerModel;
import org.opencps.api.einvoice.vnpt.model.DownloadInvPDFFkeyNoPayResponse;
import org.opencps.api.einvoice.vnpt.model.ImportAndPublishInvResponse;
import org.opencps.api.einvoice.vnpt.model.InvModel;
import org.opencps.api.einvoice.vnpt.model.InvoiceModel;
import org.opencps.api.einvoice.vnpt.model.ListCustomerModel;
import org.opencps.api.einvoice.vnpt.model.ListInvModel;
import org.opencps.api.einvoice.vnpt.model.ListProductModel;
import org.opencps.api.einvoice.vnpt.model.ProductModel;
import org.opencps.api.einvoice.vnpt.model.UpdateCusResponse;
import org.opencps.api.service.util.ConfigProps;
import org.opencps.auth.utils.DLFolderUtil;
import org.opencps.communication.utils.DateTimeUtils;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.postal.api.rest.controller.EInvoiceVNPTManagement;
import io.swagger.annotations.ApiResponse;

public class EInvoiceVNPTManagementImpl implements EInvoiceVNPTManagement{

	private static final Log _log = LogFactoryUtil.getLog(EInvoiceVNPTManagementImpl.class);		

	@Override
	public Response downloadInvPDFFkeyNoPay(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long dossierId)
			throws JAXBException, XMLStreamException, Exception {
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
		
		String result = StringPool.BLANK;
		// Hồ sơ có thanh toán và được cấu hình in biên lai
		if(dossier != null && paymentFile != null && paymentFile.getInvoicePayload().contentEquals("VNPT")) {
			
			if(paymentFile.getInvoiceFileEntryId() > 0 && paymentFile.getEinvoice() != null) {
				// da co bien lai
				_log.info("Ho so da co bien lai dien tu");
				return getEInvoiceFile(paymentFile).build();
				
			}else {
				// Call api phát hành biên lai	
				_log.info("Ho so chua co bien lai dien tu");
				Response importInvResponse = importAndPublishInv(request, header, company, locale, user, serviceContext, dossier, paymentFile);			
				int status = importInvResponse.getStatus();
				if (status != HttpStatus.SC_OK) {
					return Response.status(HttpStatus.SC_NOT_FOUND)
							   .entity("Hồ sơ không có hóa đơn điện tử VNPT")
							   .type(MediaType.APPLICATION_JSON)
							   .build();
				}
				
				//Cấu hình đầu vào cho node của file xml downloadInvPDFFkeyNoPay
				JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject("EINVOICE_VNPT_CONFIG").getJSONObject("downloadInvPDFFkeyNoPay");
				String xmlUsername = schema.getString(EInvoiceVNPTTerm.USER_NAME);
				String xmlPassword = schema.getString(EInvoiceVNPTTerm.PASS_WORD);
				
				//Read file xml 		
				String realPath = PropsUtil.get(ConfigProps.EINV_VNPT_HOME) + "/" ;
				File xmlFile = new File(realPath + EInvoiceVNPTTerm.downloadInvPDFFkeyNoPayFile);
				
				String soapRequest = "";			
				JSONObject importInv = JSONFactoryUtil.createJSONObject(importInvResponse.getEntity().toString());
				String fkey = importInv.getString("Fkey");
				
				if (xmlFile.exists()) {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder;
					
					try {
						
						dBuilder = dbFactory.newDocumentBuilder();
			            Document doc = dBuilder.parse(xmlFile);
			            doc.getDocumentElement().normalize();
			            
			            // Set node value
			            NodeList nodeList = doc.getElementsByTagName(EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY);
			            Element element = null;
			            for (int i =0; i< nodeList.getLength(); i++) {
			            	element = (Element) nodeList.item(i);
			            	Node fkeyNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_FKEY).item(0).getFirstChild();
			            	fkeyNode.setNodeValue(fkey);
			            	Node xmlUserNameNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_UserName).item(0).getFirstChild();
			            	xmlUserNameNode.setNodeValue(xmlUsername);
			            	Node xmlPassWordNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_UserPass).item(0).getFirstChild();
			            	xmlPassWordNode.setNodeValue(xmlPassword);
			            }
			            
			            doc.getDocumentElement().normalize();
			            StringWriter sw = new StringWriter();
			            TransformerFactory tf = TransformerFactory.newInstance();
			            Transformer transformer = tf.newTransformer();
			            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			            transformer.transform(new DOMSource(doc), new StreamResult(sw));
			            soapRequest = removeSpecialChar(sw.toString());		  
			            
			            _log.debug("downloadInvPDFFkeyNoPay : " + soapRequest);

			            HashMap<String, String> properties = new HashMap<String, String>();
			            properties.put("Content-Type","text/xml; charset=utf-8");
			            properties.put("SOAPAction", EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_SOAP_ACTION);
			            
						result = callSoapApi("POST", EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_SOAP_ENDPOINT, 
								properties, soapRequest, EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_SOAP_ACTION);
						
						_log.debug("DownloadInvPDFFkeyNoPayResponse : " + result.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				DownloadInvPDFFkeyNoPayResponse doFkeyNoPayResponse = unMarshalXmlToDownloadInvPDFFkeyNoPayResponse(result);
				String downloadInvPDFFkeyNoPayResult = doFkeyNoPayResponse.getDownloadInvPDFFkeyNoPayResult();
				
				switch (downloadInvPDFFkeyNoPayResult) {
				case "ERR:1":
					return Response.status(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)
								   .entity(EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR1)
								   .type(MediaType.APPLICATION_JSON)
								   .build();
				case "ERR:6":
					return Response.status(HttpStatus.SC_BAD_REQUEST)
							   .entity(EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR6)
							   .type(MediaType.APPLICATION_JSON)
							   .build();
				case "ERR:7":
					return Response.status(HttpStatus.SC_NOT_IMPLEMENTED)
							   .entity(EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR7)
							   .type(MediaType.APPLICATION_JSON)
							   .build();
				default:
					// download bien lai
					Response saveResponse = uploadEInvoicePDF(paymentFile, downloadInvPDFFkeyNoPayResult, groupId, serviceContext, user, request);
					_log.info("3333 :" + saveResponse.getStatus());
					_log.info("4444 :" + JSONFactoryUtil.looseSerialize(paymentFile));
					int tryCount = 0;
					long invoiceFileEntryId = paymentFile.getInvoiceFileEntryId();
					while (Validator.isNull(invoiceFileEntryId) || invoiceFileEntryId == 0) {
						try {
							Thread.sleep(2000);
							uploadEInvoicePDF(paymentFile, downloadInvPDFFkeyNoPayResult, groupId, serviceContext, user, request);
							tryCount++;
							if (tryCount == 5 ) break;
							if (paymentFile.getInvoiceFileEntryId() > 0 ) break;
						} catch (Exception e) {
							e.getMessage();
						}
					}
					if(saveResponse.getStatus() == 200 && paymentFile.getInvoiceFileEntryId() > 0) {
						return getEInvoiceFile(paymentFile).build();
					}
				}				
			}
		}
		return null;
	}

	private Response importAndPublishInv(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Dossier dossier, PaymentFile paymentFile) throws Exception {
		
		String result = "";
		if(dossier != null && paymentFile != null) {
						
			// validation input param
			validRequiredParam(dossier, paymentFile);

			// update customer info from system to third party
			Response jsonObjectResponse = updateCus(dossier, paymentFile);
			int status = jsonObjectResponse.getStatus();
			if (status != HttpStatus.SC_OK) {
				return Response.status(HttpStatus.SC_NOT_FOUND)
						   .entity("Không tìm thấy khách hàng trong hệ thống.")
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			}
			
			JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject("EINVOICE_VNPT_CONFIG").getJSONObject("ImportAndPublishInv");

			String xmlAccount = schema.getString(EInvoiceVNPTTerm.ACCOUNT);
			String xmlAcpass = schema.getString(EInvoiceVNPTTerm.ACPASS);
			String xmlUsername = schema.getString(EInvoiceVNPTTerm.USER_NAME);
			String xmlPassword = schema.getString(EInvoiceVNPTTerm.PASS_WORD);
			String xmlPattern = schema.getString(EInvoiceVNPTTerm.PATTERN);
			String xmlSerial = schema.getString(EInvoiceVNPTTerm.SERIAL);
			String xmlConvert = schema.getString(EInvoiceVNPTTerm.CONVERT);
					
			
			// Set value for ListInvModel
			ProductModel productModel = new ProductModel();
			productModel.setProdUnit(StringPool.BLANK);
			productModel.setProdName(paymentFile.getPaymentFee());
			productModel.setAmount((int)paymentFile.getPaymentAmount());
			List<ProductModel> productModels = new ArrayList<ProductModel>();
			productModels.add(productModel);
			
			ListProductModel listProductModel = new ListProductModel();
			listProductModel.setListProductModels(productModels);
			
			InvoiceModel invoiceModel = new InvoiceModel();
			invoiceModel.setCusCode(dossier.getApplicantIdNo());
			invoiceModel.setBuyer(dossier.getApplicantName());
			invoiceModel.setCusName(dossier.getApplicantName());
			invoiceModel.setCusAddress(dossier.getAddress());
			invoiceModel.setCusPhone(dossier.getContactTelNo());
			invoiceModel.setPaymentMethod(paymentFile.getPaymentMethod());
			invoiceModel.setKindOfService(StringPool.BLANK);
			
			if (dossier.getApplicantIdType().contentEquals("business")) {
				invoiceModel.setCusTaxCode(dossier.getApplicantIdNo());
			}else {
				invoiceModel.setCusTaxCode(StringPool.BLANK);
			}
			
			invoiceModel.setProducts(listProductModel);
			invoiceModel.setTotal((int)paymentFile.getPaymentAmount());
			invoiceModel.setAmount((int)paymentFile.getPaymentAmount());			
			invoiceModel.setAmountInWords(ReadNumber.numberToString(new BigDecimal(paymentFile.getPaymentAmount())));
			invoiceModel.setArisingDate(DateTimeUtils.convertDateToString(new Date(), DateTimeUtils._VN_DATE_FORMAT));			
			invoiceModel.setEmail(dossier.getContactEmail());
			
			InvModel invModel = new InvModel();
			invModel.setInvoice(invoiceModel);
			String fKey = dossier.getApplicantIdNo() + "-" + System.currentTimeMillis();
			invModel.setKey(fKey);
			List<InvModel> listInvoice = new ArrayList<InvModel>();
			listInvoice.add(invModel);			
			
			ListInvModel listInvModel = new ListInvModel();
			listInvModel.setListInvoice(listInvoice);
						
			//Read file xml 			
			String realPath = PropsUtil.get(ConfigProps.EINV_VNPT_HOME) + "/" ;
			File xmlFile = new File(realPath + EInvoiceVNPTTerm.importAndPublishInvFile);
						
			String soapRequest = "";
			String xmlInvData = convertListInvModelToXml(listInvModel);
			
			_log.info("xmlInvData " + xmlInvData);
			
			if (xmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				
				try {
					
					dBuilder = dbFactory.newDocumentBuilder();
		            Document doc = dBuilder.parse(xmlFile);
		            doc.getDocumentElement().normalize();
		            
		            // Change value node xmlInvData
		            NodeList nodeList = doc.getElementsByTagName(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV);
		            Element element = null;
		            for (int i =0; i< nodeList.getLength(); i++) {
		            	element = (Element) nodeList.item(i);
		            	Node xmlInvDataNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_INVDATA).item(0).getFirstChild();
		            	Node xmlAccountNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_ACCOUNT).item(0).getFirstChild();
		            	Node xmlAcpasstNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_ACPASS).item(0).getFirstChild();
		            	Node xmlUsernameNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_USERNAME).item(0).getFirstChild();
		            	Node xmlPasswordNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_PASSWORD).item(0).getFirstChild();
		            	Node xmlPatternNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_PATTERN).item(0).getFirstChild();
		            	Node xmlSerialNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_SERIAL).item(0).getFirstChild();
		            	Node xmlConvertNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_CONVERT).item(0).getFirstChild();

		            	xmlInvDataNode.setNodeValue(xmlInvData);
		            	xmlAccountNode.setNodeValue(xmlAccount);
		            	xmlAcpasstNode.setNodeValue(xmlAcpass);
		            	xmlUsernameNode.setNodeValue(xmlUsername);
		            	xmlPasswordNode.setNodeValue(xmlPassword);
		            	xmlPatternNode.setNodeValue(xmlPattern);
		            	xmlSerialNode.setNodeValue(xmlSerial);
		            	xmlConvertNode.setNodeValue(xmlConvert);

		            }
		            
		            doc.getDocumentElement().normalize();
		            StringWriter sw = new StringWriter();
		            TransformerFactory tf = TransformerFactory.newInstance();
		            Transformer transformer = tf.newTransformer();
		            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		            transformer.transform(new DOMSource(doc), new StreamResult(sw));
		            soapRequest = removeSpecialChar(sw.toString());		            

		            _log.info("ImportAndPublishInv : " + soapRequest);
		            
		            HashMap<String, String> properties = new HashMap<String, String>();
		            properties.put("Content-Type","text/xml; charset=utf-8");
		            properties.put("SOAPAction", EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_SOAP_ACTION);
		            
					result = callSoapApi("POST", EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_SOAP_ENDPOINT, 
							properties, soapRequest, EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_SOAP_ACTION);

					_log.debug("ImportAndPublishInvResponse : " + result.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

			ImportAndPublishInvResponse importAndPublishInvResponse = unMarshalXmlToImportAndPublishInvResponse(result);
			String importAndPublishInvResult = importAndPublishInvResponse.getImportAndPublishInvResult();
			switch (importAndPublishInvResult) {
			case "ERR:1":
				return Response.status(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)
							   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR1)
							   .type(MediaType.APPLICATION_JSON)
							   .build();
			case "ERR:3":
				return Response.status(HttpStatus.SC_BAD_REQUEST)
						   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR3)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			case "ERR:5":
				return Response.status(HttpStatus.SC_NOT_IMPLEMENTED)
						   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR5)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			case "ERR:7":
				return Response.status(HttpStatus.SC_NOT_FOUND)
						   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR7)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			case "ERR:13":
				return Response.status(HttpStatus.SC_BAD_REQUEST)
						   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR13)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			case "ERR:20":
				return Response.status(HttpStatus.SC_BAD_REQUEST)
						   .entity(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_ERR20)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			default:
				
				//update paymentfile einvoice
				JSONObject eInvObj = JSONFactoryUtil.createJSONObject();
				eInvObj.put("Fkey", fKey);
				eInvObj.put("ImportAndPublishInvResult", importAndPublishInvResult);
				paymentFile.setEinvoice(eInvObj.toJSONString());
				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
				
				JSONObject jbResponse = JSONFactoryUtil.createJSONObject();
				jbResponse.put("Fkey", fKey);
				jbResponse.put("ImportAndPublishInvResult", importAndPublishInvResult);
				jbResponse.put("Message", EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES_MESSAGE_SUCCESS);
				return Response.status(HttpStatus.SC_OK)
						   .entity(jbResponse.toJSONString())
						   .type(MediaType.APPLICATION_JSON)
						   .build();
			}
		}
		return null;
	}
	
	private Response updateCus(Dossier dossier, PaymentFile paymentFile) throws JAXBException, XMLStreamException, URISyntaxException, IOException, Exception {
		
		JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject("EINVOICE_VNPT_CONFIG").getJSONObject("UpdateCus");
		
		String xmlUsername = schema.getString(EInvoiceVNPTTerm.USER_NAME);
		String xmlPassword = schema.getString(EInvoiceVNPTTerm.PASS_WORD);
		String xmlConvert = schema.getString(EInvoiceVNPTTerm.CONVERT);

		// Create ListCustomerModel		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setName(dossier.getApplicantName());
		customerModel.setCode(dossier.getApplicantIdNo());
		
		if (dossier.getApplicantIdType().contentEquals("business")) {
			customerModel.setTaxCode(dossier.getApplicantIdNo());
			customerModel.setCusType(1);
		}else {
			customerModel.setTaxCode(StringPool.BLANK);
			customerModel.setCusType(0);
		}
		customerModel.setAddress(dossier.getAddress());		
		customerModel.setBankAccountName(StringPool.BLANK);
		customerModel.setBankName(StringPool.BLANK);
		customerModel.setBankNumber(StringPool.BLANK);
		customerModel.setEmail(dossier.getContactEmail());
		customerModel.setFax(StringPool.BLANK);
		customerModel.setPhone(dossier.getContactTelNo());
		customerModel.setContactPerson(dossier.getApplicantName());
		customerModel.setRepresentPerson(dossier.getApplicantName());
		
		List<CustomerModel> listCustomerModels = new ArrayList<CustomerModel>();
		listCustomerModels.add(customerModel);
		
		ListCustomerModel listCustomerModel = new ListCustomerModel();
		listCustomerModel.setListCustomerModels(listCustomerModels);
		
		//Read file xml 
		String realPath = PropsUtil.get(ConfigProps.EINV_VNPT_HOME) + "/" ;
		File xmlFile = new File(realPath + EInvoiceVNPTTerm.updateCusRequestFile);

		String soapRequest = "";
		String xmlCusData = convertListCustomerModelToXml(listCustomerModel);
		String result = "";

		if (xmlFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				
				dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            
	            // Change value node xmlCusData 
	            NodeList nodeList = doc.getElementsByTagName(EInvoiceVNPTTerm.XML_UPDATE_CUS);
	            Element element = null;
	            for (int i =0; i< nodeList.getLength(); i++) {
	            	element = (Element) nodeList.item(i);
	            	Node xmlCusDataNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_CUSDATA).item(0).getFirstChild();
	            	xmlCusDataNode.setNodeValue(xmlCusData);
	            	Node xmlUserNameNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_USERNAME).item(0).getFirstChild();
	            	xmlUserNameNode.setNodeValue(xmlUsername);
	            	Node xmlPassWordNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_PASS).item(0).getFirstChild();
	            	xmlPassWordNode.setNodeValue(xmlPassword);
	            	Node xmlConvertNode = element.getElementsByTagName(EInvoiceVNPTTerm.XML_CONVERT).item(0).getFirstChild();
	            	xmlConvertNode.setNodeValue(xmlConvert);
	            }
	            
	            doc.getDocumentElement().normalize();
	            StringWriter sw = new StringWriter();
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            transformer.transform(new DOMSource(doc), new StreamResult(sw));
	            
	            soapRequest = removeSpecialChar(sw.toString());
	            _log.info("UpdateCus: " + soapRequest);
	            
	            HashMap<String, String> properties = new HashMap<String, String>();
	            properties.put("Content-Type","text/xml; charset=utf-8");
	            properties.put("SOAPAction", EInvoiceVNPTTerm.UPDATE_CUS_SOAP_ACTION);
	            
				result = callSoapApi("POST", EInvoiceVNPTTerm.UPDATE_CUS_SOAP_ENDPOINT,
						properties, soapRequest, EInvoiceVNPTTerm.UPDATE_CUS_SOAP_ACTION);

				_log.debug("UpdateCusResponse : " + result.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		UpdateCusResponse upCusResponse = unMarshalXmlToUpdateCusResponse(result);
		int updateCusResult = upCusResponse.getUpdateCusResult();
		switch (updateCusResult) {
		case -3:
			return Response.status(HttpStatus.SC_BAD_REQUEST)
						   .entity(EInvoiceVNPTTerm.UPDATE_CUS_RES_MESSAGE_ERR3)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
		case -2:
			return Response.status(HttpStatus.SC_NOT_IMPLEMENTED)
					   .entity(EInvoiceVNPTTerm.UPDATE_CUS_RES_MESSAGE_ERR2)
					   .type(MediaType.APPLICATION_JSON)
					   .build();
		case -1:
			return Response.status(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)
					   .entity(EInvoiceVNPTTerm.UPDATE_CUS_RES_MESSAGE_ERR1)
					   .type(MediaType.APPLICATION_JSON)
					   .build();
		default:
			return Response.status(HttpStatus.SC_OK)
					   .entity(EInvoiceVNPTTerm.UPDATE_CUS_RES_MESSAGE_SUCCESS)
					   .type(MediaType.APPLICATION_JSON)
					   .build();
		}
		
	}

	private static String convertListCustomerModelToXml(ListCustomerModel input){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ListCustomerModel.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter sw = new StringWriter();
			marshaller.marshal(input, sw);
			return "<![CDATA[\r\n" + sw.toString() + "\r\n" + "]]>";
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	private static String convertListInvModelToXml(ListInvModel listInvModel){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ListInvModel.class);			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter sw = new StringWriter();
			marshaller.marshal(listInvModel, sw);
			return "<![CDATA[\r\n" + sw.toString() + "\r\n" + "]]>";
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static UpdateCusResponse unMarshalXmlToUpdateCusResponse(String input) throws JAXBException, XMLStreamException {
		XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(new StringReader(input));
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag();
        while(!xsr.getLocalName().equals(EInvoiceVNPTTerm.UPDATE_CUS_RES)) {
            xsr.nextTag();
        }
        JAXBContext jc = JAXBContext.newInstance(UpdateCusResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<UpdateCusResponse> jb = unmarshaller.unmarshal(xsr, UpdateCusResponse.class);
        xsr.close();

		UpdateCusResponse updateCusResponse = jb.getValue();
		return updateCusResponse;
	}
	
	private static ImportAndPublishInvResponse unMarshalXmlToImportAndPublishInvResponse(String input) throws JAXBException, XMLStreamException {
		XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(new StringReader(input));
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag();
        while(!xsr.getLocalName().equals(EInvoiceVNPTTerm.IMPORTANDPUBLISHINV_RES)) {
            xsr.nextTag();
        }
        JAXBContext jc = JAXBContext.newInstance(ImportAndPublishInvResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<ImportAndPublishInvResponse> jb = unmarshaller.unmarshal(xsr, ImportAndPublishInvResponse.class);
        xsr.close();

        ImportAndPublishInvResponse importAndPublishInvResponse = jb.getValue();
		return importAndPublishInvResponse;
	}
	
	private void validRequiredParam(Dossier dossier, PaymentFile paymentFile) throws Exception {
		if (dossier != null && paymentFile != null) {
			
			if (StringUtils.isEmpty(dossier.getApplicantIdNo())
				|| StringUtils.isEmpty(dossier.getApplicantName())
				|| StringUtils.isEmpty(dossier.getAddress())
				|| StringUtils.isEmpty(paymentFile.getPaymentFee())) {

				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setCode(HttpStatus.SC_BAD_REQUEST);
				errorMsg.setMessage("Dữ liệu đầu vào không hợp lệ");
				errorMsg.setDescription("Dữ liệu đầu vào không hợp lệ");
				throwException(errorMsg);
				
			}
			
			if (paymentFile.getPaymentAmount() == 0) {
				
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setCode(HttpStatus.SC_NOT_FOUND);
				errorMsg.setMessage("Không phát hành biên lai cho hóa đơn không phát sinh phí");
				errorMsg.setDescription("Không phát hành biên lai cho hóa đơn không phát sinh phí");
				throwException(errorMsg);
			}
		}
	} 
			
	private static void throwException(ErrorMsg errorMsg) throws Exception {
		ResponseBuilder builder = Response.status(Response.Status.NOT_ACCEPTABLE);
		builder.type(MediaType.APPLICATION_JSON);
		builder.entity(errorMsg.getMessage());
		throw new WebApplicationException(builder.build());
	}

	
	
	private static DownloadInvPDFFkeyNoPayResponse unMarshalXmlToDownloadInvPDFFkeyNoPayResponse(String input) throws JAXBException, XMLStreamException {
		XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(new StringReader(input));
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag();
        while(!xsr.getLocalName().equals(EInvoiceVNPTTerm.DOWNLOADINVPDFFKEYNOPAY_RES)) {
            xsr.nextTag();
        }
        JAXBContext jc = JAXBContext.newInstance(DownloadInvPDFFkeyNoPayResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<DownloadInvPDFFkeyNoPayResponse> jb = unmarshaller.unmarshal(xsr, DownloadInvPDFFkeyNoPayResponse.class);
        xsr.close();

        DownloadInvPDFFkeyNoPayResponse downloadInvPDFFkeyNoPayResponse = jb.getValue();
		return downloadInvPDFFkeyNoPayResponse;
	}
	
	private Response uploadEInvoicePDF(PaymentFile paymentFile, String eInvoiceString, long groupId, 
			ServiceContext serviceContext, User user, HttpServletRequest request) {
		
		String realPath = PropsUtil.get(ConfigProps.EINV_VNPT_HOME) + "/" + "Invoices" + "/";
		try {
			File pdfFile = new File(realPath + System.currentTimeMillis() + ".pdf");
			byte[] decodeEInvoiceString = Base64.decode(eInvoiceString);
			FileOutputStream fos = new FileOutputStream(pdfFile);
			fos.write(decodeEInvoiceString);
			fos.flush();
			fos.close();						
			
			JSONObject result = JSONFactoryUtil.createJSONObject();		
			ContentDisposition cd = new ContentDisposition(
				      "form-data; name=\"input\"; filename=\"" + pdfFile.getName() + "\"");
			Attachment file = new Attachment("input", new FileInputStream(pdfFile ), cd);
			
			if (file.getDataHandler() != null) {
				result.put("Status", true);		
				try {
					FileEntry fileEntry = null;
					InputStream inputStream = file.getDataHandler().getInputStream();
					String sourceFileName = file.getDataHandler().getName();
					if (sourceFileName == null || sourceFileName.length() == 0) {
						sourceFileName = pdfFile.getName();
					}
					String fileType = StringPool.BLANK;
					long fileSize = 0;
					String destination = StringPool.BLANK;
					if (inputStream != null && Validator.isNotNull(sourceFileName)) {
						
						if(Validator.isNull(fileType)) {
							fileType = MimeTypesUtil.getContentType(sourceFileName);
						}
						
						if(fileSize == 0) {
							fileSize = inputStream.available();
						}
						String title = sourceFileName;
						serviceContext.setAddGroupPermissions(true);
						serviceContext.setAddGuestPermissions(true);

						Calendar calendar = Calendar.getInstance();

						calendar.setTime(new Date());
						
						if (destination == null) {
							destination = StringPool.BLANK;
						}

						destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
						destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
						destination += calendar.get(Calendar.DAY_OF_MONTH);
						DLFolder dlFolder = DLFolderUtil.getTargetFolder(user.getUserId(), groupId, groupId, false, 0, destination,
								StringPool.BLANK, false, serviceContext);
						PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
						PermissionThreadLocal.setPermissionChecker(checker);
						JSONObject fileServerObj = JSONFactoryUtil.createJSONObject();
						
						fileEntry = DLAppLocalServiceUtil.addFileEntry(user.getUserId(), groupId, dlFolder.getFolderId(), title,
							fileType, title, title,
							StringPool.BLANK, inputStream, fileSize, serviceContext);

						String fileName = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
						result.put("FileName", fileName);
						if (fileEntry != null) {
							fileServerObj.put("fileEntryId", fileEntry.getFileEntryId());
							paymentFile.setInvoiceFileEntryId(fileEntry.getFileEntryId());
							PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
						}
					}			
				} catch (IOException e) {
					_log.debug(e);
				} catch (Exception e) {
					_log.debug(e);
				}				
			}
			else {
				result.put("Status", false);			
			}
			
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	private static String removeSpecialChar(String input) {
		input = input.replace("&lt;", "<");
		input = input.replace("&gt;", ">");
		input = input.replace("&#13;", "");
		return input;
	}
	
	@SuppressWarnings("rawtypes")
	private String callSoapApi(String httpMethod, String urlPath, HashMap<String, String> properties,
			String soapRequest, String soapAction) {
		
		String response = "";

		HttpURLConnection conn = null;

		BufferedReader br = null;
		
		try {
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(20000);
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);
            
			// Set the appropriate HTTP parameters.
            byte[] soapRequestBytes = new byte[soapRequest.length()];
            soapRequestBytes = soapRequest.getBytes("UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(soapRequestBytes.length));
            
            if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}    
            conn.getOutputStream().write(soapRequestBytes);
            conn.getOutputStream().close();
            
            // Read the response
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;

			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			response = sb.toString();			
			
            conn.disconnect();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;		
	}
	
	private ResponseBuilder getEInvoiceFile(PaymentFile paymentFile) {
		
		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(paymentFile.getInvoiceFileEntryId());
			File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
					true);
			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition",
					"attachment; filename=\"" + fileEntry.getFileName() + "\"");
			responseBuilder.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder;
		} catch (PortalException e) {
			e.getMessage();
		}
		return null;
	}
	
	
}
