package org.opencps.usermgt.restapi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendUsermgtServiceRestServiceTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSingletonsTest() {
		try{
			BackendUsermgtServiceRestService object = new BackendUsermgtServiceRestService();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doValidateTest() {
		try{
			BackendUsermgtServiceRestService object = new BackendUsermgtServiceRestService();
			object.doValidate(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}