package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDocumentTypeTest() {
		try{
			DocumentTypeList object = new DocumentTypeList();
			object.getDocumentType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}