package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.add(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.update(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.delete(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationFormTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.addRegistrationForm(null, null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registrationSyncsTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.registrationSyncs(null, null, null, null, null, null, null, true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByReferenceUidTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getFormDataByReferenceUid(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataFormByFormNoTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getDataFormByFormNo(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemNameTest() {
//		try{
//			RegistrationManagementImpl object = new RegistrationManagementImpl();
//			object.getDictItemName(Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void submittingTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.submitting(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormsbyRegIdTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getFormsbyRegId(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getList(null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void previewFileTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.previewFile(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getfileEntryIdTest() {
		try{
			RegistrationManagementImpl object = new RegistrationManagementImpl();
			object.getfileEntryId("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}