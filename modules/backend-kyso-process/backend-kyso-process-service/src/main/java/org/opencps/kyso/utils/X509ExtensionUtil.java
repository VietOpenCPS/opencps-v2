/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.opencps.kyso.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Vector;
//import org.bouncycastle.asn1.ASN1InputStream;
import additionalbouncycastle.asn1.ASN1InputStream;
//import org.bouncycastle.asn1.ASN1OctetString;
import additionalbouncycastle.asn1.ASN1OctetString;
//import org.bouncycastle.asn1.ASN1Sequence;
import additionalbouncycastle.asn1.ASN1Sequence;
import additionalbouncycastle.asn1.DERObject;
//import org.bouncycastle.asn1.DERObjectIdentifier;
import additionalbouncycastle.asn1.DERObjectIdentifier;
import additionalbouncycastle.asn1.x509.X509Extensions;
//import org.bouncycastle.asn1.DERTaggedObject;
import additionalbouncycastle.asn1.DERTaggedObject;
import additionalbouncycastle.asn1.x509.X509Name;
import additionalbouncycastle.jce.PrincipalUtil;
import additionalbouncycastle.jce.X509Principal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.viettel.signature.utils.CertUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Security;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509Store;
import org.opencps.kyso.action.impl.DigitalSignatureActionsImpl;
import org.apache.xml.security.*;

/**
 *
 * @author : HungND8@viettel.com.vn
 * @Version: 1.0
 * @Since : version 1.0
 * @Date : Feb 9, 2011, 4:52:29 PM
 */
public class X509ExtensionUtil {

	private static Log _log = LogFactoryUtil.getLog(DigitalSignatureActionsImpl.class);

    /**
     * utility class must get a private constructor
     */
    private X509ExtensionUtil() {
    }

    
    public static String getCN(X509Certificate cer) {
        try {
            additionalbouncycastle.jce.X509Principal principal = additionalbouncycastle.jce.PrincipalUtil.getSubjectX509Principal(cer);
            Vector values = principal.getValues(X509Name.CN);
            return (String) values.get(0);
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
        }
        return null;
    }

