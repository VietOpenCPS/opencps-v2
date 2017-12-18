package org.opencps.dossiermgt.action.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierPaymentUtils {

	public static void main(String[] args) {
		String pattern = "net=100 ship=300 tax=30 bank cash keypay $Thanh toan cho tao cai nay nhe$ $da thanh toan$ $thanh toan di$  $thanh toan di$";

		int totalPayment = getTotalPayment(pattern);

		List<String> paymentMethod = getPaymentMethod(pattern);

		System.out.println(totalPayment);

		for (String method : paymentMethod) {
			System.out.println(method);
		}

		List<String> messages = getMessagePayment(pattern);

		for (String msg : messages) {
			System.out.println(msg);
		}
	}

	/**
	 * @param pattern
	 * @param dossierId
	 * @return
	 * @throws JSONException
	 */
	public static int getTotalPayment(String pattern, long dossierId) throws JSONException {

		int total = 0;

		if (_validatorPattern(pattern)) {

			int net = 0;

			Pattern patternName = Pattern.compile("net=\\[(.*?)\\]");

			Matcher matcherName = patternName.matcher(pattern);

			if (matcherName.find()) {

				net = _getTotalDossierPayment(patternName, matcherName, pattern, dossierId);

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
	
	public static int _getTotalDossierPayment(Pattern patternName,
			Matcher matcherName, String pattern, long dossierId) throws JSONException {

		int net = 0;
		
		patternName = Pattern.compile("#(.*?)@(.*?) ");
        
   	 	matcherName = patternName.matcher(pattern);
	        
	   	List<String> listSTR = new ArrayList<String>();
		       
	   	while(matcherName.find()){
		        	
	   		listSTR.add(matcherName.group(0));
		     
	   	}
		        
	   	listSTR = new ArrayList<String>(new LinkedHashSet<String>(listSTR));
		        
	   	JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		        
	   	for (String string : listSTR) {
		        	
	   		jsonObject.put(string.trim(), string.trim());
				
	   	}
		        
	   	String result = AutoFillFormData.dataBinding(jsonObject.toString(), null, null, dossierId);
		        
		jsonObject = JSONFactoryUtil.createJSONObject(result);
		        
		Map<String, Object> jsonMap = AutoFillFormData.jsonToMap(jsonObject);
		        
		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			String valReplace = StringPool.BLANK;
			
			if(Validator.isNumber(String.valueOf(entry.getValue()))){
				
				valReplace = String.valueOf(entry.getValue());
				
		    }else{
		    	
		    	valReplace = "'"+String.valueOf(entry.getValue())+"'";
		    	
		    }
		    pattern = pattern.replaceAll(entry.getKey(), valReplace);
		}
		        
		ScriptEngineManager manager = new ScriptEngineManager();
			    
		ScriptEngine engine = manager.getEngineByExtension("js");
				
		patternName = Pattern.compile("net=\\[(.*?)\\]");
		       
		matcherName = patternName.matcher(pattern);
			    
		if(matcherName.find()){
		        	
		   manager = new ScriptEngineManager();
		    	    
		   engine = manager.getEngineByExtension("js");
		    	   
		   String netScript = matcherName.group(1);
		    		
		   try {
						
		    	engine.eval(netScript);
						
				 net = GetterUtil.getInteger(engine.get("payment"));
						
		   } catch (ScriptException e) {
				
		   }
		    		    
		}
		return net;
	}
	
	public static final String PAY_METHOD_BANK = "bank";
	public static final String PAY_METHOD_KEYPAY = "keypay";
	public static final String PAY_METHOD_CASH = "cash";
	public static final String PAY_AMOUNT_NET = "net";
	public static final String PAY_AMOUNT_SHIP = "ship";
	public static final String PAY_AMOUNT_TAX = "tax";
	public static final String PAY_MESSAGE = "$";
}
