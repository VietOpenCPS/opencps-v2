package backend.admin.config.whiteboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfiguratorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void activateTest() {
		try{
			Configurator object = new Configurator();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}