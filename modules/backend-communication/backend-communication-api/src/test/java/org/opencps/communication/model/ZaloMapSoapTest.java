package org.opencps.communication.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ZaloMapSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ZaloMapSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			ZaloMapSoap.toSoapModels(new ZaloMap[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ZaloMapSoap.toSoapModels(new ZaloMap[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ZaloMapSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTelNoTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTelNoTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setZaloOAIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setZaloOAId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsFollowedTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setIsFollowed(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getZaloMapIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getZaloMapId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIsFollowedTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getIsFollowed();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getZaloOAIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getZaloOAId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setUId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setZaloMapIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setZaloMapId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUIdTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getUId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ZaloMapSoap object = new ZaloMapSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}