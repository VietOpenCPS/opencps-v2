package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.addConfigCounter(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getByGroupId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.updateConfigCounter(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateConfigCounterTest9() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.updateConfigCounter(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.fetchConfigCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.deleteConfigCounter(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteConfigCounterTest12() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.deleteConfigCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByGroupIdTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.countByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCountersByUuidAndCompanyIdTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCountersByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCountersByUuidAndCompanyIdTest16() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCountersByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchConfigCounterByUuidAndGroupIdTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.fetchConfigCounterByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createConfigCounterTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.createConfigCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCountersTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCounters(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterByUuidAndGroupIdTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCounterByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCountersCountTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getConfigCountersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByCountrCodeTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.fetchByCountrCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			ConfigCounterLocalServiceWrapper object = new ConfigCounterLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}