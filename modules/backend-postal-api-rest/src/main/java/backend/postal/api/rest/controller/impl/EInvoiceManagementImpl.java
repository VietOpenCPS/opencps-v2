package backend.postal.api.rest.controller.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.*;

import org.opencps.api.controller.util.InvoiceTerm;
import org.opencps.api.einvoice.model.FsNHGTGT;
import org.opencps.api.einvoice.model.HoadonCtT;
import org.opencps.api.einvoice.model.HoadonGtgtCtT;
import org.opencps.api.einvoice.model.HoadonT;
import org.opencps.api.einvoice.model.LstHoadonCtT;
import org.opencps.api.invoice.model.InvoiceInputModel;
import org.opencps.api.invoice.model.InvoiceServerConfigModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.w3c.dom.NodeList;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import backend.postal.api.rest.controller.EInvoiceManagement;

public class EInvoiceManagementImpl implements EInvoiceManagement {

	private static final Log _log = LogFactoryUtil.getLog(EInvoiceManagementImpl.class);

	public InvoiceServerConfigModel fromJSONObject(JSONObject configObj) {
		//_log.info("configObj =============== " + JSONFactoryUtil.looseSerialize(configObj));
		if (configObj.has(InvoiceTerm.SERVER_ENDPOINTURL) && configObj.has(InvoiceTerm.SERVER_ACTIVE)) {
			return new InvoiceServerConfigModel(configObj.getString(InvoiceTerm.SERVER_ENDPOINTURL),
					configObj.getBoolean(InvoiceTerm.SERVER_ACTIVE));
		} else {
			return null;
		}
	}

	public InvoiceServerConfigModel getServerConfig(long groupId, String protocol) {
		InvoiceServerConfigModel config = null;

		List<ServerConfig> lstsc = ServerConfigLocalServiceUtil.getByProtocol(groupId, protocol);
		//_log.info("lstsc ========= " + JSONFactoryUtil.looseSerialize(lstsc));
		if (lstsc == null) {
			return null;
		} else {
			try {
				for (ServerConfig sc : lstsc) {
					InvoiceServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					//_log.info("check ============ " + JSONFactoryUtil.looseSerialize(check));
					if (check.getActive()) {
						config = check;
					}
				}
				return config;
			} catch (JSONException e) {
				// e.printStackTrace();
				_log.debug(e);
				return null;
			}
		}

	}

	@Override
	public Response getInvoice(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, InvoiceInputModel input) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		InvoiceServerConfigModel config = getServerConfig(groupId, "EINVOICE");
		
		if(config == null) {
			return null;
		}
		
		FsNHGTGT fsNHGTGT = new FsNHGTGT();

		HoadonGtgtCtT hoadonGtgtCt = new HoadonGtgtCtT();

		HoadonCtT hoadonCt = new HoadonCtT();

		LstHoadonCtT lstHoadonCtT = new LstHoadonCtT();

		HoadonT hoadon = new HoadonT();
		hoadon.setMa("01GTKT0/001");
		// hoadon.setNgHd(formatDate(input.getNghd(),"dd/MM/yyyy"));
		hoadon.setNgHd(input.getNgayHd());
		hoadon.setSeri(input.getSeri());
		hoadon.setMaNthue("01");
		hoadon.setKieuSo("G");
		hoadon.setMaKh(input.getMaKhackHang());
		hoadon.setTen(input.getTen());
		hoadon.setPhone(input.getPhone());
		hoadon.setTax(input.getTax());
		hoadon.setDchi(input.getDchi());
		hoadon.setMaTk(input.getMaTk());
		hoadon.setTenNh(input.getTenNh());
		hoadon.setMailH(input.getMailH());
		hoadon.setPhoneH(input.getPhoneH());
		hoadon.setTenM(input.getTenM());
		hoadon.setMaKhL(input.getMaKhL());
		hoadon.setMaNt(input.getMaNt());
		hoadon.setTg(input.getTg());
		hoadon.setHthuc(input.getHthuc());
		hoadon.setHan(input.getHan());
		hoadon.setTlGgia(input.getTlGgia());
		hoadon.setGgia(input.getGgia());
		hoadon.setPhi(input.getPhi());
		hoadon.setNoidung(input.getNoidung());
		hoadon.setTien(formatStringtoBigDecimal(input.getTien()));
		hoadon.setTtoan(formatStringtoBigDecimal(input.getTtoan()));

