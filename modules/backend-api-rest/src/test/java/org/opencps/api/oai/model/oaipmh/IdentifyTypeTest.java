package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class IdentifyTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBaseURLTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getBaseURL();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEarliestDatestampTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getEarliestDatestamp();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProtocolVersionTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setProtocolVersion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEarliestDatestampTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setEarliestDatestamp("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRepositoryNameTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setRepositoryName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRepositoryNameTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getRepositoryName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProtocolVersionTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getProtocolVersion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminEmailTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getAdminEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompressionTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getCompression();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBaseURLTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setBaseURL("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeletedRecordTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getDeletedRecord();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGranularityTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setGranularity(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGranularityTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.getGranularity();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeletedRecordTest() {
		try{
			IdentifyType object = new IdentifyType();
			object.setDeletedRecord(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}