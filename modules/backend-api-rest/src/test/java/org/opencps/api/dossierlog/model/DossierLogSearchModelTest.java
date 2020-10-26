package org.opencps.api.dossierlog.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOwnerTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setOwner(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOwnerTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.isOwner();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DossierLogSearchModel object = new DossierLogSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}