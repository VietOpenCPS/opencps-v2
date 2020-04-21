package org.opencps.esb.api.service;

import com.google.inject.Provider;
import com.inet.api.ClientConfiguration;
import com.inet.api.http.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ClientConfigurationProvider.
 *
 * @author: Dzung Nguyen
 * @version: $Id ClientConfigurationProvider 2013-09-18 11:58:30z nguyen_dv $
 * @since 1.0
 */
public class ClientConfigurationProvider implements Provider<ClientConfiguration> {
  //~ class properties ========================================================
  private static final String CONFIG_FILE = "collaboration.properties";
  private final ClientConfiguration clientConfiguration;

  //~ class members ===========================================================
  public ClientConfigurationProvider() {
    this.clientConfiguration = create();
  }

  public ClientConfiguration get() {
    return clientConfiguration;
  }

  /**
   * Creates the AwsCredentials by reading the credentials configuration from
   * the configure file.
   */
  private ClientConfiguration create() {
    InputStream stream = ClientConfigurationProvider.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    Properties configuration = new Properties();

    try {
      if (stream == null) {
        throw new RuntimeException("The credentials configuration file: " + CONFIG_FILE + " not found.");
      }

      configuration.load(stream);


/*          .withProtocol(Protocol.valueOf(configuration.getProperty("client.protocol", "HTTP")))
          .withMaxConnections(Integer.parseInt(configuration.getProperty("client.max-connection", "10"), 10))
          .withMaxErrorRetry(Integer.parseInt(configuration.getProperty("client.max-error-retry", "3"), 10))
          .withProperty("service.endpoint", configuration.getProperty("service.endpoint", "imercury.inetcloud.vn/ihorae"))
          .withProperty("client.application", configuration.getProperty("client.application", "iDesk"));*//*
      ClientConfiguration clientConfig = new ClientConfiguration()
        .withProxyHost("10.192.243.149")
        .withProxyDomain("vpbtc")
        .withProxyUsername("panuserid")
        .withProxyPassword("dvc_btc@123")
        .withProxyPort(8080)*/
      ClientConfiguration clientConfig = new ClientConfiguration()
        .withProtocol(Protocol.valueOf(configuration.getProperty("client.protocol", "HTTP")))
        .withMaxConnections(Integer.parseInt(configuration.getProperty("client.max-connection", "10"), 10))
        .withMaxErrorRetry(Integer.parseInt(configuration.getProperty("client.max-error-retry", "3"), 10))
        .withProperty("service.endpoint", configuration.getProperty("service.endpoint", "w168.inetcloud.vn/ihorae"))
        .withProperty("client.application", configuration.getProperty("client.application", "Qoffice"));


      return clientConfig;
    } catch (IOException ex) {
      throw new RuntimeException("Unable to read configuration data from file: " + CONFIG_FILE, ex);
    }
  }
}
