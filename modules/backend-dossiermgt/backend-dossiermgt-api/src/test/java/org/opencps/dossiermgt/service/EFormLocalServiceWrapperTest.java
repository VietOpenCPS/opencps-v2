package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EFormLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.updateEForm(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFormTest10() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.updateEForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByEFormNoTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getByEFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.fetchEForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.createEForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.addEForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEFormTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.deleteEForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEFormTest16() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.deleteEForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormsTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEForms(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormsCountTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEFormsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormsByUuidAndCompanyIdTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEFormsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormsByUuidAndCompanyIdTest20() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEFormsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEFormByUuidAndGroupIdTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.fetchEFormByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormByUuidAndGroupIdTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getEFormByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDataByEFormNoTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.updateDataByEFormNo(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			EFormLocalServiceWrapper object = new EFormLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}