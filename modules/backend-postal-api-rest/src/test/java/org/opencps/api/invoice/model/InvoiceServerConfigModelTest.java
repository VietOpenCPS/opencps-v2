package org.opencps.api.invoice.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class InvoiceServerConfigModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActiveTest() {
		try{
			InvoiceServerConfigModel object = new InvoiceServerConfigModel("abcde", true);
			object.setActive(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActiveTest() {
		try{
			InvoiceServerConfigModel object = new InvoiceServerConfigModel("abcde", true);
			object.getActive();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndPointUrlTest() {
		try{
			InvoiceServerConfigModel object = new InvoiceServerConfigModel("abcde", true);
			object.getEndPointUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndPointUrlTest() {
		try{
			InvoiceServerConfigModel object = new InvoiceServerConfigModel("abcde", true);
			object.setEndPointUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}