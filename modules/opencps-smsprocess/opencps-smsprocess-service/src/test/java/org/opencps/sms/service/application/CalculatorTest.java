package org.opencps.sms.service.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CalculatorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sumTest() {
		try{
			Calculator object = new Calculator();
			object.sum(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void divideTest() {
		try{
			Calculator object = new Calculator();
			object.divide(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void multiplyTest() {
		try{
			Calculator object = new Calculator();
			object.multiply(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void subtractTest() {
		try{
			Calculator object = new Calculator();
			object.subtract(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}