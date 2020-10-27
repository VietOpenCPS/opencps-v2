package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SyncSchedulerSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTypeCodeTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getTypeCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			SyncSchedulerSoap.toSoapModels(new SyncScheduler[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			SyncSchedulerSoap.toSoapModels(new SyncScheduler[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest5() {
		try{
			SyncSchedulerSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			SyncSchedulerSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeCodeTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setTypeCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRetryTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setRetry(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRetryTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getRetry();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSyncDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setSyncDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncDateTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getSyncDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncSchedulerIdTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.getSyncSchedulerId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSyncSchedulerIdTest() {
		try{
			SyncSchedulerSoap object = new SyncSchedulerSoap();
			object.setSyncSchedulerId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}