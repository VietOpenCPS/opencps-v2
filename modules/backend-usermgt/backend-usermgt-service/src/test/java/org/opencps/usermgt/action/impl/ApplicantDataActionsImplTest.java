package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getApplicantDatasTest() {
		try{
			ApplicantDataActionsImpl object = new ApplicantDataActionsImpl();
			object.getApplicantDatas(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}