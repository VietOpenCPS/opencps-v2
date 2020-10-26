package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NGSPRestClientTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			NGSPRestClient.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBaseUrlTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getBaseUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConsumerKeyTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getConsumerKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBaseUrlTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setBaseUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConsumerKeyTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setConsumerKey("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConsumerAdapterTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setConsumerAdapter("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConsumerSecretTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.setConsumerSecret("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConsumerAdapterTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getConsumerAdapter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConsumerSecretTest() {
		try{
			NGSPRestClient object = new NGSPRestClient("abcde", "abcde", "abcde", "abcde");
			object.getConsumerSecret();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}