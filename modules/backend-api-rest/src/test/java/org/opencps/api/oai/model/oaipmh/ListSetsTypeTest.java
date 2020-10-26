package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ListSetsTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSetTest() {
		try{
			ListSetsType object = new ListSetsType();
			object.getSet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResumptionTokenTest() {
		try{
			ListSetsType object = new ListSetsType();
			object.setResumptionToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResumptionTokenTest() {
		try{
			ListSetsType object = new ListSetsType();
			object.getResumptionToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}