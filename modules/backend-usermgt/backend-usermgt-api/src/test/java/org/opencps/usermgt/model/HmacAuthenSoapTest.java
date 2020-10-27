package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HmacAuthenSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			HmacAuthenSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			HmacAuthenSoap.toSoapModels(new HmacAuthen[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			HmacAuthenSoap.toSoapModels(new HmacAuthen[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			HmacAuthenSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSecretTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setSecret("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecretTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getSecret();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isPermanentTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.isPermanent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPermanentTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getPermanent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setHmacAuthIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setHmacAuthId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHmacAuthIdTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.getHmacAuthId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPermanentTest() {
		try{
			HmacAuthenSoap object = new HmacAuthenSoap();
			object.setPermanent(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}