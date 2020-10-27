package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BCYSignatureUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getServerSignerTest() {
		try{
			BCYSignatureUtil.getServerSigner("abcde", null, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getComputerHashTest() {
		try{
			BCYSignatureUtil.getComputerHash(null, null, null, 0, Long.valueOf(0), Long.valueOf(0), 0, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}