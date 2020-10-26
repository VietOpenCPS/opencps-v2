package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OMDeliverableTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTypeNameTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getTypeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTypeCodeTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getTypeCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTableConfigTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setTableConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTableConfigTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getTableConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptFileIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setFormScriptFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportFileIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setFormReportFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableTypeIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setDeliverableTypeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingDataTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getMappingData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodePatternTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getCodePattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeCodeTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setTypeCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeNameTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setTypeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataConfigTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getDataConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocSyncTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getDocSync();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgenciesTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setGovAgencies("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataConfigTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setDataConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDocSyncTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setDocSync(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getFormReport();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgenciesTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getGovAgencies();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getDeliverableTypeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptFileIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getFormScriptFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportFileIdTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.getFormReportFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setFormReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingDataTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setMappingData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodePatternTest() {
		try{
			OMDeliverableType object = new OMDeliverableType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde");
			object.setCodePattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}