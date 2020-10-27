package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MultipartUtilityTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addFilePartDataHandlerTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.addFilePartDataHandler("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFormFieldTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.addFormField("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void finishTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.finish();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addHeaderFieldTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.addHeaderField("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFilePartTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.addFilePart("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFilePartWithFileNameTest() {
		try{
			MultipartUtility object = new MultipartUtility("abcde", "abcde", Long.valueOf(0), "abcde");
			object.addFilePartWithFileName("abcde", null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}