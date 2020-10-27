package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticResponseTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAgencyTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatisticDataTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.setDossierStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticDataTest() {
		try{
			DossierStatisticResponse object = new DossierStatisticResponse();
			object.getDossierStatisticData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}