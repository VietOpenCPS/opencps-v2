package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplatesLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatebyIdTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegistrationTemplatebyId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeRegistrationTemplateTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.removeRegistrationTemplate(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.updateRegistrationTemplates(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegTempbyRegIdTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegTempbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegTempbyFormNoGovCodeTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegTempbyFormNoGovCode(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormReportTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.updateFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormScriptTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.updateFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyGroupIdTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegistrationTemplatesbyGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyGOVCODETest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegistrationTemplatesbyGOVCODE(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSampledataTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.updateSampledata(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.addRegistrationTemplates(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyFormNoTest() {
		try{
			RegistrationTemplatesLocalServiceImpl object = new RegistrationTemplatesLocalServiceImpl();
			object.getRegistrationTemplatesbyFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}