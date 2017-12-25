package org.opencps.dossiermgt.action.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class PaymentUrlGenerator {

	public static void main(String[] args) {

	}

	public static String generatorPayURL(long groupId, long paymentFileId, String pattern,
			long dossierId, String keypayMerchantCode) throws IOException {

		String result = "";
		try {

			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getPaymentFile(paymentFileId);

			// get dossier
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId,
					dossier.getGovAgencyCode());

			if (Validator.isNotNull(paymentConfig)) {
				List<String> lsMessages = _putPaymentMessage(pattern);

				long merchant_trans_id = _genetatorTransactionId(keypayMerchantCode);

				JSONObject epaymentConfigJSON = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig());

				String merchant_code = epaymentConfigJSON.getString("paymentMerchantCode");

				String good_code = generatorGoodCode(10);

				String net_cost = String.valueOf((int) paymentFile.getPaymentAmount());
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

				String return_url = StringPool.BLANK;
				return_url = epaymentConfigJSON.getString("paymentReturnUrl");

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
				param.append("net_cost=").append(URLEncoder.encode(keypay.getNet_cost(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("ship_fee=").append(URLEncoder.encode(keypay.getShip_fee(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("tax=").append(URLEncoder.encode(keypay.getTax(), "UTF-8")).append(StringPool.AMPERSAND);
				param.append("return_url=").append(URLEncoder.encode(keypay.getReturn_url()+ "/" + dossierId + "/" + paymentFileId, "UTF-8"))
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

				param.append("desc_1=&").append(URLEncoder.encode(desc_1, "UTF-8"))
				.append(StringPool.AMPERSAND);
				param.append("desc_2=&").append(URLEncoder.encode(desc_2, "UTF-8"))
				.append(StringPool.AMPERSAND);
				param.append("desc_3=&").append(URLEncoder.encode(desc_3, "UTF-8"))
				.append(StringPool.AMPERSAND);
				param.append("desc_4=&").append(URLEncoder.encode(desc_4, "UTF-8"))
				.append(StringPool.AMPERSAND);
				param.append("desc_5=&").append(URLEncoder.encode(desc_5, "UTF-8"))
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
	private static Dossier _getDossier(long dossierId) {

		Dossier dossier = null;

		try {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		} catch (Exception e) {
			_log.error(e);
		}

		return dossier;
	}

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
	private static long _genetatorTransactionId(String keypayMerchantCode) {

		long transactionId = 0;
		try {
			transactionId = Long.valueOf(keypayMerchantCode);
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
	private static PaymentFile _getPaymentFileById(long paymentFileId) {

		PaymentFile paymentFile = null;

		try {
			paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(paymentFileId);
		} catch (Exception e) {
			paymentFile = null;
		}

		return paymentFile;
	}

	/**
	 * @param groupId
	 * @param govAgencyOrganizationId
	 * @return
	 */
	private static PaymentConfig _getPaymentConfig(long groupId, String govAgencyCode) {

		PaymentConfig paymentConfig = null;

		try {
			paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId, govAgencyCode);
		} catch (Exception e) {
			paymentConfig = null;
		}

		return paymentConfig;
	}

	/**
	 * @param length
	 * @return
	 */
	public static String generatorGoodCode(int length) {

		String tempGoodCode = _generatorUniqueString(length);

		String goodCode = StringPool.BLANK;

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
			// TODO
			PaymentFile paymentFile = null;//PaymentFileLocalServiceUtil.getByGoodCode(keypayGoodCode);

			if (Validator.isNotNull(paymentFile)) {
				isContains = true;
			}
		} catch (Exception e) {
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