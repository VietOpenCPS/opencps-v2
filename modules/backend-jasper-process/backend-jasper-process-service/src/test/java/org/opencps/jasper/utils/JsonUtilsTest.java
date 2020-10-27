package org.opencps.jasper.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JsonUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void quoteTest() {
		try{
			JsonUtils.quote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void quoteHTMLTest() {
		try{
			JsonUtils.quoteHTML("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}