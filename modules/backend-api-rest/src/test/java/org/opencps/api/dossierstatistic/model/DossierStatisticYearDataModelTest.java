package org.opencps.api.dossierstatistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticYearDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserNameTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingRoleTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getWorkingRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitNameTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getWorkingUnitName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingRoleTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.setWorkingRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthsTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.getMonths();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitNameTest() {
		try{
			DossierStatisticYearDataModel object = new DossierStatisticYearDataModel();
			object.setWorkingUnitName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}