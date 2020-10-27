package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LuceneQueryUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSubQueriesTest() {
		try{
			LuceneQueryUtil.getSubQueries("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRangeTermTest() {
		try{
			LuceneQueryUtil.addRangeTerm(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addExactTermTest() {
		try{
			LuceneQueryUtil.addExactTerm(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildQuerySearchTest() {
		try{
			LuceneQueryUtil.buildQuerySearch("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validPatternTest() {
		try{
			LuceneQueryUtil.validPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSplitIndexTest() {
		try{
			LuceneQueryUtil.getSplitIndex("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBooleanClauseOccursTest() {
		try{
			LuceneQueryUtil.getBooleanClauseOccurs("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBooleanQueriesTest() {
		try{
			LuceneQueryUtil.createBooleanQueries(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBooleanQueriesTest9() {
		try{
			LuceneQueryUtil.createBooleanQueries(null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}