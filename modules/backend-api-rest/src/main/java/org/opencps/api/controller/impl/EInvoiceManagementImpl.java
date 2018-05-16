package org.opencps.api.controller.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.*;

import org.opencps.api.controller.EInvoiceManagement;
import org.opencps.api.einvoice.model.FsNHGTGT;
import org.opencps.api.einvoice.model.HoadonCtT;
import org.opencps.api.einvoice.model.HoadonGtgtCtT;
import org.opencps.api.einvoice.model.HoadonT;
import org.opencps.api.einvoice.model.InvoiceInputModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class EInvoiceManagementImpl implements EInvoiceManagement {
	@Override
	public Response getInvoice(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, InvoiceInputModel input) {
		FsNHGTGT fsNHGTGT = new FsNHGTGT();

		HoadonGtgtCtT hoadonGtgtCt = new HoadonGtgtCtT();

		HoadonCtT hoadonCt = new HoadonCtT();

		HoadonT hoadon = new HoadonT();
		hoadon.setMa("01GTKT0/001");
//		hoadon.setNgHd(formatDate(input.getNghd(),"dd/MM/yyyy"));
		hoadon.setNgHd(input.getNgHd());
		hoadon.setSeri(input.getSeri());
		hoadon.setMaNthue("01");
		hoadon.setKieuSo("G");
		hoadon.setMaKh(input.getMaKh());
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
		hoadon.setGgia("22000");
		hoadon.setPhi("5000");
		hoadon.setNd("ảnh ảnh nội ");
		hoadon.setNoidung(input.getNoidung());
		hoadon.setTien("600000");
		hoadon.setTtoan("605000");
//		hoadon.setNGAYHT(formatDate(input.getNgayHT(),"dd/MM/yyyy"));
		hoadon.setNGAYHT(input.getNgayHT());
		BigDecimal bd = new BigDecimal(1.5);

		hoadonGtgtCt.setMaVt(input.getMaVt());
		hoadonGtgtCt.setTEN(input.getTenDetail());
		hoadonGtgtCt.setDvt(input.getDvt());
		hoadonGtgtCt.setLuong(bd);
		hoadonGtgtCt.setGia(new BigDecimal(20000));
		hoadonGtgtCt.setTien(new BigDecimal(200000));
		hoadonGtgtCt.setTs(new BigDecimal(10));
		hoadonGtgtCt.setThue("2000");
		hoadonGtgtCt.setTtoan(new BigDecimal(220000));

		List<HoadonGtgtCtT> lstHoadonGtgtCt = new ArrayList<HoadonGtgtCtT>();
		lstHoadonGtgtCt.add(hoadonGtgtCt);

		hoadonCt.getHoadonGtgtCt().addAll(lstHoadonGtgtCt);
		
		fsNHGTGT.setBNsd("HA");
		fsNHGTGT.setBMk("1");
		fsNHGTGT.setBSoId("1");
		fsNHGTGT.setHoadonCt(hoadonCt);
		fsNHGTGT.setHoadon(hoadon);
		fsNHGTGT.setBKtraDch("");

		String results = "lỗi rồi !!!!!";

		try {
			String soapEndpointUrl = "http://hoadon.cmcsoft.com/Service/iv_v/siv_v_ph_hoadon.asmx";

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

				// Send SOAP Message to SOAP Server
				SOAPMessage soapResponse = soapConnection.call(message, soapEndpointUrl);

				// Print the SOAP Response
				System.out.println("Response SOAP Message:");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				soapResponse.writeTo(stream);
				soapResponse.writeTo(System.out);
				System.out.println();
				results = new String(stream.toByteArray(), "utf-8");

				soapConnection.close();
			} catch (Exception e) {
				System.err.println(
						"\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(results).build();

	}
	
	private String formatDate(String string, String formatType){
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		if(Validator.isNull(string)){
			Date now = new Date();
			string = now.toString();
		}
		return sdf.format(string);
	}
}
