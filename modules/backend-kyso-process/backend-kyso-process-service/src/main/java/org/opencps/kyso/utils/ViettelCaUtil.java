package org.opencps.kyso.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.viettel.signature.pdf.DisplayConfig;
import com.viettel.signature.pdf.SignPdfAsynchronous;
import com.viettel.signature.plugin.SignPdfFile;
import com.viettel.signature.utils.CertUtils;

import java.io.File;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

import org.opencps.kyso.action.impl.DigitalSignatureActionsImpl;

public class ViettelCaUtil {
	private static Log _log = LogFactoryUtil.getLog(ViettelCaUtil.class);
	private String digestAlgorithm = "SHA1";
	private String cryptAlgorithm = "RSA";
	private String fieldName;

	public static String getHashTypeRectangleText(SignPdfFile pdfSig, String filePath, X509Certificate[] certChain, String fontPath) {
        int numberPageSign = DisplayConfig.NUMBER_PAGE_SIGN_DEFAULT;
        float widthRectangle = DisplayConfig.WIDTH_RECTANGLE_DEFAULT;
        float heightRectangle = DisplayConfig.HEIGHT_RECTANGLE_DEFAULT;
        int locateSign = DisplayConfig.LOCATE_SIGN_DEFAULT;
        float marginTopOfRectangle = DisplayConfig.MARGIN_TOP_OF_RECTANGLE_DEFAULT;
        float marginBottomOfRectangle = DisplayConfig.MARGIN_BOTTOM_OF_RECTANGLE_DEFAULT;
        float marginRightOfRectangle = DisplayConfig.MARGIN_RIGHT_OF_RECTANGLE_DEFAULT;
        float marginLeftOfRectangle = DisplayConfig.MARGIN_LEFT_OF_RECTANGLE_DEFAULT;
        String displayText = DisplayConfig.DISPLAY_TEXT_DEFAULT_EMPTY;
        String formatRectangleText = DisplayConfig.FORMAT_RECTANGLE_TEXT_DEFAULT;
        String contact = CertUtils.getCN(certChain[0]);
        String reason = "Ký số";
        String location = "Hà Nội";
        String dateFormatString = DisplayConfig.DATE_FORMAT_STRING_DEFAULT;
        int sizeFont = DisplayConfig.SIZE_FONT_DEFAULT;
        String ou = DisplayConfig.ORGANIZATION_UNIT_DEFAULT_EMPTY;
        String o = DisplayConfig.ORGANIZATION_DEFAULT_EMPTY;
        DisplayConfig displayConfig = DisplayConfig.generateDisplayConfigRectangleText(
                numberPageSign, widthRectangle, heightRectangle, locateSign,
                marginTopOfRectangle, marginBottomOfRectangle,
                marginRightOfRectangle, marginLeftOfRectangle,
                displayText, formatRectangleText,
                contact, reason, location,
                dateFormatString, fontPath, sizeFont,
                ou, o);
        String digestAlg = SignPdfAsynchronous.HASH_ALGORITHM_SHA1;
        String cryptAlg = SignPdfAsynchronous.CRYPT_ALGORITHM_RSA;
        String base64Hash = pdfSig.createHash(filePath, certChain, digestAlg, cryptAlg, displayConfig);
        return base64Hash;
    }
	
	
}
