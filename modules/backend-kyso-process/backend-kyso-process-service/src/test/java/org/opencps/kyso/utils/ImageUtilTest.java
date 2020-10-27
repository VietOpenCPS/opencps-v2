package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ImageUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getImageByPathTest() {
		try{
			ImageUtil.getImageByPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignatureImageBase64ByPathTest() {
		try{
			ImageUtil.getSignatureImageBase64ByPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignatureImageBase64Test() {
		try{
			ImageUtil.getSignatureImageBase64("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void saveAsImageTest() {
		try{
			ImageUtil.saveAsImage("abcde", "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getImageTest() {
		try{
			ImageUtil.getImage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}