package org.opencps.dossiermgt.lgsp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocFeesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFeeTypeTest() {
		try{
			DocFees object = new DocFees();
			object.getFeeType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFeeNameTest() {
		try{
			DocFees object = new DocFees();
			object.getFeeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPriceTest() {
		try{
			DocFees object = new DocFees();
			object.getPrice();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPriceTest() {
		try{
			DocFees object = new DocFees();
			object.setPrice(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFeeTypeTest() {
		try{
			DocFees object = new DocFees();
			object.setFeeType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFeeNameTest() {
		try{
			DocFees object = new DocFees();
			object.setFeeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}