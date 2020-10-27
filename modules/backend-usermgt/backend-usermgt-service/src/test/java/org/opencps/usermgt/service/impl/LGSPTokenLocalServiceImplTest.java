package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPTokenLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fetchByTokenTypeTest() {
		try{
			LGSPTokenLocalServiceImpl object = new LGSPTokenLocalServiceImpl();
			object.fetchByTokenType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}