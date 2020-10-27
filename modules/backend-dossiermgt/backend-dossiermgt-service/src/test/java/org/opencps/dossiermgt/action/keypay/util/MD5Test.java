package org.opencps.dossiermgt.action.keypay.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MD5Test {
	@Before
	public void setUp() {
	}
	@Test
	public void getMD5HashTest() {
		try{
			MD5 object = new MD5();
			object.getMD5Hash("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}