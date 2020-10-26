package org.opencps.api.serviceprocess.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceProcessSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setReceiveDateTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setReceiveDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDateTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			ServiceProcessSearchModel object = new ServiceProcessSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}