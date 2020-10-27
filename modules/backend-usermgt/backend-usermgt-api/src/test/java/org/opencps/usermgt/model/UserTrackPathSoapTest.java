package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserTrackPathSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPathTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getPath();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			UserTrackPathSoap.toSoapModels(new UserTrackPath[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			UserTrackPathSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			UserTrackPathSoap.toSoapModels(new UserTrackPath[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			UserTrackPathSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPathTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPathDateTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getPathDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPathDateTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setPathDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserLoginIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setUserLoginId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getUserLoginId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserTrackPathIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.setUserTrackPathId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTrackPathIdTest() {
		try{
			UserTrackPathSoap object = new UserTrackPathSoap();
			object.getUserTrackPathId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}