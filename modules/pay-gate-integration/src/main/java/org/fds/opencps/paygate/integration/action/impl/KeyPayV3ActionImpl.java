/**
 * 
 */
package org.fds.opencps.paygate.integration.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import com.liferay.portal.kernel.util.Validator;
import org.fds.opencps.paygate.integration.action.KeyPayV3Action;
import org.fds.opencps.paygate.integration.util.KeyPayV3Term;
import org.fds.opencps.paygate.integration.util.KeyPayV3Utils;
import org.fds.opencps.paygate.integration.util.PayGateTerm;
import org.fds.opencps.paygate.integration.util.PayGateUtil;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;

/**
 * @author moon
 *
 */
public class KeyPayV3ActionImpl implements KeyPayV3Action {

	private Log _log = LogFactoryUtil.getLog(KeyPayV3ActionImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fds.opencps.paygate.integration.action.KeyPayV3Action#createPaylater(com.
	 * liferay.portal.kernel.model.User, long, long,
	 * com.liferay.portal.kernel.service.ServiceContext,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String createPaylater(User user, long dossierId, ServiceContext serviceContext,
			HttpServletRequest request) {
		String result = StringPool.BLANK;

		try {

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			_log.info("DossierId" + dossierId);
			_log.info("Dossier" + JSONFactoryUtil.looseSerialize(dossier));
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(), dossierId);
			JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
					.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
			if (schema == null) {
				return result;
			}

			String client_id = schema.getString(KeyPayV3Term.CLIENT_ID);
			String addition_fee = String.valueOf(paymentFile.getShipAmount());
			String trans_amount = String.valueOf(paymentFile.getPaymentAmount());
			String command = schema.getString(KeyPayV3Term.COMMAND_PAYLATER);
			String version = schema.getString(KeyPayV3Term.VERSION);
			String hash_key_1 = schema.getString(KeyPayV3Term.CLIENT_KEY_1);

			JSONObject data = JSONFactoryUtil.createJSONObject();
			String transactionId = KeyPayV3Utils.encodeTransactionId(dossier.getDossierId());
			data.put(KeyPayV3Term.CLIENT_ID, schema.getString(KeyPayV3Term.CLIENT_ID));
			data.put(KeyPayV3Term.TRANSACTION_ID, transactionId);
			data.put(KeyPayV3Term.TRANS_AMOUNT, trans_amount);
			data.put(KeyPayV3Term.COMMAND, command);// default PAY
			data.put(KeyPayV3Term.VERSION, version);// default "3.0"
			data.put(KeyPayV3Term.DESCRIPTION, paymentFile.getPaymentNote());// ?
			data.put(KeyPayV3Term.LOCALE, schema.getString(KeyPayV3Term.LOCALE));// default vn
			data.put(KeyPayV3Term.COUNTRY_CODE, schema.getString(KeyPayV3Term.COUNTRY_CODE)); // default vi
			data.put(KeyPayV3Term.CURRENCY_CODE, schema.getString(KeyPayV3Term.CURRENCY_CODE)); // default VND
			data.put(KeyPayV3Term.ENVIRONMENT, schema.getString(KeyPayV3Term.ENVIRONMENT)); // default 1-web, 2-app
			data.put(KeyPayV3Term.BILL_ID, dossier.getDossierId());

			JSONObject bill_info = JSONFactoryUtil.createJSONObject();
			bill_info.put(KeyPayV3Term.MADICHVU, schema.getString(KeyPayV3Term.MADICHVU)); // thu phi le phi = 2

			// moi dich vu cong co 1 thong tin ngan hang thu huong khac nhau
			JSONObject banksInfo = schema.getJSONObject(KeyPayV3Term.BANKINFO);
			JSONObject bankInfo = JSONFactoryUtil.createJSONObject();
			if (banksInfo.has(dossier.getServiceCode())) {
				bankInfo = banksInfo.getJSONObject(dossier.getServiceCode());
			} else {
				bankInfo = banksInfo.getJSONObject(KeyPayV3Term.DEFAULT);
			}
			bill_info.put(KeyPayV3Term.TKTHUHUONG, bankInfo.getString(KeyPayV3Term.TKTHUHUONG));
			bill_info.put(KeyPayV3Term.MANHTHUHUONG, bankInfo.getString(KeyPayV3Term.MANHTHUHUONG));
			bill_info.put(KeyPayV3Term.TENTKTHUHUONG, bankInfo.getString(KeyPayV3Term.TENTKTHUHUONG));

			JSONArray philephi = JSONFactoryUtil.createJSONArray();

			JSONObject philephiJ = JSONFactoryUtil.createJSONObject();
			philephiJ.put(KeyPayV3Term.LOAIPHILEPHI, KeyPayV3Term.LOAIPHILEPHI_PHI);
			philephiJ.put(KeyPayV3Term.MAPHILEPHI, "2");
			philephiJ.put(KeyPayV3Term.TENPHILEPHI, schema.getString(KeyPayV3Term.TENPHILEPHI_PHI));
			philephiJ.put(KeyPayV3Term.SOTIEN, paymentFile.getServiceAmount());
			philephi.put(philephiJ);
			JSONObject philephiJ2 = JSONFactoryUtil.createJSONObject();
			philephiJ2.put(KeyPayV3Term.LOAIPHILEPHI, KeyPayV3Term.LOAIPHILEPHI_LEPHI);
			philephiJ2.put(KeyPayV3Term.MAPHILEPHI, "2");
			philephiJ2.put(KeyPayV3Term.TENPHILEPHI, schema.getString(KeyPayV3Term.TENPHILEPHI_LEPHI));
			philephiJ2.put(KeyPayV3Term.SOTIEN, paymentFile.getFeeAmount());
			philephi.put(philephiJ2);
			bill_info.put(KeyPayV3Term.PHILEPHI, philephi);

			// TODO:
//				MaDonVi,TenDonVi,MaCoQuanQD,TenCoQuanQD
//
//				MaDonVi: 000.00.00.G17 TenDonVi: Bộ Xây dựng
//
//				MaCQ và Ten CQ trùng luôn MaDonVi và TenDonVi
			bill_info.put(KeyPayV3Term.MADONVI, schema.getString(KeyPayV3Term.MADONVI));
			bill_info.put(KeyPayV3Term.TENDONVI, schema.getString(KeyPayV3Term.TENDONVI));
			bill_info.put(KeyPayV3Term.MAHOSO, dossier.getDossierNo());
//			ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(dossier.getGroupId(),
//					dossier.getServiceCode());
			String serviceCode = dossier != null ? dossier.getServiceCode()
					: StringPool.BLANK;
			// TODO validate
			bill_info.put(KeyPayV3Term.MATTHC, dossier.getServiceCode());// ma TTHC  = serviceCode
			bill_info.put(KeyPayV3Term.TENTTHC, dossier.getServiceName());
			// TODO: fix
			bill_info.put(KeyPayV3Term.MADVC, serviceCode + schema.getString(KeyPayV3Term.MADVCAPPEND));// chua xd
			bill_info.put(KeyPayV3Term.TENDVC, dossier.getServiceName());// chua xd
			bill_info.put(KeyPayV3Term.NOIDUNGTHANHTOAN, paymentFile.getPaymentNote());
			bill_info.put(KeyPayV3Term.MALOAIHINHTHUPHAT, "");// ko bb;

			bill_info.put(KeyPayV3Term.HOTENNGUOINOP, dossier.getApplicantName());

			bill_info.put(KeyPayV3Term.SOCMNDNGUOINOP, dossier.getApplicantIdNo());
			bill_info.put(KeyPayV3Term.DIACHINGUOINOP, dossier.getAddress());
			bill_info.put(KeyPayV3Term.HUYENNGUOINOP, dossier.getDistrictName());// ko bb
			bill_info.put(KeyPayV3Term.TINHNGUOINOP, dossier.getCityName());// ko bb
			// TODO: fix MACOQUANQD TENCOQUANQD
			bill_info.put(KeyPayV3Term.MACOQUANQD, dossier.getGovAgencyCode());
			bill_info.put(KeyPayV3Term.TENCOQUANQD, dossier.getGovAgencyName());

			bill_info.put(KeyPayV3Term.KHOBAC, "");// ko bb
			bill_info.put(KeyPayV3Term.NGAYQD, "");// ko bb
			bill_info.put(KeyPayV3Term.SOQD, "");// ko bb

			bill_info.put(KeyPayV3Term.THOIGIANVIPHAM, "");
			bill_info.put(KeyPayV3Term.DIADIEMVIPHAM, "");
			bill_info.put(KeyPayV3Term.TENNGUOIVIPHAM, "");
			bill_info.put(KeyPayV3Term.TAIKHOANTHUNSNN, "");

			JSONArray dskhoannop = JSONFactoryUtil.createJSONArray();
			JSONObject dskhoannop_obj = JSONFactoryUtil.createJSONObject();

			dskhoannop_obj.put(KeyPayV3Term.NOIDUNG, paymentFile.getPaymentNote());
			dskhoannop_obj.put(KeyPayV3Term.SOTIEN, String.valueOf(paymentFile.getPaymentAmount()));
			dskhoannop.put(dskhoannop_obj);

			bill_info.put(KeyPayV3Term.DSKHOANNOP, dskhoannop);

			data.put(KeyPayV3Term.BILL_INFO, bill_info);

			JSONObject config = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
					.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
			if (dossier.isOnline()) {
				String returnUrl = config.getJSONObject(KeyPayV3Term.ACTION_IS_ONLINE)
						.getString(KeyPayV3Term.URL_DOMAIN);
				data.put(KeyPayV3Term.RETURN_URL, returnUrl);
			} else {
				String returnUrl = config.getJSONObject(KeyPayV3Term.ACTION_IS_NOT_ONLINE)
						.getString(KeyPayV3Term.URL_DOMAIN);
				data.put(KeyPayV3Term.RETURN_URL, returnUrl);
			}

			data.put(KeyPayV3Term.ADDITION_FEE, addition_fee);

//			String check_sum = KeyPayV3Utils.genCreateChecksum(client_id, command, transactionId, hash_key_1);
			String check_sum = KeyPayV3Utils.genCallbackChecksumReceived(addition_fee,
					client_id, command, trans_amount, transactionId, version, hash_key_1);
			_log.info("keypay check_sum " + check_sum);

			data.put(KeyPayV3Term.CHECK_SUM, check_sum);

			String endpoint = schema.getString(KeyPayV3Term.KEYPAY_LATE_CREATE_TRANSACTION_ENDPOINT);

			_log.info("keypay endpoint " + endpoint);

			_log.info("keypay data " + data);

			JSONObject response = KeyPayV3Utils.postAPI(endpoint, data);
			_log.info("response " + response.getString(KeyPayV3Term.ERROR));
			_log.info("response " + JSONFactoryUtil.looseSerialize(response));
			JSONObject dataJson = response.getJSONObject(KeyPayV3Term.DATA);
			String qrcode_pay = dataJson.getString(KeyPayV3Term.QRCODE_PAY);
			_log.info("QRCODE_PAY :" + qrcode_pay);
			if (response.has(KeyPayV3Term.ERROR)
					&& KeyPayV3Term.ERROR_0.equals(response.getString(KeyPayV3Term.ERROR))) {
				JSONObject epaymentProfile = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile());
				schema.put(KeyPayV3Term.TRANSACTION_ID, transactionId);
				schema.put(KeyPayV3Term.QRCODE_PAY, qrcode_pay);
				schema.put(KeyPayV3Term.TRANS_AMOUNT, trans_amount);
				schema.put(KeyPayV3Term.ADDITION_FEE, addition_fee);
				epaymentProfile.put(KeyPayTerm.KEYPAY_LATE_CONFIG, schema);
				PaymentFileLocalServiceUtil.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
						epaymentProfile.toJSONString(), serviceContext);
			}
			result = response.toString();

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	
	public File getQrCode(User user, long dossierId, ServiceContext serviceContext, HttpServletRequest request, HttpServletResponse response) {
		
		File outputfile = null;
		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(), dossierId);
			JSONObject data = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
					.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
			String imageStr = data.getString(KeyPayV3Term.QRCODE_PAY);
			// String imageStr = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKoAAACqAQMAAAAHuE bBAAAABlBMVEX///8AAABVwtN+AAAACXBIWXMAAA7EAAAOxAGVKw4bAAA DN0lEQVRIieWWMWokOxCGSyhQYqTUgUBX6Mk0yegqBl9gGqePbg2bmpkLGPYqcm JlewaBgkm72UQLoutVM8a84HlAna6CDj4aofqr6q8C+DuOw1LNWRVMoxf0VYh3cAcW xuUgoCp/AF4liHa8Q4uJ/XLIEd8DaDnGTZge6AqYqcfA83kjzm+SFYEThWyS3ILXKJ8G wvAy0wPTf4L/H0zC5rf+l73ql/M+6nz50rsB0+FJsmjgHziANv4rnQ3YFZtT/x6v+Pu0QM3T LZxWHLVmiygwnlkxlXnRjhkWnthcEF9BBEzD0bbjDoSpclcSKG8dZJzEHaxCyT/PEh757 0kEjr4v7VhaQD+oAvpBMkx8nmM7dtHBSJIKxMsHQKL42vFBBHimvGJG7xD5ArYdd7 YYP0iq7BcsgieQoh3vYkiaBbiaV7UAtetNqm+wpJSYSUWjKZCY9NCHdtwJkYyXDuqD3 COm47gBHxzmCp1L6NnidAX6oxl3AuhCKUoa5wD0xy07bVhaNNnvQsEfy76g7r1txywG PU6E+XRkMdfjAHcwlVPliwoG65Hq2+CM7VhFCmd8J4c6qo9V48FtwRb90RVBhmcFklF jO94h3d3PwdD02YfK/WA34IjpeQ7CIC6WHojTBtxB4G/jBFes6k/gmp3iHbxDJEAGZepAt sX9rTTbsIpkUJPDK17O+2JAsg3YxaKfL4tA/krprhrGDXgtlJ9n8k5QE9hc2c3w2vDBWcoI w/VuUTKeTqUdKwwJaHiJTGVfDJ9us/gb3AHC6F0QSc00a2D4bO4mLG2gZWFXqEfoPd QvEtpxJ0olMW3CCzlENUuP7ZiFYN56LI/6Qf0pOuPZtuN1EfnhybE0J7dPAMMGDCLS6 kWC6Qe2WAR2CXcweSzwy+Su2dON2Uw3TdqwCkJr0uSaX1XEWscTtmOwjpYmavF19 xFIhhc3YIBMRh3FbfdZfasdk7A0u1S5aio5zPz8pXcDps1UP69jFF5OSDb8efc3mLZe80bTn jaOtb7x0+2bcX2ir1knDl3bz2UTTk9jWOup/3ApjacNmKJELzsA4xUVW6WKa8ckLHpqlU cautZlGC+lHf8N5181EFE1IoKbqgAAAABJRU5ErkJggg==";
			if(Validator.isNotNull(imageStr)) {
				String imageDataBytes = imageStr.split(",")[1];

				byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageDataBytes);
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

