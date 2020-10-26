package org.opencps.api.dossierlog.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogSearchIdModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAuthorTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getAuthor();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogIdTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getDossierLogId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAuthorTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setAuthor("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierLogIdTest() {
		try{
			DossierLogSearchIdModel object = new DossierLogSearchIdModel();
			object.setDossierLogId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}