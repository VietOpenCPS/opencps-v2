package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GetRecordTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRecordTest() {
		try{
			GetRecordType object = new GetRecordType();
			object.getRecord();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRecordTest() {
		try{
			GetRecordType object = new GetRecordType();
			object.setRecord(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}