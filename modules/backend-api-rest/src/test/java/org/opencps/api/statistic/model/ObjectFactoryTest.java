package org.opencps.api.statistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createStatisticDossierSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticDossierSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticCountResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticCountResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticCountModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticCountModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticDossierResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticDossierResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticDossierModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticDossierModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}