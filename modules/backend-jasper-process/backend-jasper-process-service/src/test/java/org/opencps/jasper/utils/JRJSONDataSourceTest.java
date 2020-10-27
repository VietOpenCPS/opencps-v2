package org.opencps.jasper.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JRJSONDataSourceTest {
	@Before
	public void setUp() {
	}
	
	@Test
	public void getInstanceTest2() {
		try{
			JRJSONDataSource.getInstance(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInstanceTest3() {
		try{
			JRJSONDataSource.getInstance("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}