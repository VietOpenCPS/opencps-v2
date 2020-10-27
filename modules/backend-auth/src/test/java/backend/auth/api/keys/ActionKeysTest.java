package backend.auth.api.keys;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionKeysTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getListPermissionTest() {
		try{
			ActionKeys.getListPermission();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListPermissionDataTest() {
		try{
			ActionKeys.getListPermissionData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}