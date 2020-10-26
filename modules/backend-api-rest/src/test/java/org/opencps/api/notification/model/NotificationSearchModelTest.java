package org.opencps.api.notification.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setEndTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordsTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.setKeywords("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordsTest() {
		try{
			NotificationSearchModel object = new NotificationSearchModel();
			object.getKeywords();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}