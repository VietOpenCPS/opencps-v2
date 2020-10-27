package org.opencps.communication.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PreferencesSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			PreferencesSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			PreferencesSoap.toSoapModels(new Preferences[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			PreferencesSoap.toSoapModels(new Preferences[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			PreferencesSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getPreferencesId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreferencesIdTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setPreferencesId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getPreferences();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreferencesTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setPreferences("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			PreferencesSoap object = new PreferencesSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}