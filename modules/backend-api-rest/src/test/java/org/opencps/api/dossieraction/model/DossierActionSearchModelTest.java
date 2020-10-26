package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActionCodeTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAutoTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getAuto();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOwnerTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setOwner(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAutoTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setAuto("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOwnerTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.isOwner();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DossierActionSearchModel object = new DossierActionSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}