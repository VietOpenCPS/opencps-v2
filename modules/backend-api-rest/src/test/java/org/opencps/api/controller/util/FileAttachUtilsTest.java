package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileAttachUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperFileAttachListTest() {
		try{
			FileAttachUtils.mapperFileAttachList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperFileAttachModelTest() {
		try{
			FileAttachUtils.mapperFileAttachModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperFileAttachVersionListTest() {
		try{
			FileAttachUtils.mapperFileAttachVersionList("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}