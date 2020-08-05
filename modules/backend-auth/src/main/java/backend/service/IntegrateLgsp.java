package backend.service;

import org.opencps.kernel.message.MBMessageEntry;

public interface IntegrateLgsp extends IntegrateThirdPartyApi{
    String getToken();
    void   sendMail(String token, MBMessageEntry messageEntry, String contactEmail);
}
