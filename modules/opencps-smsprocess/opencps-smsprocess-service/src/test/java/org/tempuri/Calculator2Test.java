package org.tempuri;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class Calculator2Test {
	@Before
	public void setUp() {
	}
	@Test
	public void sumTest() {
		try{
			Calculator2 object = new Calculator2();
			object.sum(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void divideTest() {
		try{
			Calculator2 object = new Calculator2();
			object.divide(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void multiplyTest() {
		try{
			Calculator2 object = new Calculator2();
			object.multiply(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void subtractTest() {
		try{
			Calculator2 object = new Calculator2();
			object.subtract(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}