    public static String getCNFromDN(String dn) {
        String[] array = dn.split(",");
        for (int i = 0; i < array.length; i++) {
            String a = array[i];
            String[] b = a.split("=");
            if (b.length >= 2) {
                if ("CN".toLowerCase().equals(b[0].toLowerCase().trim())) {
//                    return b[1];
                    if (b.length > 2) {
                        String[] c = new String[b.length - 1];
                        for (int j = 1; j < b.length; j++) {
                            c[j - 1] = b[j];
                        }
                        return String.join("=", c);
                    } else {
                        return b[1];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get subject of certificate
     *
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getSubject(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector = principal.getValues(X509Principal.CN);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
            return "";
        }
    }

    /**
     * Get organization of certificate
     *
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getOrganization(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector = principal.getValues(X509Principal.O);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
            return "";
        }
    }

    /**
     * Get unit of certificate
     *
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getOrganizationUnit(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector = principal.getValues(X509Principal.OU);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
            return "";
        }
    }

    /**
     * Get location of certificate
     *
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getLocation(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector = principal.getValues(X509Principal.L);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
            return "";
        }
    }

    /**
     * Get location of certificate
     *
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getIssuerName(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getIssuerX509Principal(certificate);
            Vector vector = principal.getValues(X509Principal.CN);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            _log.error(ex);
            return "";
        }
    }

    /**
     * get the CRL URL from X509 Certificate
     *
     * @param certificate : X509 certificate
     * @return <CODE>String</CODE> : URL of CRL or <CODE>null</CODE> if no
     * extension or exception
     */
    public static String getCRLURL(X509Certificate certificate) {
        try {
            /*
             * check if has extension or not
             */
            DERObject obj = getExtensionValue(certificate, X509Extensions.CRLDistributionPoints.getId());
            if (obj == null) {
                return null;
            }

            ASN1Sequence accessDescriptions = (ASN1Sequence) obj;
            for (int i = 0; i < accessDescriptions.size(); i++) {
                ASN1Sequence accessDescription = (ASN1Sequence) accessDescriptions.getObjectAt(i);
                if (accessDescription.size() != 2) {
                    continue;
                } else if (accessDescription.getObjectAt(0) instanceof DERObject) {
                    String accessLocation = getStringFromGeneralName((DERObject) accessDescription.getObjectAt(0));
                    if (accessLocation == null) {
                        return "";
                    } else {
                        return accessLocation;
                    }
                }
            }
        } catch (Exception e) {
            _log.error(e);
        }
        return null;
    }

    /**
     * get the OCSP URL of a X509 certificate
     *
     * @param certificate : X509 certificate
     * @return <CODE>String</CODE> URL of certificate or <CODE>null</CODE> if
     * certificate has no OCSP URL extension or error
     */
    public static String getOCSPURL(X509Certificate certificate) {
        try {
            /*
             * check if has extension or not
             */
            DERObject obj = getExtensionValue(certificate, X509Extensions.AuthorityInfoAccess.getId());
            if (obj == null) {
                return null;
            }
            ASN1Sequence accessDescriptions = (ASN1Sequence) obj;
            for (int i = 0; i < accessDescriptions.size(); i++) {
                ASN1Sequence accessDescription = (ASN1Sequence) accessDescriptions.getObjectAt(i);
                if (accessDescription.size() != 2) {
                    continue;
                } else if (accessDescription.getObjectAt(0) instanceof DERObjectIdentifier
                        && "1.3.6.1.5.5.7.48.1".equals(((DERObjectIdentifier) accessDescription.getObjectAt(0)).getId())) {
                    String accessLocation = getStringFromGeneralName((DERObject) accessDescription.getObjectAt(1));
                    if (accessLocation == null) {
                        return "";
                    } else {
                        return accessLocation;
                    }
                }
            }
        } catch (Exception e) {
            _log.error(e);
        }
        return null;
    }

    /**
     * get the extension value of particular X509 certificate
     *
     * @param cert : X509 certificate
     * @param oid : id of particular type, see
     * <CODE>org.bouncycastle.asn1.x509.X509Extensions</CODE> to get the object
     * id
     * @return <CODE>DERObject</CODE> of
     * @throws IOException
     */
    private static DERObject getExtensionValue(X509Certificate cert, String oid) throws IOException {
        byte[] bytes = cert.getExtensionValue(oid);
        if (bytes == null) {
            return null;
        }
        ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(bytes));
        ASN1OctetString octs = (ASN1OctetString) aIn.readObject();
        aIn = new ASN1InputStream(new ByteArrayInputStream(octs.getOctets()));
        return aIn.readObject();
    }

    private static String getStringFromGeneralName(DERObject names) throws IOException {
        DERTaggedObject taggedObject = (DERTaggedObject) names;
        return new String(ASN1OctetString.getInstance(taggedObject, false).getOctets(), "ISO-8859-1");
    }

    public static Collection<X509Certificate> getCertificates(InputStream cerRoot) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        CMSSignedData sd = new CMSSignedData(cerRoot);
        X509Store store;
        store = (X509Store) sd.getCertificates();
        Collection<X509Certificate> certificates = store.getMatches(X509CertStoreSelector.getInstance(new X509CertSelector()));

        return certificates;
    }

    public static Map<String, X509Certificate> getAllStrustCertCA(String folderPath) {
        Map<String, X509Certificate> allStrustCertCA = new ConcurrentHashMap<String, X509Certificate>();

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> fileCerRoots = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (!listOfFiles[i].getName().isEmpty() && listOfFiles[i].getName().toLowerCase().indexOf(".p7b") != -1) {
                    fileCerRoots.add(listOfFiles[i].getName());
                }

            }
        }

        for (String fileCerRoot : fileCerRoots) {
            InputStream is = null;
            try {
                is = new FileInputStream(folderPath + "/" + fileCerRoot);
                Collection<X509Certificate> cers = getCertificates(is);
                for (X509Certificate cer : cers) {
                    String serialNumber = cer.getSerialNumber().toString(16);
                    //System.out.println(cer.getSubjectDN().toString());
                    allStrustCertCA.put(serialNumber, cer);
                }
            } catch (FileNotFoundException ex) {
                _log.error(ex);
            } catch (IOException ex) {
                _log.error(ex);
            } catch (Exception ex) {
                _log.error(ex);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        _log.error(ex);
                    }
                }
            }
        }
        return allStrustCertCA;
    }

    public static X509Certificate[] getCertChainOfCert(List<String> certList, String folderRootCA) {
        if (certList == null || certList.size() == 0) {
            return null;
        }
        X509Certificate[] certChain = null;
        List<X509Certificate> a = new ArrayList<X509Certificate>();
        if (certList.size() > 0) {
            for (int i = 0; i < certList.size(); i++) {
                if (certList.get(i) != null && !certList.get(i).trim().isEmpty()) {
                    X509Certificate x509Cert = CertUtils.getX509Cert(certList.get(i));
                    if (x509Cert != null) {
                        a.add(x509Cert);
                    }
                }
            }
        }
        if (a.size() == 0) {
            //error
        } else if (a.size() == 1) {
            certChain = getCertChainOfCert(a.get(0), folderRootCA);
        } else {
            certChain = new X509Certificate[a.size()];
            for (int i = 0; i < a.size(); i++) {
                certChain[i] = a.get(i);
            }
        }
        return certChain;
    }

    public static X509Certificate[] getCertChainOfCert(String certChainBase64, String folderRootCA) {
        if (certChainBase64 == null || certChainBase64.trim().isEmpty()) {
            return null;
        }
        String[] chainBase64 = certChainBase64.split(",");
        if (chainBase64 == null || chainBase64.length == 0) {
            return null;
        }
        List<String> a = new ArrayList<String>();
        if (chainBase64.length > 0) {
            for (int i = 0; i < chainBase64.length; i++) {
                if (chainBase64[i] != null && !chainBase64[i].trim().isEmpty()) {
                    a.add(chainBase64[i]);
                }

            }
        }
        return getCertChainOfCert(a, folderRootCA);
    }

    public static X509Certificate[] getCertChainOfCert(X509Certificate certUser, String folderPath) {
        Map<String, X509Certificate> allStrustCertCA = getAllStrustCertCA(folderPath);
        List<X509Certificate> chainList = new ArrayList<X509Certificate>();
        String issuerCN = X509ExtensionUtil.getCNFromDN(certUser.getIssuerDN().toString());
        for (Map.Entry<String, X509Certificate> entry : allStrustCertCA.entrySet()) {
            X509Certificate certCA = entry.getValue();
            String subjectCN = X509ExtensionUtil.getCN(certCA);
            if(issuerCN != null) {
                if (issuerCN.equals(subjectCN)) {
                    chainList.add(certCA);
                }
            }
        }
        X509Certificate[] chain = new X509Certificate[chainList.size() + 1];
        chain[0] = certUser;
        int index = 1;
        for (X509Certificate certCA : chainList) {
            chain[index] = certCA;
            index++;
        }
        return chain;
    }
}
