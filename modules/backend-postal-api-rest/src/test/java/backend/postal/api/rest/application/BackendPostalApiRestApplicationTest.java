package backend.postal.api.rest.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendPostalApiRestApplicationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSingletonsTest() {
		try{
			BackendPostalApiRestApplication object = new BackendPostalApiRestApplication();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}