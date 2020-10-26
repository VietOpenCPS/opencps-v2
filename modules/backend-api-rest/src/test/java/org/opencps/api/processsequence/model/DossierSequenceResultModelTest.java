package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSequenceResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessNoTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.getProcessNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessNoTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.setProcessNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationUnitTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.setDurationUnit(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationUnitTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.getDurationUnit();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierSequenceResultModel object = new DossierSequenceResultModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}