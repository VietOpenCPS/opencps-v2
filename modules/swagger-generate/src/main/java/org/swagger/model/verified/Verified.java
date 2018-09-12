package org.swagger.model.verified;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Verified {
	private static final Logger _log =
			Logger.getLogger(Verified.class.getName());
	
    public void doIt(String fileSource) {
        try {
        	List<String> lines = new ArrayList<String>();
            String line = null;
            File f1 = new File(fileSource);
            try (FileReader fr = new FileReader(f1)) {
	            try (BufferedReader br = new BufferedReader(fr)) {
		            while ((line = br.readLine()) != null) {
		                if (line.contains("public class") && !line.contains("@XmlRootElement"))
		                    line = line.replace("public class", "import javax.xml.bind.annotation.XmlRootElement; \n@XmlRootElement(name = \"data\") public class");
		                	line = line.replace("javax.xml.datatype.XMLGregorianCalendar", "java.util.Date");
		                lines.add(line + "\n");
		            }
	            }
            }
            try (FileWriter fw = new FileWriter(f1)) {
	            BufferedWriter out = new BufferedWriter(fw);
	            for(String s : lines)
	                 out.write(s);
	            out.flush();
	            out.close();
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        	_log.log(Level.SEVERE, "Exception occur", ex);
        }
    }

    public void listFilesForFolder(File folder) {
    	Verified verified = new Verified();
    	if (folder != null) {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                	_log.info(String.valueOf(fileEntry.getAbsoluteFile().getPath()));
//                    System.out.println(fileEntry.getAbsoluteFile());
                    verified.doIt(fileEntry.getAbsoluteFile().toString());
                }
            }
    	}
    }

    public static void main(String args[]) {
    	Verified verified = new Verified();
    	String userDir = System.getProperty("user.dir");
    	_log.info("Application plugin running in " + userDir);
//    	System.out.println("Application plugin running in " + userDir);
//    	
    	String folderStr = userDir + "/src/main/java/io/swagger/model";
    	_log.info("Scanning folder: " + folderStr);
//    	System.out.println("Scanning folder: " + folderStr);
    	File folder = new File(folderStr);
    	verified.listFilesForFolder(folder);
    }
    
}
