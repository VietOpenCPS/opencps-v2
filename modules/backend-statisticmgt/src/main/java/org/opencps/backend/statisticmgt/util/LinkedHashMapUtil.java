package org.opencps.backend.statisticmgt.util;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedHashMapUtil {
	public static LinkedHashMap<String, Class<?>> getLinkHashMapUsingRegression (String sqlTemplate){
		LinkedHashMap<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>(); 
		Pattern pattern = Pattern.compile("^select[ ](.*)[ ]from", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sqlTemplate);
	    String select = "";
	    while (matcher.find()) {
	      select = matcher.group();
	    }
	    Pattern pattern1 = Pattern.compile("([a-z]+|[A-Z]+|[0-9]+)(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
	    Matcher matcher1 = pattern1.matcher(select);
	    String select1 = "";
	    while (matcher1.find()) {
	      select1 = matcher1.group();
	      String dataType = select1.substring(select1.lastIndexOf("[")+1, select1.lastIndexOf("]"));
	      String columnName = select1.substring(0,select1.lastIndexOf("["));
	      if(dataType.equals("String")) {
	    		columns.put(columnName, String.class);
	    	} else if (dataType.equals("Long")) {
	    		columns.put(columnName, Long.class);
	    	} else if (dataType.equals("long")) {
	    		columns.put(columnName, long.class);
	    	} else if (dataType.equals("Integer")) {
	    		columns.put(columnName, Integer.class);
	    	} else if (dataType.equals("int")) {
	    		columns.put(columnName, int.class);
	    	} else if (dataType.equals("Double")) {
	    		columns.put(columnName, Double.class);
	    	} else if (dataType.equals("double")) {
	    		columns.put(columnName, double.class);
	    	} else if (dataType.equals("Float")) {
	    		columns.put(columnName, Float.class);
	    	} else if (dataType.equals("float")) {
	    		columns.put(columnName, float.class);
	    	} else if (dataType.equals("Short")) {
	    		columns.put(columnName, Short.class);
	    	} else if (dataType.equals("short")) {
	    		columns.put(columnName, short.class);
	    	} else if (dataType.equals("Boolean")) {
	    		columns.put(columnName, Boolean.class);
	    	} else if (dataType.equals("boolean")) {
	    		columns.put(columnName, boolean.class);
	    	} else if (dataType.equals("Date")) {
	    		columns.put(columnName, Date.class);
	    	} else {
	    		
	    	}
	    }
	    return columns;
	}
	
}
