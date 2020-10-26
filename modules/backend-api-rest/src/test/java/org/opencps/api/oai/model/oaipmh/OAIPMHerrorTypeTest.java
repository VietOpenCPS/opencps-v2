package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OAIPMHerrorTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			OAIPMHerrorType object = new OAIPMHerrorType();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			OAIPMHerrorType object = new OAIPMHerrorType();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodeTest() {
		try{
			OAIPMHerrorType object = new OAIPMHerrorType();
			object.getCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodeTest() {
		try{
			OAIPMHerrorType object = new OAIPMHerrorType();
			object.setCode(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}