		hoadonGtgtCt.setMaVt(input.getMaVtDetail());
		hoadonGtgtCt.setTen(input.getTenDetail());
		hoadonGtgtCt.setDvt(input.getDvtDetail());
		hoadonGtgtCt.setLuong(input.getLuongDetail());
		hoadonGtgtCt.setGia(input.getGiaDetail());
		hoadonGtgtCt.setTien(formatStringtoBigDecimal(input.getTienDetail()));
		hoadonGtgtCt.setTs(input.getTsDetail());
		hoadonGtgtCt.setThue(input.getThueDetail());
		hoadonGtgtCt.setTtoan(formatStringtoBigDecimal(input.getTtoanDetail()));
		
		List<HoadonGtgtCtT> lstHoadonGtgtCt = new ArrayList<HoadonGtgtCtT>();
		lstHoadonGtgtCt.add(hoadonGtgtCt);

		lstHoadonCtT.getHoadonGtgtCt().addAll(lstHoadonGtgtCt);
		hoadonCt.getHoadonGtgtCt().addAll(lstHoadonGtgtCt);

		hoadon.setLstHoadonCt(lstHoadonCtT);

		fsNHGTGT.setBNsd(input.getUserName());
		fsNHGTGT.setBMk(input.getPassWord());
		fsNHGTGT.setBSoId(input.getSoid());
		fsNHGTGT.setHoadonCt(hoadonCt);
		fsNHGTGT.setHoadon(hoadon);
		fsNHGTGT.setBKtraDch("");

		// String results = "khong the ket noi den server HDDT !!!!!";
		String abc = "";

		try {
			//String soapEndpointUrl = "http://hoadon.cmcsoft.com/Service/iv_v/siv_v_ph_hoadon.asmx";
			
			String soapEndpointUrl = config.getEndPointUrl();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage message = mf.createMessage();

			SOAPBody body = message.getSOAPBody();

			SOAPHeader soapheader = message.getSOAPHeader();
			soapheader.detachNode();

			MimeHeaders mimeHeader = message.getMimeHeaders();
			mimeHeader.setHeader("SOAPACTION", "http://tempuri.org/Fs_NH_GTGT");
			mimeHeader.setHeader("Content-Type", "text/xml; charset=utf-8");
			mimeHeader.setHeader("Proxy-Connection", "keep-alive");

			JAXBContext context = JAXBContext.newInstance(fsNHGTGT.getClass());

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(fsNHGTGT, body);

			message.saveChanges();
			message.writeTo(System.out);
			
			try {
				// Create SOAP Connection
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
				SOAPConnection soapConnection = soapConnectionFactory.createConnection();

				// Set timeout to call soap
				URL endpoint = new URL(null, soapEndpointUrl, new URLStreamHandler() {
					protected URLConnection openConnection(URL url) throws IOException {
						URL clone = new URL(url.toString());
						HttpURLConnection connection = (HttpURLConnection) clone.openConnection();
						connection.setRequestProperty("Content-Type", "text/xml");

						connection.setRequestProperty("Accept", "application/soap+xml, text/*");

						connection.setDoOutput(true);
						connection.setConnectTimeout(3 * 1000);
						connection.setReadTimeout(3 * 1000);
						return connection;
					}
				});

				// Send SOAP Message to SOAP Server
				SOAPMessage soapResponse = soapConnection.call(message, endpoint);

				// Print the SOAP Response
				//_log.info("Response SOAP Message:");
				// ByteArrayOutputStream stream = new ByteArrayOutputStream();
				
				NodeList nodes = soapResponse.getSOAPBody().getElementsByTagName("Fs_NH_GTGTResult");
				
		        // check if the node exists and get the value
		        
		        Node node = (Node) nodes.item(0);
		        
		        abc = node != null ? node.getTextContent() : "";
		        
				soapResponse.writeTo(System.out);
				//_log.info("abc ============ " + abc );
				// results = new String(stream.toByteArray(), "utf-8");
				
				soapConnection.close();
				
			} catch (Exception e) {
				_log.error(e);
			}

		} catch (IOException e) {
			_log.error(e);
		} catch (JAXBException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}

		return Response.status(200).entity(abc).build();

	}

	private BigDecimal formatStringtoBigDecimal(String str) {
		if(str != null) {
			BigDecimal b = new BigDecimal(str);
			return b;
		} else {
			return new BigDecimal(0);
		}
		
	}
}
