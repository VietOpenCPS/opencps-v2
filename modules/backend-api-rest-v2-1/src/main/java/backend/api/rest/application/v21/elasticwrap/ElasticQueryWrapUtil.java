//package backend.api.rest.application.v21.elasticwrap;
//
//import static org.toilelibre.libe.curl.Curl.curl;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpResponse;
//
//import com.liferay.portal.kernel.json.JSONFactoryUtil;
//import com.liferay.portal.kernel.json.JSONObject;
//
//public class ElasticQueryWrapUtil {
//
//	public JSONObject query(String q, String className, long conpanyId) {
//		
//		JSONObject object = JSONFactoryUtil.createJSONObject();
//		
//		String url = "http://localhost:9200/liferay-20116/LiferayDocumentType/_search?q=entryClassName:" + className;
//		
//		String data = curl().k().$("http://localhost:8080/o/rest/v2_1/actionconfig/_search?q=1dsfsdfsdfsfsfs");
//		HttpResponse response = curl().k().run("https://localhost:8443/public/json");
//		System.out.println("ElasticQueryWrapUtil.query()" + response);
//		return object;
//		
//	}
//	
//	public static void main(String[] args) {
//		System.out.println("ElasticQueryWrapUtil.main()");
//		System.out.println(curl().k().$("http://localhost:9200/liferay-20116/LiferayDocumentType/_search -d '{ \"query\": { \"query_string\": { \"query\" : \"(entryClassName:org.opencps.communication.model.Notificationtemplate)\" }}}'"));
//		
//		Process process = null;
//		
//		List list = new ArrayList();
//	    list.add("curl");
//	    list.add("-s");
//	    list.add("-X");
//	    list.add("POST");
//	    list.add("http://localhost:9200/liferay-20116/LiferayDocumentType/_search");
//	    list.add("-d");
//	    list.add("{ \"query\": { \"query_string\": { \"query\" : \"(entryClassName:org.opencps.communication.model.Notificationtemplate)\" }}}");
//	    
////	    list.add("http://localhost:9200/_all/_search");
////	    list.add("-d");
////	    list.add("{ \"query\": { \"query_string\": { \"query\" : \"(entryClassName:com.liferay.configuration.admin.web.internal.model.ConfigurationModel)\" }}}");
//	    ProcessBuilder pb = new ProcessBuilder(list);
//	    try {
//	        pb.redirectErrorStream();
//	        process = pb.start();
//	        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//	        String result="";
//	        String line=null;
//	        while((line=input.readLine())!=null) {
//	            result+=line;
//	        }
//            System.out.println(result);
//	        process.destroy();
//	    }
//	    catch (IOException e) {
//	        process.destroy();
//	    } finally {
//	    	 process.destroy();
//		}
//	}
//}
