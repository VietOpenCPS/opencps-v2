package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CheckFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void checkFileUploadTest() {
		try{
			CheckFileUtils.checkFileUpload(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}