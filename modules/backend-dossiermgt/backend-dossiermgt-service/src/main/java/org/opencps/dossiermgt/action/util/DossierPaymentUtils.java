package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

public class DossierPaymentUtils {

	public static void main(String[] args) {
		String pattern = "bank cash keypay net=[ payment = 100000]   ship=0 tax=0  $Lệ phí đánh giá COP $";

		Pattern patternName = null;
		Matcher matcherName = null;

		ScriptEngineManager manager;

		ScriptEngine engine;

		patternName = Pattern.compile("net=\\[(.*?)\\]");

		matcherName = patternName.matcher(pattern);

		if (matcherName.find()) {

			manager = new ScriptEngineManager();

			engine = manager.getEngineByExtension("js");

			List<ScriptEngineFactory> factories = manager.getEngineFactories();

			for (ScriptEngineFactory ft : factories) {
				_log.info("EXTENTISION____" + ft.getExtensions());
				_log.info("NAME__" + ft.getEngineName());
				_log.info("NAMES___" + ft.getNames());
			}

//			for (ScriptEngineFactory se : new ScriptEngineManager().getEngineFactories()) {
//				System.out.println("se = " + se.getEngineName());
//				System.out.println("se = " + se.getEngineVersion());
//				System.out.println("se = " + se.getLanguageName());
//				System.out.println("se = " + se.getLanguageVersion());
//				System.out.println("se = " + se.getNames());
//				System.out.println("se = " + se.getExtensions());
//			}

			String netScript = matcherName.group(1);

			try {

				engine.eval(netScript);

//				long net = GetterUtil.getInteger(engine.get("payment"));
//				System.out.println("DossierPaymentUtils.main()" + net);
			} catch (ScriptException e) {
//				e.printStackTrace();
				_log.error(e);
			}

		}

	}

	// call processPaymentFile create paymentFile
	public static void processPaymentFile(ProcessAction processAction, String pattern, long groupId, long dossierId, long userId,
			ServiceContext serviceContext, String serverNo) throws JSONException {

		// get total payment amount
		JSONObject patternObj = JSONFactoryUtil.createJSONObject(pattern);
		
//		int payment = getTotalPayment(pattern, dossierId, userId, serviceContext);

		// get PaymentFee
//		List<String> messages = getMessagePayment(pattern);

		String paymentNote = StringPool.BLANK;

//		if (messages.size() > 0) {
//			paymentFee = messages.get(0);
//		}

		// create paymentFile
		PaymentFileActions actions = new PaymentFileActionsImpl();

		// get dossier
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId,
				dossier.getGovAgencyCode());
		String paymentFee = StringPool.BLANK;
		long advanceAmount = 0;
		
		if (patternObj.has(PaymentFileTerm.PAYMENT_FEE)) {
			paymentFee = patternObj.getString(PaymentFileTerm.PAYMENT_FEE);			
		}
		if (patternObj.has(PaymentFileTerm.ADVANCE_AMOUNT)) {
			advanceAmount = patternObj.getLong(PaymentFileTerm.ADVANCE_AMOUNT);
		}
		
		try {

			// generator epaymentProfile
			JSONObject epaymentConfigJSON = paymentConfig != null ? JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig()) : JSONFactoryUtil.createJSONObject();
			
			PaymentFile paymentFile = actions.createPaymentFile(
					userId, 
					groupId, 
					dossierId, 
					null, 
					paymentFee, 
					advanceAmount, 
					0, 
					0, 
					0, 
					0, 
					paymentNote, 
					StringPool.BLANK, 
					StringPool.BLANK, 
					processAction.getRequestPayment(), 
					StringPool.BLANK, 
					serviceContext);

			
//			PaymentFile paymentFile = actions.createPaymentFile(userId, groupId, dossierId, null,
//					dossier.getGovAgencyCode(), dossier.getGovAgencyName(), dossier.getApplicantName(),
//					dossier.getApplicantIdNo(), paymentFee, payment, paymentNote, null, paymentConfig.getBankInfo(),
//					serviceContext);
//			
			long counterPaymentFile = CounterLocalServiceUtil.increment(PaymentFile.class.getName()+"paymentFileNo");
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(new Date());
			
			int prefix = cal.get(Calendar.YEAR);
			
			String invoiceTemplateNo = Integer.toString(prefix) + String.format("%010d", counterPaymentFile);
			