				// write the image to a file
				outputfile = new File("barcode/tt" + paymentFile.getPaymentFileId() + ".png");

				if (!outputfile.exists()) {
					outputfile.createNewFile();
				}

				ImageIO.write(image, "png", outputfile);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputfile;
	}

	public JSONObject paylaterCallback(User user, ServiceContext serviceContext, String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			if (body != null && body.length() > 0) {
				_log.info("=======body========" + body);
				JSONObject data = JSONFactoryUtil.createJSONObject(body);
				String transactionId = data.getString(KeyPayV3Term.TRANSACTION_ID);
				String dossierId = KeyPayV3Utils.decodeTransactionId(transactionId);
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(Long.parseLong(dossierId));
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(), dossier.getDossierId());
				JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
						.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
				String client_id = schema.getString(KeyPayV3Term.CLIENT_ID);
				String addition_fee = String.valueOf(paymentFile.getShipAmount());
				String trans_amount = String.valueOf(paymentFile.getPaymentAmount());
				String command = schema.getString(KeyPayV3Term.COMMAND_PAYLATER);
				String version = schema.getString(KeyPayV3Term.VERSION);
				String hash_key_1 = schema.getString(KeyPayV3Term.CLIENT_KEY_1);
				String check_sum = KeyPayV3Utils.genCallbackChecksumReceived(addition_fee, client_id, command, trans_amount, transactionId, version, hash_key_1);
				_log.info("Checksum" + check_sum);
				if (check_sum.equals(data.getString(KeyPayV3Term.CHECK_SUM))
						&& KeyPayV3Term.DA_THANH_TOAN.equals(data.getString(KeyPayV3Term.STATUS))) {
					boolean doAction = doActionPP(user, dossier.getGroupId(), dossier, paymentFile, data, serviceContext);
					if (doAction) {
						result.put(KeyPayV3Term.RETURN_CODE, KeyPayV3Term.RETURN_CODE_SUCCESS);
						result.put(KeyPayV3Term.RETURN_MSG, "Thành công");
					} else {
						result.put(KeyPayV3Term.RETURN_CODE, KeyPayV3Term.RETURN_CODE_ERROR);
						result.put(KeyPayV3Term.RETURN_MSG, "Thất bại");
					}
				}
			} else {
				result.put(KeyPayV3Term.RETURN_CODE, KeyPayV3Term.RETURN_CODE_ERROR);
				result.put(KeyPayV3Term.RETURN_MSG, "Thất bại");
			}
		} catch (Exception e) {
			_log.error(e);
			result.put(KeyPayV3Term.RETURN_CODE, KeyPayV3Term.RETURN_CODE_ERROR);
			result.put(KeyPayV3Term.RETURN_MSG, "Thất bại");
		}
		return result;
	}
	private boolean doActionPP(User user, long groupId, Dossier dossier, PaymentFile paymentFile, JSONObject confirmPayload,
							   ServiceContext serviceContext) {

		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
					.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);

			PaymentFileActions actions = new PaymentFileActionsImpl();

			JSONObject action = JSONFactoryUtil.createJSONObject();

			if (dossier.isOnline()) {
				// TODO: call api doaction to DVC
				action = config.getJSONObject(PayGateTerm.ACTION_IS_ONLINE);

				String actionCode = action.getString(PayGateTerm.ACTION_CODE);

				//url doAction
				String url = action.getString(PayGateTerm.URL);

				String username = action.getString(PayGateTerm.USERNAME);

				String pwd = action.getString(PayGateTerm.PWD);

				// Change payment Status = 5
				paymentFile = actions.updateFileConfirm(groupId, dossier.getDossierId(), paymentFile.getReferenceUid(),
						StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_PAY_PLAT_DVCQG,
						confirmPayload.toJSONString(), serviceContext);

				HashMap<String, String> properties = new HashMap<String, String>();

				properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));

				String endPoint = PayGateTerm.buildPathDoAction(url, dossier.getReferenceUid());

				long dossierActionId = dossier.getDossierActionId();

				DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);

				long serviceProcessId = dossierAction.getServiceProcessId();

				String stepCode = dossierAction.getStepCode();

				Map<String, Object> params = new HashMap<String, Object>();

				params.put(PayGateTerm.ACTION_CODE, actionCode);

				JSONObject payment = JSONFactoryUtil.createJSONObject();

				payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
				payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
				payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
				payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
				payment.put(PaymentFileTerm.SHIP_AMOUNT, paymentFile.getShipAmount());
				payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_PAYLATER);
				payment.put(PaymentFileTerm.CONFIRM_PAYLOAD, confirmPayload.toJSONString());
				payment.put(PaymentFileTerm.PAYMENT_REQUEST, 5);
				if (stepCode != null) {

					List<ProcessAction> processActionList = ProcessActionLocalServiceUtil
							.getProcessActionByG_SPID_PRESC(groupId, serviceProcessId, stepCode);

					for (ProcessAction processAction : processActionList) {

						_log.info(processAction.getActionCode());
						_log.info(processAction.getRequestPayment());
						if (processAction.getActionCode().equals(actionCode)) {

							payment = JSONFactoryUtil.createJSONObject();
							payment.put(PaymentFileTerm.PAYMENT_REQUEST, processAction.getRequestPayment());
						}
					}
				}

				params.put(PayGateTerm.PAYMENT, payment.toString());

				_log.info("params============" + params);
				JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
						properties, params, username, pwd);

				_log.info("=====resPostDossier=========" + resPostDossier);

				return true;
			} else {
				paymentFile = actions.updateFileConfirm(groupId, dossier.getDossierId(), paymentFile.getReferenceUid(),
						StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_PAY_PLAT_DVCQG,
						confirmPayload.toJSONString(), serviceContext);

				action = config.getJSONObject(PayGateTerm.ACTION_IS_NOT_ONLINE);

				HashMap<String, String> properties = new HashMap<String, String>();

				properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID)); //124302
