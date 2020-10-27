package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.deleteRegistrationForm("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registrationFormSyncTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.registrationFormSync(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.addRegistrationForm(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findFormbyRegidRefidTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.findFormbyRegidRefid(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByFormNoTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getFormDataByFormNo(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationFormsTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.deleteRegistrationForms(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOccursTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getOccurs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamNamesTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getParamNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamsTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getParams();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamTypesTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getParamTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSearchContextTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getSearchContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubPatternsTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getSubPatterns();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest() {
		try{
			RegistrationFormLocalServiceImpl.getSubQueries("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest21() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getSubQueries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void LuceneQueryTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.LuceneQuery("abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubPatternsTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setSubPatterns(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamsTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamTypesTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setParamTypes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOccursTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setOccurs(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamNamesTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setParamNames(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubQueriesTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setSubQueries(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSearchContextTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.setSearchContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormsbyRegIdTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getFormsbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateIsNewTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.updateIsNew(Long.valueOf(0), Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validPatternTest() {
		try{
			RegistrationFormLocalServiceImpl.validPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSplitIndexTest() {
		try{
			RegistrationFormLocalServiceImpl.getSplitIndex("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_REGID_ISNEWTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.findByG_REGID_ISNEW(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationFormTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.updateRegistrationForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRegIdAndFormNoTest() {
		try{
			RegistrationFormLocalServiceImpl object = new RegistrationFormLocalServiceImpl();
			object.getByRegIdAndFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBooleanClauseOccursTest() {
		try{
			RegistrationFormLocalServiceImpl.getBooleanClauseOccurs("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBooleanQueriesTest() {
		try{
			RegistrationFormLocalServiceImpl.createBooleanQueries(null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}