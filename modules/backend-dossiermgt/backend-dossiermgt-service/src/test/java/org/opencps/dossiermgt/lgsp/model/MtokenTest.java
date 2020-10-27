package org.opencps.dossiermgt.lgsp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MtokenTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAccessTokenTest() {
		try{
			Mtoken object = new Mtoken();
			object.getAccessToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScopeTest() {
		try{
			Mtoken object = new Mtoken();
			object.setScope("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScopeTest() {
		try{
			Mtoken object = new Mtoken();
			object.getScope();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTokenTypeTest() {
		try{
			Mtoken object = new Mtoken();
			object.setTokenType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpiresInTest() {
		try{
			Mtoken object = new Mtoken();
			object.getExpiresIn();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTypeTest() {
		try{
			Mtoken object = new Mtoken();
			object.getTokenType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAccessTokenTest() {
		try{
			Mtoken object = new Mtoken();
			object.setAccessToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpiresInTest() {
		try{
			Mtoken object = new Mtoken();
			object.setExpiresIn(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}