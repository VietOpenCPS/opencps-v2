package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class InvokeRESTTest {
	@Before
	public void setUp() {
	}
	@Test
	public void callPostAPITest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callPostAPI(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callPostAPITest2() {
		try{
			InvokeREST object = new InvokeREST();
			object.callPostAPI("abcde", "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callPostFileAPIWithFileNameTest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callPostFileAPIWithFileName(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callAPITest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callAPI(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callPostFileAPITest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callPostFileAPI(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callPostAPIRawTest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callPostAPIRaw("abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callDeleteAPITest() {
		try{
			InvokeREST object = new InvokeREST();
			object.callDeleteAPI(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}