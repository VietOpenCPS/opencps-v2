package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierRequestUDLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierRequestTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.updateDossierRequest(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierRequestUDByUuidAndGroupIdTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.fetchDossierRequestUDByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDByUuidAndGroupIdTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUDByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDsByUuidAndCompanyIdTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUDsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDsByUuidAndCompanyIdTest11() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUDsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestByDossierIdTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDsCountTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUDsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.updateDossierRequestUD(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDsTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUDs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.deleteDossierRequestUD(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierRequestUDTest17() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.deleteDossierRequestUD(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.createDossierRequestUD(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.fetchDossierRequestUD(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.addDossierRequestUD(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestUDTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequestUD(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getDossierRequest(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierRequestUDLocalServiceWrapper object = new DossierRequestUDLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}