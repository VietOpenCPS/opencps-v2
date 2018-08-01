import java.io.FileReader;

//import com.liferay.portal.kernel.json.JSONArray;
//import com.liferay.portal.kernel.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestJSON {

	public static void main(String []args) {
		 JSONParser parser = new JSONParser();
		 try {
			 JSONArray a = (JSONArray) parser.parse(new FileReader("/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/backend-api-rest-v2-1/src/main/resources/test.json"));

			  for (Object o : a)
			  {
			    JSONObject person = (JSONObject) o;

			    String name = (String) person.get("name");
			    System.out.println(name);

			    String city = (String) person.get("city");
			    System.out.println(city);

			    String job = (String) person.get("job");
			    System.out.println(job);

			    JSONArray cars = (JSONArray) person.get("cars");

			    for (Object c : cars)
			    {
			      System.out.println(c+"");
			    }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