			paymentFile.setInvoiceTemplateNo(invoiceTemplateNo);
			
			PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
			
			JSONObject epaymentProfileJSON = JSONFactoryUtil.createJSONObject();

			if (epaymentConfigJSON.has("paymentKeypayDomain")) {

				try {
					String generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId,
							paymentFile.getPaymentFileId(), pattern, dossierId);

					epaymentProfileJSON.put("keypayUrl", generatorPayURL);

					// fill good_code to keypayGoodCode
					String pattern1 = "good_code=";
					String pattern2 = "&";

					String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);

					Pattern p = Pattern.compile(regexString);
					Matcher m = p.matcher(generatorPayURL);

					if (m.find()) {
						String goodCode = m.group(1);

						epaymentProfileJSON.put("keypayGoodCode", goodCode);
					} else {
						epaymentProfileJSON.put("keypayGoodCode", StringPool.BLANK);
					}

					epaymentProfileJSON.put("keypayMerchantCode", epaymentConfigJSON.get("paymentMerchantCode"));

					actions.updateEProfile(dossierId, paymentFile.getReferenceUid(), epaymentProfileJSON.toJSONString(),
							serviceContext);

				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					_log.error(e);
				}

			}
//
//			// Create paymentfile sync
//			if (Validator.isNotNull(serverNo)) {
////				DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, dossier.getReferenceUid(),
////						false, 2, paymentFile.getPrimaryKey(), paymentFile.getReferenceUid(), serverNo);
//			}

		} catch (PortalException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			_log.error(e);
		}
	}

	/**
	 * @param pattern
	 * @param dossierId
	 * @return
	 * @throws JSONException
	 */
	public static int getTotalPayment(String pattern, long dossierId, long userId, ServiceContext serviceContext)
			throws JSONException {

		int total = 0;

		if (_validatorPattern(pattern)) {

			int net = 0;

			Pattern patternName = Pattern.compile("net=\\[(.*?)\\]");

			Matcher matcherName = patternName.matcher(pattern);

			if (matcherName.find()) {

				net = _getTotalDossierPayment(patternName, matcherName, pattern, dossierId, serviceContext);

			} else {

				net = _getAmount(pattern, PAY_AMOUNT_NET);

			}

			int ship = _getAmount(pattern, PAY_AMOUNT_SHIP);

			int tax = _getAmount(pattern, PAY_AMOUNT_TAX);

			total = net + ship + tax;

		}

		return total;
	}

	/**
	 * @param pattern
	 * @return
	 */
	public static int getTotalPayment(String pattern) {

		int total = 0;

		if (_validatorPattern(pattern)) {
			int net = _getAmount(pattern, PAY_AMOUNT_NET);

			int ship = _getAmount(pattern, PAY_AMOUNT_SHIP);

			int tax = _getAmount(pattern, PAY_AMOUNT_TAX);

			total = net + ship + tax;
		}

		return total;
	}

	/**
	 * @param pattern
	 * @return
	 */
	public static List<String> getPaymentMethod(String pattern) {

		List<String> paymentMethods = new ArrayList<String>();

		if (_validatorPattern(pattern)) {
			if (_checkcontains(pattern, PAY_METHOD_BANK)) {
				paymentMethods.add(PAY_METHOD_BANK);
			}

			if (_checkcontains(pattern, PAY_METHOD_CASH)) {
				paymentMethods.add(PAY_METHOD_CASH);
			}

			if (_checkcontains(pattern, PAY_METHOD_KEYPAY)) {
				paymentMethods.add(PAY_METHOD_KEYPAY);
			}

		}

		return paymentMethods;

	}

	/**
	 * Get payment message
	 * 
	 * @param pattern
	 * @return
	 */
	public static List<String> getMessagePayment(String pattern) {

		List<String> msgPayments = new ArrayList<String>();
		try {
			pattern = pattern.substring(pattern.indexOf(PAY_MESSAGE), pattern.lastIndexOf(PAY_MESSAGE) + 1);

			String[] splitPattern = StringUtil.split(pattern, PAY_MESSAGE);

			for (String element : splitPattern) {
				if (!element.trim().contentEquals("")) {
					msgPayments.add(StringUtil.trimLeading(element));
				}
			}
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		return msgPayments;
	}

	private static boolean _validatorPattern(String pattern) {

		return true;
	}

	/**
	 * @param pattern
	 * @param content
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static boolean _checkcontains(String pattern, String content) {

		boolean isContains = false;

		String[] splitPattern = StringUtil.split(pattern, StringPool.SPACE);

		for (String element : splitPattern) {
			if (Validator.equals(element, content)) {
				isContains = true;
				break;

			}
		}

		return isContains;
	}

	/**
	 * @param pattern
	 * @param content
	 * @return
	 */
	private static int _getAmount(String pattern, String content) {

		int amount = 0;

		String[] splitPattern = StringUtil.split(pattern, StringPool.SPACE);

		for (String element : splitPattern) {

			if (element.contains(content)) {

				String[] splipElement = StringUtil.split(element, StringPool.EQUAL);

				if (splipElement.length == 2) {
					int tempAmount = GetterUtil.getInteger(splipElement[1], 0);

					if (tempAmount < 0) {
						tempAmount = 0;
					}

					amount = tempAmount;
				}

				break;

			}

		}

		return amount;

	}

	public static int _getTotalDossierPayment(Pattern patternName, Matcher matcherName, String pattern, long dossierId,
			ServiceContext serviceContext) throws JSONException {

		_log.info("patternName" + patternName);
		_log.info("matcherName" + matcherName);
		_log.info("pattern" + pattern);
		_log.info("dossierId" + dossierId);

		int net = 0;

		patternName = Pattern.compile("#(.*?)@(.*?) ");

		matcherName = patternName.matcher(pattern);

		List<String> listSTR = new ArrayList<String>();

		while (matcherName.find()) {

			listSTR.add(matcherName.group(0));

		}

		listSTR = new ArrayList<String>(new LinkedHashSet<String>(listSTR));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String string : listSTR) {

			jsonObject.put(string.trim(), string.trim());

		}
		if (listSTR.size() != 0) {
			String result = AutoFillFormData.sampleDataBinding(jsonObject.toString(), dossierId, serviceContext);

			jsonObject = JSONFactoryUtil.createJSONObject(result);

			Map<String, Object> jsonMap = AutoFillFormData.jsonToMap(jsonObject);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				String valReplace;

				if (Validator.isNumber(String.valueOf(entry.getValue()))) {

					valReplace = String.valueOf(entry.getValue());

				} else {

					valReplace = "'" + String.valueOf(entry.getValue()) + "'";

				}
				pattern = pattern.replaceAll(entry.getKey(), valReplace);
			}

		}

		// ScriptEngineManager manager = new ScriptEngineManager();

		// ScriptEngine engine = manager.getEngineByExtension("js");

		patternName = Pattern.compile("net=\\[(.*?)\\]");

		matcherName = patternName.matcher(pattern);

		if (matcherName.find()) {

			ScriptEngineManager manager = new ScriptEngineManager(null);

			ScriptEngine engine = manager.getEngineByExtension("js");

			String netScript = matcherName.group(1);

			/*
			 * List<ScriptEngineFactory> factories = new
			 * ScriptEngineManager(null).getEngineFactories();
			 * 
			 * if (factories.size() == 0) { _log.info("NULL ME NO ROI"); }
			 * 
			 * for (ScriptEngineFactory ft : factories) {
			 * _log.info("EXTENTISION____" + ft.getExtensions());
			 * _log.info("NAME__" + ft.getEngineName()); _log.info("NAMES___" +
			 * ft.getNames()); }
			 */

			_log.info("NETSCRIPT______" + netScript);

			try {

				_log.info("engine_1" + engine);
				_log.info("maneger" + manager);

				engine.eval(netScript);
				_log.info("engine_2" + engine);

				net = GetterUtil.getInteger(engine.get("payment"));

				_log.info("net__________" + net);

			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}

		}
		return net;
	}

	static Log _log = LogFactoryUtil.getLog(DossierPaymentUtils.class);

	public static final String PAY_METHOD_BANK = "bank";
	public static final String PAY_METHOD_KEYPAY = "keypay";
	public static final String PAY_METHOD_CASH = "cash";
	public static final String PAY_AMOUNT_NET = "net";
	public static final String PAY_AMOUNT_SHIP = "ship";
	public static final String PAY_AMOUNT_TAX = "tax";
	public static final String PAY_MESSAGE = "$";
}