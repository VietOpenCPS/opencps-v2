package backend.api.rest.application.v21.elasticwrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

public class ElasticQueryWrapUtil {

	public static JSONObject query(String q, String className, long conpanyId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			Process process = null;

			List<String> list = new ArrayList<String>();
			list.add("curl");
			list.add("-s");
			list.add("-X");
			list.add("GET");
			list.add("http://localhost:9200/liferay-20116/LiferayDocumentType/_search");
			list.add("-d");
			list.add(q);

			ProcessBuilder pb = new ProcessBuilder(list);

			String data = StringPool.BLANK;

			try {

				pb.redirectErrorStream();
				process = pb.start();
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

				String line = null;

				while ((line = input.readLine()) != null) {
					data += line;
				}
				process.destroy();

			} catch (IOException e) {
				process = null;
				process.destroy();
			} finally {
				process.destroy();
			}

			result = JSONFactoryUtil.createJSONObject(data);
			
		} catch (JSONException e) {
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
