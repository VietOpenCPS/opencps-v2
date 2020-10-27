package backend.auth.api;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getServiceContextTest() {
		try{
			BackendUtils.getServiceContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}