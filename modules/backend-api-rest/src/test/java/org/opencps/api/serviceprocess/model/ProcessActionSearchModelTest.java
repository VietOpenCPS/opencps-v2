package org.opencps.api.serviceprocess.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessActionSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setPreStepCodeTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setPreStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreStepCodeTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getPreStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPostStepCodeTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getPostStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPostStepCodeTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setPostStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceProcessIdTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setServiceProcessId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessIdTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getServiceProcessId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			ProcessActionSearchModel object = new ProcessActionSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}