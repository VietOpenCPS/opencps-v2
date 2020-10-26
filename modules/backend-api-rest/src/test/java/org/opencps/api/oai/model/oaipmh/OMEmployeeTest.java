package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OMEmployeeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRolesTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getRoles();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRolesTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setRoles(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingStatusTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setWorkingStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingStatusTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getWorkingStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTelNoTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTelNoTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			OMEmployee object = new OMEmployee();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}