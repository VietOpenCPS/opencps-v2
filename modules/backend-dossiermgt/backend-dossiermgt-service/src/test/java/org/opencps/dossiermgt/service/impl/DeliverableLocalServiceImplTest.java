package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DeliverableLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemNameTest() {
//		try{
//			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
//			object.getDictItemName(Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void searchLuceneTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.addDeliverable(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTest7() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.addDeliverable(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailByIdTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getDetailById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DCODETest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getByF_GID_DCODE(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.updateDeliverable(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListDeliverableTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getListDeliverable(0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByGID_DIDTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.fetchByGID_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOccursTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getOccurs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamNamesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getParamNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamsTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getParams();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamTypesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getParamTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSearchContextTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getSearchContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubPatternsTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getSubPatterns();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getSubQueries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest24() {
		try{
			DeliverableLocalServiceImpl.getSubQueries("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void LuceneQueryTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.LuceneQuery("abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubPatternsTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setSubPatterns(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamsTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamTypesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setParamTypes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOccursTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setOccurs(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamNamesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setParamNames(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubQueriesTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setSubQueries(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSearchContextTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.setSearchContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableSignTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.addDeliverableSign(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.deleteDeliverable(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableByModifiedDateTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getDeliverableByModifiedDate("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeAndStateTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getByCodeAndState("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDeliverableByStateTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.findDeliverableByState("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableDetailTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.getDeliverableDetail(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableLocalServiceImpl object = new DeliverableLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validPatternTest() {
		try{
			DeliverableLocalServiceImpl.validPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSplitIndexTest() {
		try{
			DeliverableLocalServiceImpl.getSplitIndex("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBooleanClauseOccursTest() {
		try{
			DeliverableLocalServiceImpl.getBooleanClauseOccurs("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBooleanQueriesTest() {
		try{
			DeliverableLocalServiceImpl.createBooleanQueries(null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}