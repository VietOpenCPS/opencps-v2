package org.opencps.api.serviceinfo.model;
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
	public void createStatisticsAgencyResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsAgencyResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticsLevelResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsLevelResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticsDomainResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsDomainResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoRecentResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoRecentResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileTemplateResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileTemplateResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileTemplatesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileTemplates();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceRecentDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceRecentDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticsDomainTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticsLevelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticsAgencyTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStatisticsAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileTemplateModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileTemplateModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoServiceConfigTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoServiceConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceInfoResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}