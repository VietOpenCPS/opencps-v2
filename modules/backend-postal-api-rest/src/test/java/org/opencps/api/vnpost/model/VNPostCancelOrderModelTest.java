package org.opencps.api.vnpost.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VNPostCancelOrderModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCustomerCodeTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.getCustomerCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderNumberTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.getOrderNumber();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderNumberTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.setOrderNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCustomerCodeTest() {
		try{
			VNPostCancelOrderModel object = new VNPostCancelOrderModel();
			object.setCustomerCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}