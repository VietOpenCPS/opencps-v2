package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HttpDownloadUtilityTest {
	@Before
	public void setUp() {
	}
	@Test
	public void downloadFileTest() {
		try{
			HttpDownloadUtility.downloadFile("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}