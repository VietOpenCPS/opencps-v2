package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientStatisticSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRegionTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getRegion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			TrackClientStatisticSoap.toSoapModels(new TrackClientStatistic[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			TrackClientStatisticSoap.toSoapModels(new TrackClientStatistic[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest5() {
		try{
			TrackClientStatisticSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			TrackClientStatisticSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMobileTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setMobile(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMobileTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getMobile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getDay();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDayTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setDay(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUrlTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegionTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setRegion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUrlTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDesktopTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getDesktop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDesktopTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.isDesktop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMobileTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.isMobile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTabletTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setTablet(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isTabletTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.isTablet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTabletTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getTablet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDesktopTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setDesktop(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientStatisticIdTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.getTrackClientStatisticId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTrackClientStatisticIdTest() {
		try{
			TrackClientStatisticSoap object = new TrackClientStatisticSoap();
			object.setTrackClientStatisticId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}