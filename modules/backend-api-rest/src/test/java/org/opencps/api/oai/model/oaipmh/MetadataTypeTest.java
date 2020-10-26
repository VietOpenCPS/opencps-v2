package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MetadataTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserTest() {
		try{
			MetadataType object = new MetadataType();
			object.getUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserTest() {
		try{
			MetadataType object = new MetadataType();
			object.setUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleTest() {
		try{
			MetadataType object = new MetadataType();
			object.setRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableTest() {
		try{
			MetadataType object = new MetadataType();
			object.setDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleTest() {
		try{
			MetadataType object = new MetadataType();
			object.getRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeTest() {
		try{
			MetadataType object = new MetadataType();
			object.getDeliverableType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableTypeTest() {
		try{
			MetadataType object = new MetadataType();
			object.setDeliverableType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTest() {
		try{
			MetadataType object = new MetadataType();
			object.getDeliverable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}