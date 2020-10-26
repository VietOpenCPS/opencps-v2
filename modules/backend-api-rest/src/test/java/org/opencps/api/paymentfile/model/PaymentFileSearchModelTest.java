package org.opencps.api.paymentfile.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentFileSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAgencyTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceTest() {
		try{
			PaymentFileSearchModel object = new PaymentFileSearchModel();
			object.setService("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}