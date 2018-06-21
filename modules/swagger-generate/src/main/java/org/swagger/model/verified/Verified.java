package org.swagger.model.verified;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class Verified {

    public void doIt(String fileSource) {
        try {
        	List<String> lines = new ArrayList<String>();
            String line = null;
            File f1 = new File(fileSource);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains("public class") && !line.contains("@XmlRootElement"))
                    line = line.replace("public class", "import javax.xml.bind.annotation.XmlRootElement; \n@XmlRootElement public class");
                	line = line.replace("javax.xml.datatype.XMLGregorianCalendar", "java.util.Date");
                lines.add(line + "\n");
            }
            fr.close();
            br.close();
            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                 out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void listFilesForFolder(File folder) {
    	Verified verified = new Verified();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getAbsoluteFile());
                verified.doIt(fileEntry.getAbsoluteFile().toString());
            }
        }
    }

    public static void main(String args[]) {
    	Verified verified = new Verified();
    	
    	String folderStr = "/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/swagger-generate/src/main/java/io/swagger/model";
    	File folder = new File(folderStr);
    	verified.listFilesForFolder(folder);
    }
    
}
