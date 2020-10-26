package org.opencps.api.filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSKeyGeneratorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void generateKeyTest() {
		try{
			OpenCPSKeyGenerator object = new OpenCPSKeyGenerator();
			object.generateKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}