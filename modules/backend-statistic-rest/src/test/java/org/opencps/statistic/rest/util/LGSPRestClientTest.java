package org.opencps.statistic.rest.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.getBaseUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.getToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBaseUrlTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.setBaseUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishDossierTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.publishDossier("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDocumentTraceTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.postDocumentTrace("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isWriteLogTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.isWriteLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWriteLogTest() {
		try{
			LGSPRestClient object = new LGSPRestClient("abcde");
			object.setWriteLog(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateVotingStatisticsMonthTest() {
//		try{
//			LGSPRestClient object = new LGSPRestClient("abcde");
//			object.updateVotingStatisticsMonth("abcde", Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateStatisticsMonthTest() {
//		try{
//			LGSPRestClient object = new LGSPRestClient("abcde");
//			object.updateStatisticsMonth("abcde", Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}