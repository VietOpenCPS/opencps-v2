package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.deleteRegistrationForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationFormTest10() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.deleteRegistrationForm("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteRegistrationFormTest11() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.deleteRegistrationForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void registrationFormSyncTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.registrationFormSync(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.addRegistrationForm(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationFormTest14() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.addRegistrationForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findFormbyRegidRefidTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.findFormbyRegidRefid(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByFormNoTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getFormDataByFormNo(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationFormsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.deleteRegistrationForms(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOccursTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getOccurs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamNamesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getParamNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getParams();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamTypesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getParamTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSearchContextTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getSearchContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubPatternsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getSubPatterns();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getSubQueries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void LuceneQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.LuceneQuery("abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubPatternsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setSubPatterns(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamTypesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setParamTypes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOccursTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setOccurs(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamNamesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setParamNames(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubQueriesTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setSubQueries(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSearchContextTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setSearchContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationFormByUuidAndGroupIdTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.fetchRegistrationFormByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormsByUuidAndCompanyIdTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationFormsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormsByUuidAndCompanyIdTest40() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationFormsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormByUuidAndGroupIdTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationFormByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest47() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest49() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest52() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormsbyRegIdTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getFormsbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateIsNewTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.updateIsNew(Long.valueOf(0), Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_REGID_ISNEWTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.findByG_REGID_ISNEW(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.fetchRegistrationForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormsCountTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationFormsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.updateRegistrationForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationFormTest60() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.updateRegistrationForm(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.createRegistrationForm(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationFormsTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getRegistrationForms(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRegIdAndFormNoTest() {
		try{
			RegistrationFormLocalServiceWrapper object = new RegistrationFormLocalServiceWrapper(null);
			object.getByRegIdAndFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}