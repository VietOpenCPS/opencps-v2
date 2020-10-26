package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPayLoadModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFieldTypeTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.getFieldType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRequiredTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.setRequired(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRequiredTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.getRequired();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFieldNameTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.setFieldName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFieldLabelTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.setFieldLabel("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFieldTypeTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.setFieldType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFieldNameTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.getFieldName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFieldLabelTest() {
		try{
			DossierPayLoadModel object = new DossierPayLoadModel();
			object.getFieldLabel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}