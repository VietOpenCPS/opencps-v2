package org.opencps.backend.statisticmgt.util;

import java.util.Date;
import java.util.LinkedHashMap;

public class LinkedHashMapUtil {
	public static LinkedHashMap<String, Class<?>> getLinkHashMap (String sqlTemplate){
		int indexFrom = sqlTemplate.indexOf("FROM");
	    sqlTemplate = sqlTemplate.substring(0,indexFrom);
	    sqlTemplate = sqlTemplate.substring(6);
	    sqlTemplate = sqlTemplate.trim();
	    String[] objects = sqlTemplate.split(",");
	    LinkedHashMap<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>(); 
	    for(String object : objects) {
	    	int indexDot = object.lastIndexOf(".") + 1;
	    	object = object.substring(indexDot);
	    	String dataType = object.substring(object.lastIndexOf("[")+1, object.lastIndexOf("]"));
	    	String columnName = object.substring(0,object.lastIndexOf("["));
	    	if(object.contains(" ")){
	    		object = object.replace(" ", "");
	    		columnName = object.substring(object.lastIndexOf("]")+1);
	    	}
	    	if(dataType.equals("String")) {
	    		columns.put(columnName, String.class);
	    	} else if (dataType.equals("Integer")) {
	    		columns.put(columnName, Integer.class);
	    	} else if (dataType.equals("Date")) {
	    		columns.put(columnName, Date.class);
	    	}
	    }
		return columns;
	}
}
