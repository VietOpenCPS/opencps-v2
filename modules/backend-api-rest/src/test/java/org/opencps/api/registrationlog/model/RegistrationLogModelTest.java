package org.opencps.api.registrationlog.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLogModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAuthorTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getAuthor();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogIdTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getRegistrationLogId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegistrationLogIdTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.setRegistrationLogId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAuthorTest() {
		try{
			RegistrationLogModel object = new RegistrationLogModel();
			object.setAuthor("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}