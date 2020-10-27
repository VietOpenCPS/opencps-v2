package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverables(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.addDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTest12() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.addDeliverable(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTest13() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.addDeliverable(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailByIdTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDetailById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.fetchDeliverable(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DCODETest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getByF_GID_DCODE(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.updateDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTest21() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.updateDeliverable(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getListDeliverable(0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByGID_DIDTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.fetchByGID_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOccursTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getOccurs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamNamesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getParamNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamsTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getParams();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamTypesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getParamTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSearchContextTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getSearchContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubPatternsTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getSubPatterns();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getSubQueries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void LuceneQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.LuceneQuery("abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubPatternsTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setSubPatterns(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamsTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamTypesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setParamTypes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOccursTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setOccurs(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamNamesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setParamNames(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubQueriesTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setSubQueries(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSearchContextTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setSearchContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablesByUuidAndCompanyIdTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverablesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablesByUuidAndCompanyIdTest42() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverablesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableSignTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.addDeliverableSign(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.createDeliverable(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.deleteDeliverable(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTest46() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.deleteDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableByUuidAndGroupIdTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.fetchDeliverableByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablesCountTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverablesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableByModifiedDateTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverableByModifiedDate("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeAndStateTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getByCodeAndState("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDeliverableByStateTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.findDeliverableByState("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableByUuidAndGroupIdTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverableByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableDetailTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverableDetail(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getDeliverable(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest60() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest62() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest65() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableLocalServiceWrapper object = new DeliverableLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}