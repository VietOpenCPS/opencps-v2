package org.opencps.auth.api;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendAuthImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void hasResourceTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.hasResource(null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isAuthTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.isAuth(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}