package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import org.opencps.api.controller.Mofa2Management;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import com.liferay.portal.kernel.util.Validator;
import sun.net.www.protocol.http.HttpURLConnection;

import java.net.URL;


public class Mofa2ManagementImpl implements Mofa2Management {

    @Override
    public Response createMofa2(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, DossierInputModel dossierInputModel, long dossierId, String serverCode) {
        try {
            String result = StringPool.BLANK;
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "VNPOST_CLS";
            long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

            Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
            if(Validator.isNotNull(dossier)){
                result = insertMofa2(groupId, dossier, serverCodeFind);
            }


            return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    public static String insertMofa2(long groupId, Dossier dossier, String serverCode){
        String result = StringPool.BLANK;
        String serverUrl = StringPool.BLANK;
        try {
            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCode);
            StringBuilder sb = new StringBuilder();
            if(sc !=null){
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject(configObj.getString(ServerConfigTerm.NHAN_THONG_BAO_KET_QUA));
                serverUrl = jsonObject.getString(SyncServerTerm.SERVER_URL);

                URL urlVoid = new URL(serverUrl);
                JSONArray jsonData = JSONFactoryUtil.createJSONArray();
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("So_Bien_Nhan",dossier.getDossierCounter());
                jsonBody.put("Du_Kien_Thu","45");
                jsonBody.put("Don_Vi_Tien_Te","$");
                jsonBody.put("Nguoi_Tao",dossier.getUserName());
                jsonBody.put("Ngay_Tao",dossier.getCreateDate());
                jsonBody.put("Nguoi_Nhan",dossier.getApplicantName());
                jsonBody.put("Ngay_Nhan",dossier.getApplicantName());


                jsonData.put(jsonBody);
            }
        }catch (Exception e){
            e.getMessage();
        }

        return result;
    }
}
