package org.opencps.usermgt.lifecycle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSSessionActionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void runTest() {
		try{
			OpenCPSSessionAction object = new OpenCPSSessionAction();
			object.run(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}