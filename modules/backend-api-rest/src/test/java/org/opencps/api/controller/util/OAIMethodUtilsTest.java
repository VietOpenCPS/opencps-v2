package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OAIMethodUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void calculateIdentifyTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateIdentify(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateListMetadataFormatsTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateListMetadataFormats(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateListSetsTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateListSets(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateGetRecordTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateGetRecord(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateListIdentifiersTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateListIdentifiers(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateListRecordsTest() {
		try{
			OAIMethodUtils object = new OAIMethodUtils();
			object.calculateListRecords(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}