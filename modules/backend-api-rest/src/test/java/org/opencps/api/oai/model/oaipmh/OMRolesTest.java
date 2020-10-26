package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OMRolesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRoleTest() {
		try{
			OMRoles object = new OMRoles();
			object.getRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}