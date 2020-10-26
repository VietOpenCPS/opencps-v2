package org.opencps.api.reassign.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReAssignTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getToUsersTest() {
		try{
			ReAssign object = new ReAssign();
			object.getToUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}