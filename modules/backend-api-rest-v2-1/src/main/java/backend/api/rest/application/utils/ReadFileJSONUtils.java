package backend.api.rest.application.utils;

import java.io.IOException;

import org.apache.cxf.helpers.IOUtils;
import org.json.simple.parser.ParseException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class ReadFileJSONUtils {

//	private static final String SOURCE_JSON = "../backend-api-rest-v2-1/src/main/resources/";
	private static Log _log = LogFactoryUtil.getLog(ReadFileJSONUtils.class);

//	public static String test(String className) throws FileNotFoundException, IOException, ParseException{
//		 static ddd = new readFileJSON(className);
//		
//		return null;
//	}
	
	public String readFileJSON(String className) throws IOException, ParseException {
		_log.info("====START readFileJSON==== ");
		String result;
//		String userDir = System.getProperty("root.dir");
//		System.out.println("Application plugin running in " + userDir);

//		String sourceFile = SOURCE_JSON + className + ".json";
//		JSONParser parser = new JSONParser();
//		JSONObject jsonData = (JSONObject) parser.parse(new FileReader(sourceFile));
//		if (jsonData != null) {
//			result = jsonData.toJSONString();
//		}
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		result = IOUtils.toString(classLoader.getResourceAsStream("Statistic.json"));
		_log.info("====END readFileJSON==== | result: "+result);
		return result;
	}
}
