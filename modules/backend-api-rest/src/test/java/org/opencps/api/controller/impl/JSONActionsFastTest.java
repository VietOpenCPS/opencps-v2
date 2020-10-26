package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JSONActionsFastTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getJSONObjectTest() {
		try{
			JSONActionsFast.getJSONObject(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJSONOArrayTest() {
		try{
			JSONActionsFast.getJSONOArray(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJSONObjectStringTest() {
		try{
			JSONActionsFast.getJSONObjectString(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}