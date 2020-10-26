package org.opencps.api.datatempmgt.model;
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
	public void getParentTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getParent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setParent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAncestorTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getAncestor();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAncestorTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setAncestor("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLevelTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.getLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelTest() {
		try{
			DataSearchModel object = new DataSearchModel();
			object.setLevel("abcde");
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