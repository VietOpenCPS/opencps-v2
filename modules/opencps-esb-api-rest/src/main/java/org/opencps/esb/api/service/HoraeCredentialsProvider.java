package org.opencps.esb.api.service;

import com.google.common.base.Strings;
import com.google.inject.Provider;
import com.inet.api.iws.auth.horae.BasicHoraeCredentials;
import com.inet.api.iws.auth.horae.HoraeCredentials;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * HoraeCredentialsProvider.
 *
 * @author: Dzung Nguyen
 * @version: $Id HoraeCredentialsProvider 2013-09-18 11:51:30z nguyen_dv $
 * @since 1.0
 */
public class HoraeCredentialsProvider implements Provider<HoraeCredentials> {
  //~ class properties =========================================================
  private static final String CONFIG_FILE = "credentials.properties";
  private final HoraeCredentials credentials;

  //~ class members ============================================================
  /**
   * Constructs the AwsCredentialsProvider object.
   */
  public HoraeCredentialsProvider() {
    this.credentials = create();
  }

  public HoraeCredentials get() {
    return credentials;
  }

  /**
   * Creates the AwsCredentials by reading the credentials configuration from
   * the configure file.
   */
  private HoraeCredentials create() {
    InputStream stream = HoraeCredentialsProvider.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    Properties configure = new Properties();

    try {
      if (stream == null) {
        throw new RuntimeException("The credentials configuration file: " + CONFIG_FILE + " not found.");
      }

      configure.load(stream);

      String key = configure.getProperty("key");
      String secret = configure.getProperty("secret");

      if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(secret)) {
        throw new RuntimeException("The key or secret key not found.");
      }

      return new BasicHoraeCredentials(key, secret);
    } catch (IOException ex) {
      throw new RuntimeException("Unable to read configuration data from file: " + CONFIG_FILE, ex);
    }
  }
}
