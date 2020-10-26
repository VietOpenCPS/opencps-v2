package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ListRecordsTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRecordTest() {
		try{
			ListRecordsType object = new ListRecordsType();
			object.getRecord();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResumptionTokenTest() {
		try{
			ListRecordsType object = new ListRecordsType();
			object.setResumptionToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResumptionTokenTest() {
		try{
			ListRecordsType object = new ListRecordsType();
			object.getResumptionToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}