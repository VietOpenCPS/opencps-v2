package org.opencps.api.controller.impl;

import backend.api.rest.application.BackendAPIRestApplication;
import backend.auth.api.exception.BusinessExceptionImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.VNPostManagement;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.api.dossier.model.DossierResultsModel;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierStatusMappingImpl;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

public class VNPostManagementImpl implements VNPostManagement
{
	private static final Log _log = LogFactoryUtil.getLog(VNPostManagementImpl.class);
	@Override
	public Response getDossierDetailByBarcode(HttpServletRequest request,HttpHeaders header,ServiceContext serviceContext,
		String receiptCode)
	{
		String [] receiptCodeSplit = receiptCode.split(StringPool.DASH);
		String checkKey = receiptCodeSplit[0];
		String securityCode = StringPool.BLANK;
		long dossierId ;
		long dossierCounter ;
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		if (Validator.isNotNull(receiptCode) && receiptCodeSplit.length==4 && checkKey.equals("D"))
		{
			securityCode = receiptCodeSplit[1];
			dossierId = Long.parseLong(receiptCodeSplit[2]);
			dossierCounter = Long.parseLong(receiptCodeSplit[3]);
			List<Dossier> dossiers = DossierLocalServiceUtil.fetchDossierByG_DID(groupId,dossierId);
			_log.info(dossiers.size());
			if (Validator.isNotNull(dossiers) && dossiers.size() ==1 && Validator.isNotNull(dossiers.get(0)))
			{
				Dossier dossier = dossiers.get(0);
				String password = dossier.getPassword();
				long counter = dossier.getCounter();
				if (Validator.isNotNull(password) && securityCode.equals(password) && Validator.isNotNull(counter) && dossierCounter==counter)
				{
					_log.info(dossiers.size());
					DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier,0);
					return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
				}
			}
		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	@Override public Response updateDossierByBarcode(HttpServletRequest request,HttpHeaders header,
		ServiceContext serviceContext,String receiptCode,DossierInputModel dossierInputModel, String ma_bien_nhan)
	{
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		int viaPostal = dossierInputModel.getViaPostal();
		String postalServiceCode = dossierInputModel.getPostalServiceCode();
		String postalServiceName = dossierInputModel.getPostalServiceName();
		String postalAddress = dossierInputModel.getPostalAddress();
		String postalCityCode = dossierInputModel.getPostalCityCode();
		String postalCityName  = dossierInputModel.getPostalCityName();
		String postalDistrictCode = dossierInputModel.getPostalDistrictCode();
		String postalDistrictName = dossierInputModel.getPostalDistrictName();
		String postalWardCode = dossierInputModel.getPostalWardCode();
		String postalWardName = dossierInputModel.getPostalWardName();
		String postalTelNo = dossierInputModel.getPostalTelNo();

		DossierActions dossierActions = new DossierActionsImpl();
		Dossier dossier = dossierActions.updateDossierVNPost(groupId , ma_bien_nhan,viaPostal, postalServiceCode,
			postalServiceName, postalAddress, postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalWardCode, postalWardName, postalTelNo);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(dossier) && Validator.isNotNull(dossier.getDossierId()))
			jsonObject.put("status", "succeed");
		else
			jsonObject.put("status","failed");

		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toString()).build();
	}
}