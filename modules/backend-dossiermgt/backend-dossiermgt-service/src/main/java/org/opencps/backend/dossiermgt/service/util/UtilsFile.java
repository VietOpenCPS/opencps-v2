package org.opencps.backend.dossiermgt.service.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.opencps.dossiermgt.action.impl.FrequencyIntegrationActionImpl;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class UtilsFile {
    public static InputStream base64toFile(byte[] contentDecoded) {
        try {
            return new ByteArrayInputStream(contentDecoded);
        } catch (Exception e) {
            System.out.println("ERROR base64 to file" + e.getMessage());
            return null;
        }
    }

    public static byte[] decodeBase64(String contentEncode) {
        try {
            byte[] encbytes = contentEncode.getBytes();

            Base64.Decoder dec = Base64.getDecoder();
            byte[] decbytes = dec.decode(encbytes);
            return decbytes;
        } catch (Exception e) {
            System.out.println("ERROR decode base64" + e.getMessage());
            return null;
        }
    }

    public static String getMimeType(String type) {
        switch (type) {
            case FrequencyOfficeConstants.DOC :
                return FrequencyOfficeConstants.MIME_DOC;
            case FrequencyOfficeConstants.DOCX :
                return FrequencyOfficeConstants.MIME_DOCX;
            case FrequencyOfficeConstants.CSV :
                return FrequencyOfficeConstants.MIME_CSV;
            case FrequencyOfficeConstants.JPEG :
                return FrequencyOfficeConstants.MIME_JPEG;
            case FrequencyOfficeConstants.JPG :
                return FrequencyOfficeConstants.MIME_JPG;
            case FrequencyOfficeConstants.PNG :
                return FrequencyOfficeConstants.MIME_PNG;
            case FrequencyOfficeConstants.PDF :
                return FrequencyOfficeConstants.MIME_PDF;
            case FrequencyOfficeConstants.XLS :
                return FrequencyOfficeConstants.MIME_XLS;
            case FrequencyOfficeConstants.XLSX :
                return FrequencyOfficeConstants.MIME_XLSX;
            default:
                return "";
        }
    }

}
