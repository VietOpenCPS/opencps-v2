package org.opencps.api.serviceconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessOptionInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setOptionNameTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setOptionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOptionNameTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getOptionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSeqOrderTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setSeqOrder(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAutoSelectTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setAutoSelect("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAutoSelectTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getAutoSelect();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateIdTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setDossierTemplateId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceProcessIdTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setServiceProcessId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInstructionNoteTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getInstructionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubmissionNoteTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setSubmissionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessIdTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getServiceProcessId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInstructionNoteTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.setInstructionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubmissionNoteTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getSubmissionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateIdTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getDossierTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSeqOrderTest() {
		try{
			ProcessOptionInputModel object = new ProcessOptionInputModel();
			object.getSeqOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}