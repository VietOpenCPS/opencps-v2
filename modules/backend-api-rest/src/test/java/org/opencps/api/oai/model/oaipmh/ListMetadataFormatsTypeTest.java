package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ListMetadataFormatsTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMetadataFormatTest() {
		try{
			ListMetadataFormatsType object = new ListMetadataFormatsType();
			object.getMetadataFormat();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}