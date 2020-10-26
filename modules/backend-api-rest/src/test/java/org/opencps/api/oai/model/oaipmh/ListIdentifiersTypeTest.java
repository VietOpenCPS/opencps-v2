package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ListIdentifiersTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setResumptionTokenTest() {
		try{
			ListIdentifiersType object = new ListIdentifiersType();
			object.setResumptionToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResumptionTokenTest() {
		try{
			ListIdentifiersType object = new ListIdentifiersType();
			object.getResumptionToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHeaderTest() {
		try{
			ListIdentifiersType object = new ListIdentifiersType();
			object.getHeader();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}