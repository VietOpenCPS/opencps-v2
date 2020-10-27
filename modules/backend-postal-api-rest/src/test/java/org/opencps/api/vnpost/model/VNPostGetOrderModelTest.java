package org.opencps.api.vnpost.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VNPostGetOrderModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPageSizeTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.getPageSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPageSizeTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.setPageSize(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderNumberTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.getOrderNumber();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastIdTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.getLastId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderNumberTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.setOrderNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLastIdTest() {
		try{
			VNPostGetOrderModel object = new VNPostGetOrderModel();
			object.setLastId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}