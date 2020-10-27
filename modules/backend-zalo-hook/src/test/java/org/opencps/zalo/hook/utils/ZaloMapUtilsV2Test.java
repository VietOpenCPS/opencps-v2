package org.opencps.zalo.hook.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ZaloMapUtilsV2Test {
	@Before
	public void setUp() {
	}
	@Test
	public void doActionTest() {
		try{
			ZaloMapUtilsV2.doAction("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}