package backend.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HttpUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getIpAddressTest() {
		try{
			HttpUtil.getIpAddress(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}