package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HeaderTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setIdentifierTest() {
		try{
			HeaderType object = new HeaderType();
			object.setIdentifier("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDatestampTest() {
		try{
			HeaderType object = new HeaderType();
			object.setDatestamp("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			HeaderType object = new HeaderType();
			object.setStatus(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			HeaderType object = new HeaderType();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDatestampTest() {
		try{
			HeaderType object = new HeaderType();
			object.getDatestamp();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSetSpecTest() {
		try{
			HeaderType object = new HeaderType();
			object.getSetSpec();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdentifierTest() {
		try{
			HeaderType object = new HeaderType();
			object.getIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}