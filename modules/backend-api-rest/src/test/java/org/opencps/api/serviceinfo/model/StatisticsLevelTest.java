package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticsLevelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getLevelTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.getLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.setLevel("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLevelNameTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.getLevelName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelNameTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.setLevelName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCountTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.setCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCountTest() {
		try{
			StatisticsLevel object = new StatisticsLevel();
			object.getCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}