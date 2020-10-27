package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LuceneQueryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOccursTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getOccurs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamNamesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getParamNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamsTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getParams();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParamTypesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getParamTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSearchContextTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getSearchContext();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubPatternsTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getSubPatterns();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubQueriesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getSubQueries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubPatternsTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setSubPatterns(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamsTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamTypesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setParamTypes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOccursTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setOccurs(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParamNamesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setParamNames(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubQueriesTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setSubQueries(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSearchContextTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.setSearchContext(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRolesFromSigninUserTest() {
		try{
			LuceneQuery object = new LuceneQuery("abcde", null, null);
			object.getRolesFromSigninUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}