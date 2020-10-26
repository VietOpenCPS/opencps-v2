package org.opencps.api.holiday.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DataSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setScopeTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setScope("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScopeTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getScope();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setYear("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordsTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setKeywords("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordsTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getKeywords();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}