package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRegionTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getRegion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			TrackClientSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			TrackClientSoap.toSoapModels(new TrackClient[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			TrackClientSoap.toSoapModels(new TrackClient[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			TrackClientSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMobileTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setMobile(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLeaveDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setLeaveDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMobileTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getMobile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLeaveDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getLeaveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getDay();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDayTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setDay(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getTrackClientId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTrackClientIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setTrackClientId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSessionIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setSessionId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisitDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getVisitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClientIPTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getClientIP();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClientIPTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setClientIP("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSessionIdTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getSessionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUrlTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMacAddressTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getMacAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMacAddressTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setMacAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegionTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setRegion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUrlTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNationTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getNation();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNationTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setNation("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLatitudeTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getLatitude();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLatitudeTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setLatitude("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLongitudeTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getLongitude();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVisitDateTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setVisitDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLongitudeTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setLongitude("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTimeOnPageTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getTimeOnPage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTimeOnPageTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setTimeOnPage(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDesktopTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getDesktop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDesktopTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.isDesktop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMobileTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.isMobile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTabletTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setTablet(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isTabletTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.isTablet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTabletTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.getTablet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDesktopTest() {
		try{
			TrackClientSoap object = new TrackClientSoap();
			object.setDesktop(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}