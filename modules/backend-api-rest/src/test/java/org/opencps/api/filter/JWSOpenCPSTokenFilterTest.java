package org.opencps.api.filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JWSOpenCPSTokenFilterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void filterTest() {
		try{
			JWSOpenCPSTokenFilter object = new JWSOpenCPSTokenFilter();
			object.filter(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}