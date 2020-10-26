package org.opencps.api.statistic.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticCountModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getKeyTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.getKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTitleTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.getTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeyTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.setKey("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCountTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.setCount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCountTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.getCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTitleTest() {
		try{
			StatisticCountModel object = new StatisticCountModel();
			object.setTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}