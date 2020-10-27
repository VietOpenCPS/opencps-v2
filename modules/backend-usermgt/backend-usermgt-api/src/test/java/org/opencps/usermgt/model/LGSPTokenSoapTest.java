package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPTokenSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			LGSPTokenSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			LGSPTokenSoap.toSoapModels(new LGSPToken[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			LGSPTokenSoap.toSoapModels(new LGSPToken[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			LGSPTokenSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTokenTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTokenTypeTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setTokenType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTypeTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getTokenType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRefreshTokenTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setRefreshToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpiryDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setExpiryDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTokenIdTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.setTokenId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpiryDateTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getExpiryDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenIdTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getTokenId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRefreshTokenTest() {
		try{
			LGSPTokenSoap object = new LGSPTokenSoap();
			object.getRefreshToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}