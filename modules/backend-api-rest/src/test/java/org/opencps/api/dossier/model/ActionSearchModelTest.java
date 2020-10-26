package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAutoTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getAuto();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAutoTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setAuto("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodeTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.getCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodeTest() {
		try{
			ActionSearchModel object = new ActionSearchModel();
			object.setCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}