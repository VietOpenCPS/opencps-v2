package backend.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.constant.Constant;
import org.opencps.kernel.message.MBMessageEntry;
import backend.service.IntegrateLgsp;
import com.liferay.portal.kernel.log.Log;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.IntStream;

public class IntegrateLgspImpl implements IntegrateLgsp {
    private String domain = Constant.DOMAIN;
    private Log _log = LogFactoryUtil.getLog(IntegrateLgspImpl.class);
    private final String SUCCESSFUL = "SUCCESSFUL";
    private final String ERROR = "ERROR";
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private Integer MAX_CALL_SEND_MAIL = 8;

    @Override
    public String getToken() {
        try {
            boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
                    ? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp")) : false;

            if(!syncUserLGSP) {
                _log.error("sync User LGSP false");
                return "";
            }

            String pathGetToken = domain + Constant.ListUrlLGSP.GET_TOKEN.getValue();
            URL urlToken = new URL(pathGetToken);

            HttpURLConnection connection = (HttpURLConnection) urlToken.openConnection();
            connection.setRequestMethod(HttpMethod.POST);
            connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
            connection.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
            connection.setRequestProperty("Auth", Constant.KEY_AUTHENTICATE);
            connection.setRequestProperty("Content-Length", String.valueOf(0));

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            StringBuilder sbToken = new StringBuilder();
            OutputStream os = connection.getOutputStream();
            os.close();

            inputStreamReader = new InputStreamReader(connection.getInputStream());
            bufferedReader    = new BufferedReader(inputStreamReader);

            int cpToken;
            while ((cpToken = bufferedReader.read()) != -1) {
                sbToken.append((char) cpToken);
            }

            if (sbToken == null || sbToken.toString().isEmpty()) {
                _log.error("Get payload token failed");
                return "";
            }
            JSONObject jsonToken = JSONFactoryUtil.createJSONObject(sbToken.toString());

            if(!jsonToken.has("token")) {
                _log.error("Token not found in payload");
                return "";
            }

            return jsonToken.getString("token");
        } catch (Exception e) {
            _log.error(e);
            return "";
        } finally {
            if(inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }
        }
    }

    @Override
    public void sendMail(String token, MBMessageEntry messageEntry, String contactEmail) {
        try {
            String pathSendEmail = domain + Constant.ListUrlLGSP.GET_SEND_EMAIL.getValue();
            String authenticationKey = "Bearer" + StringPool.SPACE + token;
            URL urlSendEmail = new URL(pathSendEmail);

            StringBuilder sbSendMail = new StringBuilder();
            //create body
            JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
            jsonBody.put("mailReceiver", contactEmail);
            jsonBody.put("receiverName", contactEmail);
            jsonBody.put("content", messageEntry.getEmailBody());
            jsonBody.put("subject", messageEntry.getEmailSubject());
            //create connection
            HttpURLConnection connection = (HttpURLConnection) urlSendEmail.openConnection();
            connection.setRequestMethod(HttpMethod.POST);
            connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
            connection.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
            connection.setRequestProperty("Authorization", authenticationKey);
            connection.setRequestProperty("Content-Length",
                    StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream osLogin = connection.getOutputStream();
            osLogin.write(jsonBody.toString().getBytes());
            osLogin.close();

            inputStreamReader = new InputStreamReader(connection.getInputStream());
            bufferedReader    = new BufferedReader(inputStreamReader);
            String resultSendMail = "";

            for(int i = 1; i <= MAX_CALL_SEND_MAIL; i ++) {
                resultSendMail = sendMailOneTime(bufferedReader);
                if (resultSendMail.equals(SUCCESSFUL)){
                    break;
                }
            }

        } catch(Exception e){
            _log.error(e);
        } finally {
            if(inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }
        }
    }

    private String sendMailOneTime(BufferedReader bufferedReader) {
        try {
            StringBuilder sbSendMail = new StringBuilder();
            int cpSendMail;
            while ((cpSendMail = bufferedReader.read()) != -1) {
                sbSendMail.append((char) cpSendMail);
            }
            if(sbSendMail == null || sbSendMail.toString().isEmpty()) {
                return ERROR;
            }
            JSONObject jsonResult = JSONFactoryUtil.createJSONObject(sbSendMail.toString());
            if (jsonResult != null && jsonResult.has("result") &&
                    "SUCCESSFUL".equals(jsonResult.getString("result"))) {
                _log.info("Send mail succeed");
                return SUCCESSFUL;
            }
            return ERROR;
        } catch (Exception e) {
            _log.error(e);
            return ERROR;
        }
    }
}
