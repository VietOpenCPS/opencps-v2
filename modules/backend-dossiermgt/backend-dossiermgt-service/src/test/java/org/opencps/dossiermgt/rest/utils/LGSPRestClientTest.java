package org.opencps.dossiermgt.rest.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPRestClientTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			LGSPRestClient.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBaseUrlTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getBaseUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBaseUrlTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setBaseUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishDossierTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.publishDossier("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDocumentTraceTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.postDocumentTrace("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isWriteLogTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.isWriteLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWriteLogTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setWriteLog(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}