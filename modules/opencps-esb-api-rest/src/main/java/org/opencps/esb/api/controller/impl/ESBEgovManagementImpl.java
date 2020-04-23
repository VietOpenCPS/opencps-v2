package org.opencps.esb.api.controller.impl;

import com.inet.api.AuthClientException;
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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.esb.api.controller.ESBEgovManagement;
import org.opencps.esb.api.service.ClientConfigurationProvider;
import org.opencps.esb.api.service.HoraeCredentialsProvider;
import org.opencps.esb.api.utils.BuildEdXMLUtils;


public class ESBEgovManagementImpl implements ESBEgovManagement{

	private static final Log _log = LogFactoryUtil.getLog(ESBEgovManagementImpl.class);
	@Override
	public Response getESBCounter(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = GetterUtil.getLong(id);
		try {
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}
			if (dossier != null) {
				System.out.println("dossier: " + JSONFactoryUtil.looseSerialize(dossier));
				
				// Noi dung file edxml
				Content content = BuildEdXMLUtils.buildEdXMLSender(dossier, groupId);
				System.out.println(" Content: " + content.toString());
				// Send message
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

				if (content != null && slotResult != null) {
					SendKnobstickResult result = knobstickService
							.sendKnobstick(new SendKnobstickRequest().withSlot(slotResult.getSlot().getId())
									.withKey(content.getContent().getName()).withContentSha256(content.getHashCode())
									.withContent(new FileInputStream(content.getContent())));
					// ESB TRA VE THONG TIN
					System.out.println("Result knobstick = [" + result + "]");
					if (result != null) {
						// CHUYEN DU LIEU LIEN THONG DEN DON VI NHAN
						knobstickService.deliverKnobstick(
								new DeliverKnobstickRequest().withKnobstickId(result.getKnobstick().getId()));
					}
					
					// check send
					CheckSendKnobstickStatusResult result111 = knobstickService.checkSendKnobstickStatus(
							new CheckSendKnobstickStatusRequest().withKnobstickId(result.getKnobstick().getId()));
				}


			}
			
			return Response.status(200).entity("SUCCESS").build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(405).entity("{NOT SUCESS}").build();
		}
	}
}
