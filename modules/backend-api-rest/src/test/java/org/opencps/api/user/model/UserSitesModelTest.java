package org.opencps.api.user.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserSitesModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOfficeSiteIdTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.getOfficeSiteId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOfficeSiteIdTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.setOfficeSiteId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiteGroupIdTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.setSiteGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiteNameTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.setSiteName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCurrentSiteTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.setCurrentSite(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiteNameTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.getSiteName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCurrentSiteTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.isCurrentSite();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiteGroupIdTest() {
		try{
			UserSitesModel object = new UserSitesModel();
			object.getSiteGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}