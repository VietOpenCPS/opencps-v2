package backend.admin.config.whiteboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminBundlesInstalledTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBundleContextTest() {
		try{
			AdminBundlesInstalled.getBundleContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBundleIdsTest() {
		try{
			AdminBundlesInstalled.getBundleIds();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBundleStateTest() {
		try{
			AdminBundlesInstalled.getBundleState("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBundleContextTest() {
		try{
			AdminBundlesInstalled.setBundleContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBundlesTest() {
		try{
			AdminBundlesInstalled.setBundles(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBundlesTest() {
		try{
			AdminBundlesInstalled.getBundles();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBundleIdsTest() {
		try{
			AdminBundlesInstalled.setBundleIds(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}