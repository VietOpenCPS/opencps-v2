package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.util.Base64;

public class POSVCBUtils {

    public static JSONObject getRequestConnectionPOSVCB(long groupId,JSONObject defaultJSON ){
        try {
            StringBuilder sb = new StringBuilder();
//            _log.debug("SERVER PROXY: " + defaultJSON.toString());
            if (Validator.isNotNull(defaultJSON)) {
                String serverUrl = StringPool.BLANK;
                String merchantOutletId = StringPool.BLANK;
                String merchantId = StringPool.BLANK;
                String clientId = StringPool.BLANK;

                JSONObject configObj = defaultJSON;

                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                merchantOutletId = configObj.getString(SyncServerTerm.MERCHANT_OUTLET_ID_CONFIG);
                merchantId = configObj.getString(SyncServerTerm.MERCHANT_ID_CONFIG);
                clientId = configObj.getString(SyncServerTerm.CLIENT_ID_CONFIG);

                URL urlSale = new URL(serverUrl);
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("EVENT", SyncServerTerm.CONN);
                jsonBody.put("MERCHANT_OUTLET_ID", merchantOutletId);
                jsonBody.put("MERCHANT_ID", merchantId);
                jsonBody.put("CLIENT_ID", clientId);

                String data = jsonBody.toString();
                String thirdPartyKey = SyncServerTerm.THIRD_PARTY_KEY;
                byte[] keyData = thirdPartyKey.getBytes();
                System.out.println("keyData: " + keyData);
                JSONObject jsonBodyEncrypt = JSONFactoryUtil.createJSONObject();

                jsonBodyEncrypt.put("KEY", configObj.getString(SyncServerTerm.THIRD_PARTY_ID));
                jsonBodyEncrypt.put("VALUE", encrypt3DES1(keyData, data));
                String body = "=" + jsonBodyEncrypt.toString();
                java.net.HttpURLConnection conSale = (java.net.HttpURLConnection) urlSale.openConnection();

                conSale.setRequestMethod(HttpMethod.POST);
                conSale.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conSale.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);

                conSale.setRequestProperty("Content-Length", StringPool.BLANK + Integer.toString(body.getBytes().length));
                conSale.setUseCaches(false);
                conSale.setDoInput(true);
                conSale.setDoOutput(true);
                OutputStream osLogin = conSale.getOutputStream();

                osLogin.write(body.getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conSale.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                try {
                    String dataSub = sb.toString().replaceAll("\"","");
                    _log.info("DataSub: " + dataSub);
                    String decrypt = decrypt3DES1(keyData, dataSub);
                    JSONObject response = JSONFactoryUtil.createJSONObject(decrypt);
                    int total = response.getInt("TOTAL");
                    _log.info("Total: " + total);
                    if (total > 0) {
                        JSONArray reponseArr = response.getJSONArray(SyncServerTerm.CONN);
                        JSONObject reponseJSON = reponseArr.getJSONObject(0);
                        if (Validator.isNotNull(reponseJSON)) {
                            _log.debug("Máy Online : " + total);
                            return reponseJSON;
                        } else {
                            _log.debug("Không tìm được máy Online");
                            return null;
                        }
                    }
                }catch (Exception e){
                   _log.info("Exception");
                    e.getMessage();
                    return null;
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
    public static JSONObject getPaymentConfig(long groupId, String govAgencyCode){
        try {
            PaymentConfig payConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId, govAgencyCode);
            if (payConfig != null && Validator.isNotNull(payConfig.getEpaymentConfig())) {
                JSONObject config = JSONFactoryUtil.createJSONObject(payConfig.getEpaymentConfig());
                if(Validator.isNotNull(config.getString("POS_VCB"))) {
                    JSONObject configPOS = JSONFactoryUtil.createJSONObject(config.getString("POS_VCB"));
                    if (Validator.isNotNull(configPOS) && Validator.isNotNull(configPOS.getString("default"))) {
                        JSONObject defaultJSON = JSONFactoryUtil.createJSONObject(configPOS.getString("default"));
                        return defaultJSON;
                    }else{
                        _log.debug("Không có cấu hình ");
                    }
                }else{
                    _log.debug("Không có cấu hình ");
                    return null;
                }
            }else{
                _log.debug("Không có cấu hình PaymentConfig ");
            }
        }catch (Exception e){
            _log.debug("Exception ");
            e.getMessage();
        }
        return null;
    }

    public static String saleRequestDataPOSVCB(long groupId, String govAgencyCode,
                                               long amount, String currencyCode, String staffId,
                                               String addPrint, String addData, String orderId) {

        try {
            JSONObject defaultJSON = getPaymentConfig(groupId, govAgencyCode);
            JSONObject reponseJSON = getRequestConnectionPOSVCB(groupId, defaultJSON);

            StringBuilder sb = new StringBuilder();
            if (Validator.isNotNull(defaultJSON) && Validator.isNotNull(reponseJSON)) {
                _log.debug("SERVER PROXY: " + reponseJSON.toString());
                String serverUrl = StringPool.BLANK;
                String serialNumber = StringPool.BLANK;
                String refId = StringPool.BLANK;
                String merchantOutletId = StringPool.BLANK;
                String merchantId = StringPool.BLANK;
                String clientId = StringPool.BLANK;

                serialNumber = reponseJSON.getString(SyncServerTerm.SERIAL_NUMBER);
                refId = reponseJSON.getString(SyncServerTerm.REF_ID);
                merchantOutletId = reponseJSON.getString(SyncServerTerm.MERCHANT_OUTLET_ID);
                merchantId = reponseJSON.getString(SyncServerTerm.MERCHANT_ID);
                clientId = reponseJSON.getString(SyncServerTerm.CLIENT_ID);

                serverUrl = defaultJSON.getString(SyncServerTerm.SERVER_URL);

                URL urlSale = new URL(serverUrl);
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("EVENT", SyncServerTerm.EVENT_SALE);
                jsonBody.put("REF_ID", refId);
                jsonBody.put("SERIAL_NUMBER", serialNumber);
                jsonBody.put("TERMINAL_ID", refId);
                String paymentAmount = paresAmount(amount);
                jsonBody.put("AMOUNT", paymentAmount);
                jsonBody.put("CURRENCY_CODE", currencyCode);
                jsonBody.put("STAFF_ID", staffId);
                jsonBody.put("ADD_PRINT", addPrint);
                jsonBody.put("ADD_DATA", addData);
                jsonBody.put("ORDER_ID", orderId);
                jsonBody.put("APP", SyncServerTerm.APP);
                jsonBody.put("MERCHANT_OUTLET_ID", merchantOutletId);
                jsonBody.put("MERCHANT_ID", merchantId);
                jsonBody.put("CLIENT_ID", clientId);

                String data = jsonBody.toString();
                System.out.println("Data: " + data);
                String thirdPartyKey = SyncServerTerm.THIRD_PARTY_KEY;
                byte[] keyData = thirdPartyKey.getBytes();
                System.out.println("keyData: " + keyData);

                JSONObject jsonBodyEncrypt = JSONFactoryUtil.createJSONObject();

                jsonBodyEncrypt.put("KEY", defaultJSON.getString(SyncServerTerm.THIRD_PARTY_ID));
                jsonBodyEncrypt.put("VALUE", encrypt3DES1(keyData, data));
                String body = "=" + jsonBodyEncrypt.toString();

                _log.debug("POST DATA: " + body);

                java.net.HttpURLConnection conSale = (java.net.HttpURLConnection) urlSale.openConnection();

                conSale.setRequestMethod(HttpMethod.POST);
                conSale.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conSale.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
                conSale.setRequestProperty("Content-Length", StringPool.BLANK + Integer.toString(body.getBytes().length));
                conSale.setUseCaches(false);
                conSale.setDoInput(true);
                conSale.setDoOutput(true);
                OutputStream osLogin = conSale.getOutputStream();
                osLogin.write(body.getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conSale.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                String dataSub = sb.toString().replaceAll("\"", "");
                String decrypt3D = decrypt3DES1(keyData, dataSub);

                return decrypt3D;
            } else {
                return "Không có máy POS Online";
            }
        } catch (Exception e) {
            _log.debug(e);
            return null;
        }
    }

    public static String voidPOSVCB( long groupId, String govAgencyCode, String currencyCode, String staffId,
                                     String orderId,  JSONObject resultJSON, String addPrint) {

        try {
            JSONObject defaultJSON = getPaymentConfig(groupId, govAgencyCode);
            JSONObject reponseJSON = getRequestConnectionPOSVCB(groupId, defaultJSON);
            StringBuilder sb = new StringBuilder();
            if (Validator.isNotNull(defaultJSON) && Validator.isNotNull(reponseJSON) && Validator.isNotNull(resultJSON.getString(SyncServerTerm.INVOICE))) {
                _log.info("Vaooooooo 111111111");
                String serverUrl = StringPool.BLANK;
                String serialNumber = StringPool.BLANK;
                String refId = StringPool.BLANK;
                String merchantOutletId = StringPool.BLANK;
                String merchantId = StringPool.BLANK;
                String clientId = StringPool.BLANK;
                String invoice = StringPool.BLANK;
                String addData = StringPool.BLANK;
                String amount = StringPool.BLANK;

                serialNumber = reponseJSON.getString(SyncServerTerm.SERIAL_NUMBER);
                refId = resultJSON.getString(SyncServerTerm.REF_ID);
                merchantOutletId = reponseJSON.getString(SyncServerTerm.MERCHANT_OUTLET_ID);
                merchantId = reponseJSON.getString(SyncServerTerm.MERCHANT_ID);
                clientId = reponseJSON.getString(SyncServerTerm.CLIENT_ID);
                invoice = resultJSON.getString(SyncServerTerm.INVOICE);
                addData = resultJSON.getString(SyncServerTerm.ADD_DATA);
                amount = resultJSON.getString(SyncServerTerm.AMOUNT);

                serverUrl = defaultJSON.getString(SyncServerTerm.SERVER_URL);
                URL urlVoid = new URL(serverUrl);

                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("EVENT", SyncServerTerm.EVENT_VOID);
                jsonBody.put("REF_ID", refId);
                jsonBody.put("SERIAL_NUMBER", serialNumber);
                jsonBody.put("TERMINAL_ID", refId);
                jsonBody.put("AMOUNT", amount);
                jsonBody.put("CURRENCY_CODE", currencyCode);
                jsonBody.put("STAFF_ID", staffId);
                jsonBody.put("ADD_PRINT", addPrint);
                jsonBody.put("INVOICE", invoice);
                jsonBody.put("AMOUNT_CONFIRM", "Y");
                jsonBody.put("ADD_DATA", addData);
                jsonBody.put("ORDER_ID", orderId);
                jsonBody.put("APP", SyncServerTerm.APP);
                jsonBody.put("MERCHANT_OUTLET_ID", merchantOutletId);
                jsonBody.put("MERCHANT_ID", merchantId);
                jsonBody.put("CLIENT_ID", clientId);

                String data = jsonBody.toString();
                _log.info("Data: " + data);

                String thirdPartyKey = SyncServerTerm.THIRD_PARTY_KEY;
                byte[] keyData = thirdPartyKey.getBytes();

                JSONObject jsonBodyEncrypt = JSONFactoryUtil.createJSONObject();

                jsonBodyEncrypt.put("KEY", defaultJSON.getString(SyncServerTerm.THIRD_PARTY_ID));
                jsonBodyEncrypt.put("VALUE", encrypt3DES1(keyData, data));
                _log.info("Vaooooooo 22222222: " + jsonBodyEncrypt.toString());
                String body = "=" + jsonBodyEncrypt.toString();

                _log.debug("POST DATA: " + body);

                java.net.HttpURLConnection conVoid = (java.net.HttpURLConnection) urlVoid.openConnection();

                conVoid.setRequestMethod(HttpMethod.POST);
                conVoid.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conVoid.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
                conVoid.setRequestProperty("Content-Length", StringPool.BLANK + Integer.toString(body.getBytes().length));
                conVoid.setUseCaches(false);
                conVoid.setDoInput(true);
                conVoid.setDoOutput(true);
                _log.debug("POST DATA: " + body);
                OutputStream osLogin = conVoid.getOutputStream();
                osLogin.write(body.getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conVoid.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                _log.info("Vaooooooo 33333: " + sb.toString());
//                    String dataSub = sb.toString().replaceAll("\"", "");
//                    String decrypt3D = decrypt3DES1(keyData, dataSub);
                return decrypt3DES1(keyData, sb.toString().replaceAll("\"", ""));

            }
            return null;
        } catch (Exception e) {
            _log.debug(e);
            return null;
        }

    }

    public static String settlementPOSVCB(Long groupId, String serverCode, String refId, String serialNumber,
                                     String staffId, String invoice, String amountConfirm,
                                     String addData, String orderId) {


        try {
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "POS_VCB";

            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
            StringBuilder sb = new StringBuilder();
            _log.debug("SERVER PROXY: " + sc.getConfigs());
            if (sc != null) {
                String serverUrl = StringPool.BLANK;

                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                URL urlSettlement = new URL(serverUrl);

                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("EVENT",SyncServerTerm.EVENT_SETTLEMENT);
                jsonBody.put("REF_ID",refId);
                jsonBody.put("SERIAL_NUMBER",serialNumber);
                jsonBody.put("AMOUNT_CONFIRM",Validator.isNotNull(amountConfirm) ? amountConfirm : "Y");
                jsonBody.put("STAFF_ID",staffId);
                jsonBody.put("ADD_PRINT",SyncServerTerm.ADD_PRINT_SETTLEMENT);
                jsonBody.put("INVOICE",invoice);
                jsonBody.put("ADD_DATA",addData);
                jsonBody.put("ORDER_ID",orderId);
                jsonBody.put("APP",SyncServerTerm.APP);
                jsonBody.put("MERCHANT_OUTLET_ID",configObj.getString(SyncServerTerm.MERCHANT_OUTLET_ID));
                jsonBody.put("MERCHANT_ID",configObj.getString(SyncServerTerm.MERCHANT_ID));
                jsonBody.put("CLIENT_ID",configObj.getString(SyncServerTerm.CLIENT_ID));

                String data = jsonBody.toString();

//                byte[] keyData = configObj.getString(SyncServerTerm.THIRD_PARTY_KEY).getBytes();

                String thirdPartyKey = SyncServerTerm.THIRD_PARTY_KEY;
                byte[] keyData = thirdPartyKey.getBytes();

                JSONObject jsonBodyEncrypt = JSONFactoryUtil.createJSONObject();

                jsonBodyEncrypt.put("KEY", configObj.getString(SyncServerTerm.THIRD_PARTY_ID));
                jsonBodyEncrypt.put("VALUE",encrypt3DES1(keyData,data));

                String body = "="+ jsonBodyEncrypt.toString();

                _log.debug("POST DATA: " + body);

                java.net.HttpURLConnection conSettlement = (java.net.HttpURLConnection) urlSettlement.openConnection();

                conSettlement.setRequestMethod(HttpMethod.POST);
                conSettlement.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conSettlement.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
                conSettlement.setRequestProperty("Content-Length",StringPool.BLANK + Integer.toString(body.getBytes().length));
                conSettlement.setUseCaches(false);
                conSettlement.setDoInput(true);
                conSettlement.setDoOutput(true);
                _log.debug("POST DATA: " + body);
                OutputStream osLogin = conSettlement.getOutputStream();
                osLogin.write(body.getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conSettlement.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                return decrypt3DES1(keyData,sb.toString());
            }
            return null;
        } catch (Exception e) {
            _log.debug(e);
            return null;
        }

    }

    public static String checkResultPOSVCB(long groupId, String govAgencyCode, String key){
        try {
            String result = StringPool.BLANK;
            JSONObject defaultJSON = getPaymentConfig(groupId, govAgencyCode);

            StringBuilder sb = new StringBuilder();
            _log.debug("SERVER PROXY: " + defaultJSON.toString());
            if (Validator.isNotNull(defaultJSON) && Validator.isNotNull(key)) {
                String serverUrl = StringPool.BLANK;

                JSONObject configObj = defaultJSON;
                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                URL urlSettlement = new URL(serverUrl);
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put("EVENT",SyncServerTerm.CHECK_RESULT);
                jsonBody.put("KEY",key);
                String data = jsonBody.toString();
//                byte[] keyData = configObj.getString(SyncServerTerm.THIRD_PARTY_KEY).getBytes();
                String thirdPartyKey = SyncServerTerm.THIRD_PARTY_KEY;
                byte[] keyData = thirdPartyKey.getBytes();

                JSONObject jsonBodyEncrypt = JSONFactoryUtil.createJSONObject();
                jsonBodyEncrypt.put("KEY", configObj.getString(SyncServerTerm.THIRD_PARTY_ID));
                jsonBodyEncrypt.put("VALUE",encrypt3DES1(keyData,data));

                String body = "="+ jsonBodyEncrypt.toString();
                _log.debug("POST DATA: " + body);
                java.net.HttpURLConnection conSettlement = (java.net.HttpURLConnection) urlSettlement.openConnection();

                conSettlement.setRequestMethod(HttpMethod.POST);
                conSettlement.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conSettlement.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
                conSettlement.setRequestProperty("Content-Length",StringPool.BLANK + Integer.toString(body.getBytes().length));
                conSettlement.setUseCaches(false);
                conSettlement.setDoInput(true);
                conSettlement.setDoOutput(true);

                OutputStream osLogin = conSettlement.getOutputStream();
                osLogin.write(body.getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conSettlement.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                _log.info("Data: " + sb.toString());
                String dataSub = sb.toString().replaceAll("\"", "");

                result = decrypt3DES1(keyData,dataSub);
            }else{
                return "Chưa có key giao dịch thanh toán";
            }
            return result;
        }catch (Exception e){
            _log.debug(e);
            return null;
        }
    }


    private static Log _log = LogFactoryUtil.getLog(POSVCBUtils.class.getName());

    private static String encrypt3DES1(byte[] keyData, String data) throws Exception {
        if (data == null || data.trim().equals(""))
            return "";
        SecretKey key = new SecretKeySpec(keyData, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[8]);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] stringBytes = data.getBytes("UTF8");
        byte[] cipherText = cipher.doFinal(stringBytes);
        String cipher_msg = Base64.getEncoder().encodeToString(cipherText);
        // String cipher_msg = encoder . encode ( cipherText );
        cipher_msg = DatatypeConverter.printHexBinary(cipher_msg.getBytes());
        cipher_msg = cipher_msg.toLowerCase();
        // Return cipher message
        return cipher_msg;
    }

    public static String decrypt3DES1(byte[] keyData, String data) throws Exception {
        try {
            if (data == null || data.trim().equals(""))
                return "";
            data = data.toUpperCase();
            SecretKey key = new SecretKeySpec(keyData, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(new byte[8]);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            String _data = new String(DatatypeConverter.parseHexBinary(data), "UTF8");
            byte[] raw = Base64.getDecoder().decode(_data);
            byte[] stringBytes = cipher.doFinal(raw);
            String plain_msg = new String(stringBytes, "UTF8");
            _log.info("plain_msg: " + plain_msg);
            return plain_msg;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String paresAmount(long amount){
        String paymentAmount = String.valueOf(amount);
        if (paymentAmount.length() == 4L) {
            paymentAmount = "000000" + paymentAmount + "00";
        } else if (paymentAmount.length() == 5) {
            paymentAmount = "00000" + paymentAmount + "00";
        } else if (paymentAmount.length() == 6) {
            paymentAmount = "0000" + paymentAmount + "00";
        } else if (paymentAmount.length() == 7) {
            paymentAmount = "000" + paymentAmount + "00";
        } else if (paymentAmount.length() == 8) {
            paymentAmount = "00" + paymentAmount + "00";
        }
        return  paymentAmount;
    }

}
