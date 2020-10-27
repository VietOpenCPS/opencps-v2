package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.updateApplicantData(null, Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest6() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.updateApplicantData(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest7() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.updateApplicantData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest8() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.updateApplicantData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.deleteApplicantData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantDataTest10() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.deleteApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDatasTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantDatas(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activeTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.active(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void inActiveTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.inActive(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.addApplicantData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.createApplicantData(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataTest18() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.createApplicantData(null, Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataTest19() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.createApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.fetchApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest27() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest29() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest32() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDataTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchApplicantDataByUuidAndGroupIdTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.fetchApplicantDataByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDatasByUuidAndCompanyIdTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantDatasByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDatasByUuidAndCompanyIdTest36() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantDatasByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DN_FTN_AINTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.findByG_DN_FTN_AIN(Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDataByUuidAndGroupIdTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantDataByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDatasCountTest() {
		try{
			ApplicantDataLocalServiceWrapper object = new ApplicantDataLocalServiceWrapper(null);
			object.getApplicantDatasCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}