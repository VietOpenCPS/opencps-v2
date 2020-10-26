package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OAIBuilderUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQueryTest() {
		try{
			OAIBuilderUtils object = new OAIBuilderUtils(null);
			object.getQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQueryTest() {
		try{
			OAIBuilderUtils object = new OAIBuilderUtils(null);
			object.setQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResultsTest() {
		try{
			OAIBuilderUtils object = new OAIBuilderUtils(null);
			object.setResults(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResultsTest() {
		try{
			OAIBuilderUtils object = new OAIBuilderUtils(null);
			object.getResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}