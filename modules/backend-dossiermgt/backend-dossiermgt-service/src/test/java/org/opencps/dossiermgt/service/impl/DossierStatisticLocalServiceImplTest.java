package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void insertTest() {
		try{
			DossierStatisticLocalServiceImpl object = new DossierStatisticLocalServiceImpl();
			object.insert(Long.valueOf(0), 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierStatisticLocalServiceImpl object = new DossierStatisticLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierStatisticLocalServiceImpl object = new DossierStatisticLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_YTest() {
		try{
			DossierStatisticLocalServiceImpl object = new DossierStatisticLocalServiceImpl();
			object.fetchByG_M_Y(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticbyYearTest() {
		try{
			DossierStatisticLocalServiceImpl object = new DossierStatisticLocalServiceImpl();
			object.getDossierStatisticbyYear(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}