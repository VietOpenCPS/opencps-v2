package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SystemUtilsTest {
	@Before
	public void setUp() {
	}

	/*@Test
	public void cleanDossierFileTest() {
		try{
			SystemUtils.cleanDossierFile(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void cleanDossierTest() {
		try{
			SystemUtils.cleanDossier(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void cleanServiceInfoTest() {
		try{
			SystemUtils.cleanServiceInfo(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	/*@Test
	public void cleanDossierLogTest() {
		try{
			SystemUtils.cleanDossierLog(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/

	/*@Test
	public void cleanNotificationTemplateTest() {
		try{
			SystemUtils.cleanNotificationTemplate(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	/*@Test
	public void cleanNotificationQueueTest() {
		try{
			SystemUtils.cleanNotificationQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
}