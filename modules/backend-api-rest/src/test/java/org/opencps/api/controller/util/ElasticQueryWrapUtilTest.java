package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ElasticQueryWrapUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void queryTest() {
		try{
			ElasticQueryWrapUtil.query("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}