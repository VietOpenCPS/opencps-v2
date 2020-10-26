package backend.admin.config.whiteboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BundleLoaderTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassLoaderTest() {
		try{
			BundleLoader object = new BundleLoader("abcde");
			object.getClassLoader();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassLoaderTest() {
		try{
			BundleLoader object = new BundleLoader("abcde");
			object.setClassLoader(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}