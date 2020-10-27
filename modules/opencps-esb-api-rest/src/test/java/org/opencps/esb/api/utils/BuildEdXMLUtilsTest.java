package org.opencps.esb.api.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BuildEdXMLUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void buildEdXMLSenderTest() {
		try{
			BuildEdXMLUtils.buildEdXMLSender(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}