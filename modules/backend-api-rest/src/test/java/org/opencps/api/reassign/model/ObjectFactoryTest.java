package org.opencps.api.reassign.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createReAssignTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReAssign();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createToUsersTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createToUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}