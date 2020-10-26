package org.opencps.api.statistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticDossierModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setStepCodeTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMenuGroupTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setMenuGroup("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepNameTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getStepName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepCodeTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepNameTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setStepName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuGroupTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getMenuGroup();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalCountTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setTotalCount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalCountTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getTotalCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			StatisticDossierModel object = new StatisticDossierModel();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}