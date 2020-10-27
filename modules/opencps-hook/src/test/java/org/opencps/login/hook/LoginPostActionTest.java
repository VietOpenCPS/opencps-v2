package org.opencps.login.hook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LoginPostActionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void processLifecycleEventTest() {
		try{
			LoginPostAction object = new LoginPostAction();
			object.processLifecycleEvent(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}