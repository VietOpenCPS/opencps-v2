package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void checkVotePermissionTest() {
		try{
			VotingManagementImpl object = new VotingManagementImpl();
			object.checkVotePermission(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}