package org.opencps.api.vnpost.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VNPostServerConfigModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActiveTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setActive(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActiveTest2() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setActive(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActiveTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getActive();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderProvinceTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderProvince();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApiCancelOrderTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getApiCancelOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderDistrictTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderDistrict();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApiGetOrderTrackingTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getApiGetOrderTracking();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApiGetOrderTrackingTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setApiGetOrderTracking("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderDistrictTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderDistrict(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderProvinceTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderProvince(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApiCancelOrderTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setApiCancelOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApiGetTokenTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getApiGetToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCustomerKeyTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getCustomerKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecretKeyTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSecretKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderNameTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCustomerCodeTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getCustomerCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderAddressTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderTelTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderTel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApiPostOrderTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getApiPostOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSenderEmailTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.getSenderEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderAddressTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCustomerCodeTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setCustomerCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderEmailTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderTelTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderTel("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCustomerKeyTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setCustomerKey("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSenderNameTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSenderName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSecretKeyTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setSecretKey("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApiPostOrderTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setApiPostOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApiGetTokenTest() {
		try{
			VNPostServerConfigModel object = new VNPostServerConfigModel("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
			object.setApiGetToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}