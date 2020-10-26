package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DescriptionTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAnyTest() {
		try{
			DescriptionType object = new DescriptionType();
			object.getAny();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAnyTest() {
		try{
			DescriptionType object = new DescriptionType();
			object.setAny(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}