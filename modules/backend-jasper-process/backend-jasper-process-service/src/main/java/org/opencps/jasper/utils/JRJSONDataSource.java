package org.opencps.jasper.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class JRJSONDataSource extends JsonDataSource {

	public JRJSONDataSource(InputStream stream) throws JRException, IOException {
		super(stream);
		if (stream != null) {
			stream.close();
		}

	}

	public JRJSONDataSource(InputStream jsonStream, String selectExpression) throws JRException, IOException {
		super(jsonStream, selectExpression);
		if (jsonStream != null) {
			jsonStream.close();
		}
	}

	public static JRJSONDataSource getInstance(String json) throws JRException, IOException {

		InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

		return new JRJSONDataSource(stream);
	}

	public static JRJSONDataSource getInstance(InputStream stream) throws JRException, IOException {

		return new JRJSONDataSource(stream);
	}

	public static JRJSONDataSource getInstance(InputStream jsonStream, String selectExpression)
			throws JRException, IOException {

		return new JRJSONDataSource(jsonStream, selectExpression);
	}
}