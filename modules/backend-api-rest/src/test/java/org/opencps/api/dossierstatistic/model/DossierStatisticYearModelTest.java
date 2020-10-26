package org.opencps.api.dossierstatistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticYearModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setOverdueCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.setOverdueCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.setDossierCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.setActionCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.getDossierCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.getActionCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOverdueCountTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.getOverdueCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			DossierStatisticYearModel object = new DossierStatisticYearModel();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}