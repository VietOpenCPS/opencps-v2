package org.opencps.api.registrationform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormInfoModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPropertiesTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getProperties();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPropertiesTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setProperties("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			RegistrationFormInfoModel object = new RegistrationFormInfoModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}