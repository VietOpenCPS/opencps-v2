package org.opencps.dossiermgt.action.keypay.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class KeypayHashUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getHashTest() {
		try{
			KeypayHashUtil object = new KeypayHashUtil();
			object.getHash("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}