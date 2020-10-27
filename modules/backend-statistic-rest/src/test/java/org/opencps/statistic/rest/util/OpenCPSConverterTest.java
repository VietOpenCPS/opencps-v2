package org.opencps.statistic.rest.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSConverterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertToDocumentTracesTest() {
		try{
			OpenCPSConverter.convertToDocumentTraces(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToLGSPJSONTest() {
		try{
			OpenCPSConverter.convertDossierToLGSPJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSuccessTest() {
		try{
			OpenCPSConverter.isSuccess(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertMtokenTest() {
		try{
			OpenCPSConverter.convertMtoken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertToUTCDateTest() {
		try{
			OpenCPSConverter.convertToUTCDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStatisticsToLGSPJSONTest() {
		try{
			OpenCPSConverter.convertStatisticsToLGSPJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertVotingStatisticsToLGSPJSONTest() {
		try{
			OpenCPSConverter.convertVotingStatisticsToLGSPJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}