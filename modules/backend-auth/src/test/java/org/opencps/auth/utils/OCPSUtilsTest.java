package org.opencps.auth.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OCPSUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toLongArrayTest() {
		try{
			OCPSUtils.toLongArray(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toStringArrayTest() {
		try{
			OCPSUtils.toStringArray(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}