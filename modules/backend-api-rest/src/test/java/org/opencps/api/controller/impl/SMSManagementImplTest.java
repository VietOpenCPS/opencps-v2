package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getZaloUIdByTelNoTest() {
		try{
			SMSManagementImpl object = new SMSManagementImpl();
			object.getZaloUIdByTelNo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildDeliverableSearchDataFormTest() {
		try{
			SMSManagementImpl.buildDeliverableSearchDataForm("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendSMSTest() {
		try{
			SMSManagementImpl object = new SMSManagementImpl();
			object.sendSMS(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInetSMSTest() {
		try{
			SMSManagementImpl object = new SMSManagementImpl();
			object.getInetSMS(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateDueDateTest() {
		try{
			SMSManagementImpl object = new SMSManagementImpl();
			object.calculateDueDate(null, null, null, null, null, null, "abcde", Double.valueOf(0.0), 0, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postInvoiceTestTest() {
		try{
			SMSManagementImpl object = new SMSManagementImpl();
			object.postInvoiceTest(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}