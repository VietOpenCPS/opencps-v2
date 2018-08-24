
package org.opencps.kernel.template.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {

	public static class Thing {

		private Object data;

		public Thing(String name, int age) {
			this.setName(name);
			this.setAge(age);
		}

		public Thing(String name, int age, Object object) {
			this.setName(name);
			this.setAge(age);
			this.setData(object);
		}

		public String getName() {

			return name;
		}

		public void setName(String name) {

			this.name = name;
		}

		public int getAge() {

			return age;
		}

		public void setAge(int age) {

			this.age = age;
		}
		private String name;
		private int age;

		public Object getData() {

			return data;
		}

		public void setData(Object object) {

			this.data = object;
		}

	}

	public static void main(String[] args)
		throws IOException, TemplateException {

		// String templateStr = "Hello [${emp.user} - ${emp.email}]";
		String templateStr =
			"name: ${thing.name} - age: ${thing.age} - data: ${thing.data.user}]";

		Configuration configuration =
			new Configuration(Configuration.getVersion());
		configuration.setDefaultEncoding("UTF-8");
		configuration.setObjectWrapper(
			new DefaultObjectWrapper(
				configuration.getIncompatibleImprovements()));

		Template t = new Template(
			"name", new StringReader(templateStr),
			new Configuration(Configuration.getVersion()));

		Map<String, String> map = new HashMap<String, String>();

		Map<String, Object> map2 = new HashMap<String, Object>();

		map.put("user", "trungnt");
		map.put("email", "trungnt@fds.vn");

		SimpleHash hash = new SimpleHash(map);

		// hash.put("emp", map);

		Info info = new Info("trungnt", "trungnt@fds.vn");

		hash.put("emp", info);

		map2.put("emp", info);

		Writer out = new OutputStreamWriter(System.out);

		// t.process(map2, out);

		// out.flush();

		// ObjectWrapper objectWrapper =

		/*
		 * StringTemplateLoader stringLoader = new StringTemplateLoader();
		 * stringLoader.putTemplate( "greetTemplate",
		 * "<#macro greet>Hello</#macro>"); stringLoader.putTemplate(
		 * "myTemplate", "<#include \"greetTemplate\"><@greet/> World!");
		 */

		// new BeanModel(object, (BeansWrapper)objectWrapper);

		Map<String, Object> root = new HashMap<String, Object>();
		Thing thing = new Thing("trungnt", 30, info);
		// thing.setName("Name1");
		// thing.setAge(30);
		root.put("thing", thing);

		StringWriter out2 = new StringWriter();

		t.process(root, out2);

		System.out.println(out2.getBuffer().toString());

	}

	public static class Info {

		private String user;
		private String email;

		public Info() {

		}

		public Info(String user, String email) {
			this.setUser(user);
			this.setEmail(email);

		}

		public String getUser() {

			return user;
		}

		public String getEmail() {

			return email;
		}

		public void setUser(String user) {

			this.user = user;
		}

		public void setEmail(String email) {

			this.email = email;
		}

	}

}