//				String endPoint = PayGateTerm.buildPathDoAction("http://192.168.68.78:8080",
//						String.valueOf(dossier.getDossierId()));
				String endPoint = PayGateTerm.buildPathDoAction(action.getString(PayGateTerm.URL),
						String.valueOf(dossier.getDossierId()));
				Map<String, Object> params = new HashMap<String, Object>();

				params.put(PayGateTerm.ACTION_CODE, action.get(PayGateTerm.ACTION_CODE));
				JSONObject payment = JSONFactoryUtil.createJSONObject();
				payment.put(PaymentFileTerm.PAYMENT_REQUEST, 5);
				payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
				payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
				payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
				payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
				payment.put(PaymentFileTerm.SHIP_AMOUNT, paymentFile.getShipAmount());
				payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_PAYLATER);
				payment.put(PaymentFileTerm.CONFIRM_PAYLOAD, confirmPayload.toJSONString());
				params.put(PayGateTerm.PAYMENT, payment.toString());

				_log.info(endPoint);
				JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
						properties, params, action.getString(PayGateTerm.USERNAME), action.getString(PayGateTerm.PWD));

				_log.info("=====resPostDossier=========" + resPostDossier);
				if(resPostDossier.length() >0){
					return true;
				}else{
					return false;
				}
			}

		} catch (Exception e) {
			_log.error(e);
			return false;
		}
	}
	@SuppressWarnings("rawtypes")
	public JSONObject callPostAPI(String httpMethod, String accept, String urlPath, HashMap<String, String> properties,
								  Map<String, Object> params, String username, String password) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(ConstantUtils.VALUE_ACCEPT, accept);

			if (Validator.isNotNull(username) && Validator.isNotNull(password)) {
				String authString = username + StringPool.COLON + password;

				String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

				conn.setRequestProperty(ConstantUtils.VALUE_AUTHORIZATION, ConstantUtils.VALUE_BASIC + authStringEnc);
			}

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append(StringPool.AMPERSAND.charAt(0));
				postData.append(java.net.URLEncoder.encode(param.getKey(), ConstantUtils.UTF_8));
				postData.append(StringPool.EQUAL.charAt(0));
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), ConstantUtils.UTF_8));
			}

			byte[] postDataBytes = postData.toString().getBytes(ConstantUtils.UTF_8);

			conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

			conn.getOutputStream().write(postDataBytes);

			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response = JSONFactoryUtil.createJSONObject(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (IOException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (JSONException e) {
			_log.error("Can't invoke api " + urlPath);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}

		}

		return response;
	}
}
