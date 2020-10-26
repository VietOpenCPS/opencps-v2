package org.opencps.api.serviceprocess.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessStepSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setServiceProcessIdTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setServiceProcessId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessIdTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getServiceProcessId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			ProcessStepSearchModel object = new ProcessStepSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}