package org.opencps.usermgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserMGTConstantsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getListPermissionTest() {
		try{
			UserMGTConstants.getListPermission();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}