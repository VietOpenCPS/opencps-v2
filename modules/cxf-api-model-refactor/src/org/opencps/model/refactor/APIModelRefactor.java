package org.opencps.model.refactor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIModelRefactor {

	//private static Log _log = LogFactoryUtil.getLog(APIModelRefactor.class);

	public APIModelRefactor() {
	}

	public void _doRefactorModel(File file) {

		FileReader fr = null;
		BufferedReader br = null;
		BufferedWriter out = null;
		try {
			List<String> lines = new ArrayList<String>();
			String line = null;

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if ((line.contains("public class")) &&
					(!line.contains("@XmlRootElement")))
					line = line.replace(
						"public class",
						"import javax.xml.bind.annotation.XmlRootElement; \n@XmlRootElement(name = \"data\") public class");
				line = line.replace(
					"javax.xml.datatype.XMLGregorianCalendar",
					"java.util.Date");
				lines.add(line + "\n");
			}

			FileWriter fw = new FileWriter(file);
			out = new BufferedWriter(fw);
			for (String s : lines)
				out.write(s);
			out.flush();
		}
		catch (Exception ex) {
			//_log.error(ex);
			ex.printStackTrace();
		} finally {
			processCloseFile(fr, br, out);
		}
	}

	public void _doRefactorApi(File file) {

		FileReader fr = null;
		BufferedReader br = null;
		BufferedWriter out = null;
		try {
			List<String> lines = new ArrayList<String>();
			String line = null;

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {

				if (line.contains("@Path(\"/\")")) {
					line = line.replace(
						"@Path(\"/\")",
						"import java.util.Locale; \nimport javax.ws.rs.core.Context; \nimport javax.ws.rs.core.HttpHeaders; \nimport com.liferay.portal.kernel.model.Company; \nimport com.liferay.portal.kernel.model.User; \nimport com.liferay.portal.kernel.service.ServiceContext; \n@Path(\"/\")");
				}

				if ((line.contains("public Object"))) {
					int pos = line.indexOf("(");
					StringBuilder builder = new StringBuilder(line);
					builder.replace(
						pos, pos + 1,
						"(@Context User user, @Context Company company, @Context Locale locale, @Context HttpHeaders httpHeaders, @Context ServiceContext serviceContext, ");
					line = builder.toString();
				}

				lines.add(line + "\n");
			}
			FileWriter fw = new FileWriter(file);
			out = new BufferedWriter(fw);
			for (String s : lines)
				out.write(s);
			out.flush();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			processCloseFile(fr, br, out);
		}
	}

	public void doRefactor(String namespace) {

		String userDir = System.getProperty("user.dir");

//		System.out.println("Application plugin running in " + userDir);

		String modelFolderPath = userDir + "/src/main/java/org/opencps/rest/" +
			namespace + "/model";
//		System.out.println("Scanning folder: " + modelFolderPath);
		File folder = new File(modelFolderPath);
		if (folder != null) {
			for (File file : folder.listFiles()) {
				if (!file.isDirectory()) {

//					System.out.println(file.getAbsoluteFile());
					_doRefactorModel(file);
				}
			}
		}

		String apiFolderPath =
			userDir + "/src/main/java/org/opencps/rest/" + namespace + "/api";
//		System.out.println("Scanning folder: " + apiFolderPath);
		File apiFolder = new File(apiFolderPath);
		if (apiFolder != null) {
			for (File file : apiFolder.listFiles()) {
				if (!file.isDirectory()) {

//					System.out.println(file.getAbsoluteFile());
					_doRefactorApi(file);
				}
			}
		}
	}

	private static void processCloseFile(FileReader fr, BufferedReader br, BufferedWriter out) {
		if (fr != null) {
			try {
				fr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					} finally {
						if (out != null) {
							try {
								out.close();
							} catch (IOException e3) {
								e3.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/*public static void main(String[] args) {

		String test =
			"public Object getMobileDashboardData(@HeaderParam(\"groupId\") Long groupId);";

		System.out.println(test.indexOf("("));

		StringBuilder builder = new StringBuilder(test);
		builder.replace(36, 37, "(xxx");

		System.out.println(builder.toString());
	}*/
}
