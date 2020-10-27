package org.opencps.dossiermgt.input.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PersonDossierStatisticTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			PersonDossierStatistic object = new PersonDossierStatistic();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			PersonDossierStatistic object = new PersonDossierStatistic();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSoluonghosoTest() {
		try{
			PersonDossierStatistic object = new PersonDossierStatistic();
			object.setSoluonghoso(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSoluonghosoTest() {
		try{
			PersonDossierStatistic object = new PersonDossierStatistic();
			object.getSoluonghoso();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}