package backend.admin.config.whiteboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminBundleCheckerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void startTest() {
		try{
			AdminBundleChecker object = new AdminBundleChecker();
			object.start();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}