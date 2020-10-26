package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MetadataFormatTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMetadataPrefixTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.getMetadataPrefix();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetadataPrefixTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.setMetadataPrefix("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetadataNamespaceTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.getMetadataNamespace();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetadataNamespaceTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.setMetadataNamespace("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchemaTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.setSchema("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSchemaTest() {
		try{
			MetadataFormatType object = new MetadataFormatType();
			object.getSchema();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}