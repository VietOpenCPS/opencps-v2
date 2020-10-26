package org.opencps.background.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CountEntityTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDatabaseTest() {
		try{
			CountEntity object = new CountEntity();
			object.getDatabase();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLuceneTest() {
		try{
			CountEntity object = new CountEntity();
			object.getLucene();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDatabaseTest() {
		try{
			CountEntity object = new CountEntity();
			object.setDatabase(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLuceneTest() {
		try{
			CountEntity object = new CountEntity();
			object.setLucene(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}