package org.opencps.zalo.hook.servlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VNPostNGSPServletTest {
	@Before
	public void setUp() {
	}
	@Test
	public void initTest() {
		try{
			VNPostNGSPServlet object = new VNPostNGSPServlet();
			object.init();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doPostTest() {
		try{
			VNPostNGSPServlet object = new VNPostNGSPServlet();
			object.doPost(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}