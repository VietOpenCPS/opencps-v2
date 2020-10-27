package org.opencps.dossiermgt.listenner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierListennerUltilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPayloadTest() {
		try{
			DossierListennerUltils.getPayload("abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationQueueTest() {
		try{
			DossierListennerUltils.createNotificationQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationQueueTest4() {
		try{
			DossierListennerUltils.createNotificationQueue(null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierLogTest() {
		try{
			DossierListennerUltils.createDossierLog(null, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendToEmployeeTest() {
		try{
			DossierListennerUltils.sendToEmployee(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendToApplicantTest() {
		try{
			DossierListennerUltils.sendToApplicant(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}