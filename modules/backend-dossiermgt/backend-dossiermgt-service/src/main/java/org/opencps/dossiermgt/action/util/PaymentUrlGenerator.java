package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.dossiermgt.action.keypay.util.HashFunction;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

public class PaymentUrlGenerator {

	public static void main(String[] args) {
		System.out.println("PaymentUrlGenerator.main()"+GetterUtil.getBoolean("false"));
		
		String stringToSearch = "http://google.com/dkms?good_code=123123123123&abc=1";
		
		String pattern1 = "good_code=";
		String pattern2 = "&";
		
		String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);
		
		    Pattern p = Pattern.compile(regexString);   // the pattern to search for
		    Matcher m = p.matcher(stringToSearch);

		    // if we find a match, get the group 
		    if (m.find())
		    {
		      // we're only looking for one group, so get it
		      String theGroup = m.group(1);
		      
		      System.out.println("PaymentUrlGenerator.main()"+theGroup);
		    }
		
	    StringBuffer buf = new StringBuffer();
		buf.append("");

		MessageDigest md5 = null;
		byte[] ba = null;
		// create the md5 hash and UTF-8 encode it
		try {
			md5 = MessageDigest.getInstance("SHA-256");
			ba = md5.digest(buf.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			_log.error(e);
		} // wont happen
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String formattedDate = df.format(Calendar.getInstance().getTime());
		System.out.println(formattedDate);
		System.out.println("PaymentUrlGenerator.main()"+ HashFunction.hexShort(ba));
		    
	}
	public static String generatorPayURL(long groupId, long paymentFileId, String pattern,
			long dossierId) throws IOException {

		String result = "";
		try {

			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getPaymentFile(paymentFileId);

			// get dossier
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId,
					dossier.getGovAgencyCode());

			if (Validator.isNotNull(paymentConfig)) {
				List<String> lsMessages = _putPaymentMessage(pattern);

				long merchant_trans_id = _genetatorTransactionId();

				JSONObject epaymentConfigJSON = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig());

				String merchant_code = epaymentConfigJSON.getString("paymentMerchantCode");

				String good_code = generatorGoodCode(10);
				
				String net_cost = String.valueOf(paymentFile.getPaymentAmount());
				
				String ship_fee = "0";
				String tax = "0";

				String bank_code = StringPool.BLANK;

				String service_code = epaymentConfigJSON.getString("paymentServiceCode");
				String version = epaymentConfigJSON.getString("paymentVersion");
				String command = epaymentConfigJSON.getString("paymentCommand");
				String currency_code = epaymentConfigJSON.getString("paymentCurrencyCode");

				String desc_1 = StringPool.BLANK;
				String desc_2 = StringPool.BLANK;
				String desc_3 = StringPool.BLANK;
				String desc_4 = StringPool.BLANK;
				String desc_5 = StringPool.BLANK;

				if (lsMessages.size() > 0) {
					desc_1 = lsMessages.get(0);
					desc_2 = lsMessages.get(1);
					desc_3 = lsMessages.get(2);
					desc_4 = lsMessages.get(3);
					desc_5 = lsMessages.get(4);

					if (desc_1.length() >= 20) {
						desc_1 = desc_1.substring(0, 19);
					}
					if (desc_2.length() >= 30) {
						desc_2 = desc_2.substring(0, 29);
					}
					if (desc_3.length() >= 40) {
						desc_3 = desc_3.substring(0, 39);
					}
					if (desc_4.length() >= 100) {
						desc_4 = desc_4.substring(0, 89);
					}
					if (desc_5.length() > 15) {
						desc_5 = desc_5.substring(0, 15);

						if (!Validator.isDigit(desc_5)) {
							desc_5 = StringPool.BLANK;
						}
					}
				}

				String xml_description = StringPool.BLANK;
				String current_locale = epaymentConfigJSON.getString("paymentCurrentLocale");
				String country_code = epaymentConfigJSON.getString("paymentCountryCode");
				String internal_bank = epaymentConfigJSON.getString("paymentInternalBank");

				String merchant_secure_key = epaymentConfigJSON.getString("paymentMerchantSecureKey");

				// dossier = _getDossier(dossierId);

				// TODO : update returnURL keyPay

				String return_url;
				_log.info("SONDT GENURL paymentReturnUrl ====================== "+ JSONFactoryUtil.looseSerialize(epaymentConfigJSON));
//				return_url = epaymentConfigJSON.getString("paymentReturnUrl")+ "/" + dossier.getReferenceUid() + "/" + paymentFile.getReferenceUid();
				return_url = epaymentConfigJSON.getString("paymentReturnUrl")+ "&dossierId=" + dossier.getDossierId() + "&goodCode=" + good_code + "&transId=" + merchant_trans_id + "&referenceUid=" + dossier.getReferenceUid();
				_log.info("SONDT GENURL paymentReturnUrl ====================== "+ return_url);
				// http://119.17.200.66:2681/web/bo-van-hoa/dich-vu-cong/#/thanh-toan-thanh-cong?paymentPortal=KEYPAY&dossierId=77603&goodCode=123&transId=555
				KeyPay keypay = new KeyPay(String.valueOf(merchant_trans_id), merchant_code, good_code, net_cost,
						ship_fee, tax, bank_code, service_code, version, command, currency_code, desc_1, desc_2, desc_3,
						desc_4, desc_5, xml_description, current_locale, country_code, return_url, internal_bank,
						merchant_secure_key);

				// keypay.setKeypay_url(paymentConfig.getKeypayDomain());

				StringBuffer param = new StringBuffer();
				param.append("merchant_code=").append(URLEncoder.encode(keypay.getMerchant_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("merchant_secure_key=").append(URLEncoder.encode(keypay.getMerchant_secure_key(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("bank_code=").append(URLEncoder.encode(keypay.getBank_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("internal_bank=").append(URLEncoder.encode(keypay.getInternal_bank(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("merchant_trans_id=").append(URLEncoder.encode(keypay.getMerchant_trans_id(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("good_code=").append(URLEncoder.encode(keypay.getGood_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("net_cost=").append(URLEncoder.encode(keypay.getNet_cost(), "UTF-8") )
						.append(StringPool.AMPERSAND);
				param.append("ship_fee=").append(URLEncoder.encode(keypay.getShip_fee(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("tax=").append(URLEncoder.encode(keypay.getTax(), "UTF-8")).append(StringPool.AMPERSAND);
				param.append("return_url=").append(URLEncoder.encode(keypay.getReturn_url(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("version=").append(URLEncoder.encode(keypay.getVersion(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("command=").append(URLEncoder.encode(keypay.getCommand(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("current_locale=").append(URLEncoder.encode(keypay.getCurrent_locale(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("currency_code=").append(URLEncoder.encode(keypay.getCurrency_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("service_code=").append(URLEncoder.encode(keypay.getService_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("country_code=").append(URLEncoder.encode(keypay.getCountry_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);

				param.append("desc_1=").append(URLEncoder.encode(keypay.getDesc_1(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_2=").append(URLEncoder.encode(keypay.getDesc_2(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_3=").append(URLEncoder.encode(keypay.getDesc_3(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_4=").append(URLEncoder.encode(keypay.getDesc_4(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_5=").append(URLEncoder.encode(keypay.getDesc_5(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("xml_description=").append(URLEncoder.encode(keypay.getXml_description(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("secure_hash=").append(keypay.getSecure_hash());

				result = epaymentConfigJSON.getString("paymentKeypayDomain") + StringPool.QUESTION + param.toString();

			}

		} catch (NoSuchPaymentFileException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	/**
	 * @param dossierId
	 * @return
	 */
//	private static Dossier _getDossier(long dossierId) {
//
//		Dossier dossier = null;
//
//		try {
//			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		return dossier;
//	}

	private static List<String> _putPaymentMessage(String pattern) {

		List<String> lsDesc = new ArrayList<String>();

		lsDesc.add(0, StringPool.BLANK);
		lsDesc.add(1, StringPool.BLANK);
		lsDesc.add(2, StringPool.BLANK);
		lsDesc.add(3, StringPool.BLANK);
		lsDesc.add(4, StringPool.BLANK);

		List<String> lsMsg = DossierPaymentUtils.getMessagePayment(pattern);

		for (int i = 0; i < lsMsg.size(); i++) {
			lsDesc.set(1, lsMsg.get(i));
		}

		return lsDesc;
	}

	/**
	 * Generator PaymentFile
	 * 
	 * @param paymentFile
	 * @return
	 */
	private static long _genetatorTransactionId() {

		long transactionId = 0;
		try {
			transactionId = CounterLocalServiceUtil.increment(PaymentFile.class.getName() + ".genetatorTransactionId");
		} catch (SystemException e) {
			_log.error(e);
		}
		return transactionId;
	}

	/**
	 * Get paymentFile by id
	 * 
	 * @param paymentFileId
	 * @return
	 */
//	private static PaymentFile _getPaymentFileById(long paymentFileId) {
//
//		PaymentFile paymentFile = null;
//
//		try {
//			paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(paymentFileId);
//		} catch (Exception e) {
//			_log.error(e);
//			paymentFile = null;
//		}
//
//		return paymentFile;
//	}

	/**
	 * @param groupId
	 * @param govAgencyOrganizationId
	 * @return
	 */
//	private static PaymentConfig _getPaymentConfig(long groupId, String govAgencyCode) {
//
//		PaymentConfig paymentConfig = null;
//
//		try {
//			paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId, govAgencyCode);
//		} catch (Exception e) {
//			_log.error(e);
//			paymentConfig = null;
//		}
//
//		return paymentConfig;
//	}

	/**
	 * @param length
	 * @return
	 */
	public static String generatorGoodCode(int length) {

		String tempGoodCode = _generatorUniqueString(length);

		String goodCode;

		while (_checkContainsGoodCode(tempGoodCode)) {
			tempGoodCode = _generatorUniqueString(length);
		}

		/*
		 * while(_testCheck(tempGoodCode)) { tempGoodCode =
		 * _generatorUniqueString(length); }
		 */
		goodCode = tempGoodCode;

		return goodCode;
	}

	@SuppressWarnings("unused")
	private static boolean _testCheck(String keyCode) {

		boolean isContains = false;

		List<String> ls = new ArrayList<String>();

		ls.add("0");
		ls.add("1");
		ls.add("2");
		ls.add("3");
		ls.add("4");
		ls.add("5");
		ls.add("6");
		ls.add("7");
		ls.add("9");

		if (ls.contains(keyCode)) {
			isContains = true;
		}

		return isContains;
	}

	/**
	 * @param keypayGoodCode
	 * @return
	 */
	private static boolean _checkContainsGoodCode(String keypayGoodCode) {

		boolean isContains = false;

		try {
			PaymentFile paymentFile = null;//PaymentFileLocalServiceUtil.getByGoodCode(keypayGoodCode);

			if (Validator.isNotNull(paymentFile)) {
				isContains = true;
			}
		} catch (Exception e) {
			_log.error(e);
			isContains = true;
		}

		return isContains;

	}

	/**
	 * @param pattern
	 * @param lenght
	 * @return
	 */
	private static String _generatorUniqueString(int lenght) {

		char[] chars = "0123456789".toCharArray();

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < lenght; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();

	}

	private static Log _log = LogFactoryUtil.getLog(PaymentUrlGenerator.class);
}