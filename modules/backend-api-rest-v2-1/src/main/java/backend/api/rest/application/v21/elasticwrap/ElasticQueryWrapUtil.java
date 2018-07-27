package backend.api.rest.application.v21.elasticwrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.ws.rs.HttpMethod;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class ElasticQueryWrapUtil {
	private static final String ELASTIC_SERVER_API_URL = "http://localhost:9200/liferay-20116/LiferayDocumentType/_search";
	private static final String MULTIPLE_ELASTIC_SERVER_API_URL = "http://localhost:9200/liferay-20116/LiferayDocumentType/_msearch";
	
	public static JSONObject query(String q, String className, long conpanyId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
//			Process process = null;
//
//			List<String> list = new ArrayList<String>();
//			list.add("curl");
//			list.add("-s");
//			list.add("-X");
//			list.add("GET");
//			list.add("http://localhost:9200/liferay-20116/LiferayDocumentType/_search");
//			list.add("-d");
//			list.add(q);
//
//			ProcessBuilder pb = new ProcessBuilder(list);
//
//			System.out.println(pb.command());
//			for (String string : pb.command()) {
//				System.out.println("string: "+string);
//			}
//			String data = StringPool.BLANK;
//
//			try {
//
//				pb.redirectErrorStream();
//				process = pb.start();
//				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//				String line = null;
//
//				while ((line = input.readLine()) != null) {
//					data += line;
//				}
//				process.destroy();
//
//			} catch (IOException e) {
//				process = null;
//				process.destroy();
//			} finally {
//				process.destroy();
//			}

			URL url = new URL(ELASTIC_SERVER_API_URL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(HttpMethod.GET);
			conn.setRequestProperty("Accept", "Content-Type: application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoInput(true);
			conn.setDoOutput(true);
	        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
	        osw.write(q);
	        osw.flush();
	        osw.close();
	        
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			result = JSONFactoryUtil.createJSONObject(sb.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static JSONObject queryMultiple(String q, String className, long conpanyId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
//			Process process = null;
//
//			List<String> list = new ArrayList<String>();
//			list.add("curl");
//			list.add("-s");
//			list.add("-X");
//			list.add("GET");
//			list.add("http://localhost:9200/liferay-20116/LiferayDocumentType/_msearch");
//			list.add("-H");
//			list.add("'Content-Type: application/json'");
//			list.add("-d");
//			list.add(q);
//
//			ProcessBuilder pb = new ProcessBuilder(list);
//
//			System.out.println(pb.command());
//			for (String string : pb.command()) {
//				System.out.println("string: "+string);
//			}
//			String data = StringPool.BLANK;
//
//			try {
//
//				pb.redirectErrorStream();
//				process = pb.start();
//				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//				String line = null;
//
//				while ((line = input.readLine()) != null) {
//					data += line;
//				}
//				process.destroy();
//
//			} catch (IOException e) {
//				process = null;
//				process.destroy();
//			} finally {
//				process.destroy();
//			}

			URL url = new URL(MULTIPLE_ELASTIC_SERVER_API_URL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(HttpMethod.GET);
			conn.setRequestProperty("Accept", "Content-Type: application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoInput(true);
			conn.setDoOutput(true);
	        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
	        osw.write(q);
	        osw.flush();
	        osw.close();
	        
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			result = JSONFactoryUtil.createJSONObject(sb.toString());			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		// System.out.println("ElasticQueryWrapUtil.main()");
		// System.out.println(curl().k().$("http://localhost:9200/liferay-20116/LiferayDocumentType/_search
		// -d '{ \"query\": { \"query_string\": { \"query\" :
		// \"(entryClassName:org.opencps.communication.model.Notificationtemplate)\"
		// }}}'"));
		//
		// Process process = null;
		//
		// List list = new ArrayList();
		// list.add("curl");
		// list.add("-s");
		// list.add("-X");
		// list.add("POST");
		// list.add("http://localhost:9200/liferay-20116/LiferayDocumentType/_search");
		// list.add("-d");
		// list.add("{ \"query\": { \"query_string\": { \"query\" :
		// \"(entryClassName:org.opencps.communication.model.Notificationtemplate)\"
		// }}}");
		//
		//// list.add("http://localhost:9200/_all/_search");
		//// list.add("-d");
		//// list.add("{ \"query\": { \"query_string\": { \"query\" :
		// \"(entryClassName:com.liferay.configuration.admin.web.internal.model.ConfigurationModel)\"
		// }}}");
		// ProcessBuilder pb = new ProcessBuilder(list);
		// try {
		// pb.redirectErrorStream();
		// process = pb.start();
		// BufferedReader input = new BufferedReader(new
		// InputStreamReader(process.getInputStream()));
		// String result="";
		// String line=null;
		// while((line=input.readLine())!=null) {
		// result+=line;
		// }
		// System.out.println(result);
		// process.destroy();
		// }
		// catch (IOException e) {
		// process.destroy();
		// } finally {
		// process.destroy();
		// }
	}
}
