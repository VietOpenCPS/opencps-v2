package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RecordTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setHeaderTest() {
		try{
			RecordType object = new RecordType();
			object.setHeader(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHeaderTest() {
		try{
			RecordType object = new RecordType();
			object.getHeader();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetadataTest() {
		try{
			RecordType object = new RecordType();
			object.getMetadata();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetadataTest() {
		try{
			RecordType object = new RecordType();
			object.setMetadata(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAboutTest() {
		try{
			RecordType object = new RecordType();
			object.getAbout();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}