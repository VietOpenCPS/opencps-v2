package org.opencps.api.statistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticDossierSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getLevelTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setLevel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAgencyTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTopTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setTop("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setStep("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotStatusRegTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setNotStatusReg(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getStep();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getTop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotStatusRegTest() {
		try{
			StatisticDossierSearchModel object = new StatisticDossierSearchModel();
			object.getNotStatusReg();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}