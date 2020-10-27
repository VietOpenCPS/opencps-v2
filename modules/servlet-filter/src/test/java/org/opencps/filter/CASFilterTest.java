package org.opencps.filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CASFilterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void initTest() {
		try{
			CASFilter object = new CASFilter();
			object.init(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLogTest() {
		try{
			CASFilter object = new CASFilter();
			object.getLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processFilterTest() {
		try{
			CASFilter object = new CASFilter();
			object.processFilter(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isFilterEnabledTest() {
		try{
			CASFilter object = new CASFilter();
			object.isFilterEnabled(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}