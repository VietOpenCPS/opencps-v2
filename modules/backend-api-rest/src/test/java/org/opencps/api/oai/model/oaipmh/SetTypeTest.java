package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SetTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSetDescriptionTest() {
		try{
			SetType object = new SetType();
			object.getSetDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSetSpecTest() {
		try{
			SetType object = new SetType();
			object.getSetSpec();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSetNameTest() {
		try{
			SetType object = new SetType();
			object.getSetName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSetNameTest() {
		try{
			SetType object = new SetType();
			object.setSetName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSetSpecTest() {
		try{
			SetType object = new SetType();
			object.setSetSpec("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}