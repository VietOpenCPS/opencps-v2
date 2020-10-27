package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateHolidayDBTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.updateHolidayDB(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHoliday(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchHolidayByUuidAndGroupIdTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.fetchHolidayByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidaiesByUuidAndCompanyIdTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidaiesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidaiesByUuidAndCompanyIdTest12() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidaiesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayByUuidAndGroupIdTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidayByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_holidayDateTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.fetchByF_holidayDate(Long.valueOf(0), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidaiesCountTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidaiesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayByGroupIdAndTypeTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidayByGroupIdAndType(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayByGroupIdTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidayByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.addHoliday(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addHolidayTest19() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.addHoliday(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.createHoliday(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayGtThanTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidayGtThan(Long.valueOf(0), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidaiesTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getHolidaies(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.updateHoliday(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateHolidayTest24() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.updateHoliday(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.fetchHoliday(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteHolidayTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.deleteHoliday(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteHolidayTest27() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.deleteHoliday(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteHolidayTest28() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.deleteHoliday(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest35() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest37() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest40() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			HolidayLocalServiceWrapper object = new HolidayLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}