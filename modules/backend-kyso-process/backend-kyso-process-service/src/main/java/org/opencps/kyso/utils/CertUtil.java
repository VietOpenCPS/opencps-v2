/**
* OpenCPS is the open source Core Public Services software
* Copyright (C) 2016-present OpenCPS community

* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>
*/

package org.opencps.kyso.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author trungnt
 */
public class CertUtil {

	/**
	 * @param path
	 * @return
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static Certificate getCertificateByPath(String certPath)
		throws CertificateException, FileNotFoundException, URISyntaxException {

		CertificateFactory cf = CertificateFactory
			.getInstance("X.509");

		Certificate cert = cf
			.generateCertificate(new FileInputStream(new File(certPath)));

		return cert;
	}

	/**
	 * @param certPath
	 * @return
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static X509Certificate getX509CertificateByPath(String certPath)
		throws CertificateException, FileNotFoundException, URISyntaxException {

		CertificateFactory cf = CertificateFactory
			.getInstance("X.509");

		X509Certificate cert = (X509Certificate) cf
			.generateCertificate(new FileInputStream(new File(certPath)));

		return cert;
	}

	/**
	 * @param url
	 * @return
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static Certificate getCertificateByURL(String url)
		throws CertificateException, FileNotFoundException, URISyntaxException {

		CertificateFactory cf = CertificateFactory
			.getInstance("X.509");

		Certificate cert = cf
			.generateCertificate(new FileInputStream(new File(new URI(url))));

		return cert;
	}
}
