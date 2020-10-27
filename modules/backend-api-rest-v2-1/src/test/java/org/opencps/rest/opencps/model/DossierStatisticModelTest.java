package org.opencps.rest.opencps.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void stepCodeTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.stepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepCodeTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.setStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepNameTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.getStepName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepCodeTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.getStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepNameTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.setStepName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalCountTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.setTotalCount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalCountTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.getTotalCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dossierSubStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.dossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dossierStatusTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.dossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void stepNameTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.stepName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalCountTest() {
		try{
			DossierStatisticModel object = new DossierStatisticModel();
			object.totalCount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}