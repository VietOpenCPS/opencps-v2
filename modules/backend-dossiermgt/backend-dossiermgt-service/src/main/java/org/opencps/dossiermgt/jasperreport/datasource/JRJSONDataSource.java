///**
//* OpenCPS is the open source Core Public Services software
//* Copyright (C) 2016-present OpenCPS community
//
//* This program is free software: you can redistribute it and/or modify
//* it under the terms of the GNU Affero General Public License as published by
//* the Free Software Foundation, either version 3 of the License, or
//* any later version.
//
//* This program is distributed in the hope that it will be useful,
//* but WITHOUT ANY WARRANTY; without even the implied warranty of
//* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//* GNU Affero General Public License for more details.
//* You should have received a copy of the GNU Affero General Public License
//* along with this program. If not, see <http://www.gnu.org/licenses/>
//*/
//
//package org.opencps.dossiermgt.jasperreport.datasource;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.data.JsonDataSource;
//
///**
// * @author trungnt
// *
// */
//public class JRJSONDataSource extends JsonDataSource {
//
//	public JRJSONDataSource(InputStream stream)
//	    throws JRException, IOException {
//		super(
//		    stream);
//		if(stream != null){
//			stream.close();
//		}
//
//	}
//
//	public JRJSONDataSource(InputStream jsonStream, String selectExpression)
//	    throws JRException, IOException {
//		super(
//		    jsonStream, selectExpression);
//		if(jsonStream != null){
//			jsonStream.close();
//		}
//	}
//
//	public static JRJSONDataSource getInstance(String json)
//	    throws JRException, IOException {
//
//		InputStream stream = new ByteArrayInputStream(json
//		    .getBytes(StandardCharsets.UTF_8));
//		
//		return new JRJSONDataSource(stream);
//	}
//
//	public static JRJSONDataSource getInstance(InputStream stream)
//	    throws JRException, IOException {
//
//		return new JRJSONDataSource(stream);
//	}
//
//	public static JRJSONDataSource getInstance(
//	    InputStream jsonStream, String selectExpression)
//	    throws JRException, IOException {
//
//		return new JRJSONDataSource(jsonStream, selectExpression);
//	}
//}
