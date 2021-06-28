package org.opencps.backend.dossiermgt.service.util;

import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;

public class UtilsFile {

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
