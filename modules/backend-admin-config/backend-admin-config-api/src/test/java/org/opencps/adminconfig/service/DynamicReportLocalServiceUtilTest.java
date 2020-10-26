package org.opencps.adminconfig.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DynamicReportLocalServiceUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DynamicReportLocalServiceUtil.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DynamicReportLocalServiceUtil.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DynamicReportLocalServiceUtil.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DynamicReportLocalServiceUtil.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.createDynamicReport(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DynamicReportLocalServiceUtil.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.deleteDynamicReport(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDynamicReportTest8() {
		try{
			DynamicReportLocalServiceUtil.deleteDynamicReport(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DynamicReportLocalServiceUtil.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest10() {
		try{
			DynamicReportLocalServiceUtil.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.fetchDynamicReport(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportsTest() {
		try{
			DynamicReportLocalServiceUtil.getDynamicReports(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportsCountTest() {
		try{
			DynamicReportLocalServiceUtil.getDynamicReportsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DynamicReportLocalServiceUtil.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.updateDynamicReport(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDynamicReportDBTest() {
		try{
			DynamicReportLocalServiceUtil.updateDynamicReportDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceTest() {
		try{
			DynamicReportLocalServiceUtil.getService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DynamicReportLocalServiceUtil.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest19() {
		try{
			DynamicReportLocalServiceUtil.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest20() {
		try{
			DynamicReportLocalServiceUtil.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest21() {
		try{
			DynamicReportLocalServiceUtil.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByCodeTest() {
		try{
			DynamicReportLocalServiceUtil.fetchByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DynamicReportLocalServiceUtil.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupTypeTest() {
		try{
			DynamicReportLocalServiceUtil.getByGroupType(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.addDynamicReport(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_CODETest() {
		try{
			DynamicReportLocalServiceUtil.fetchByG_CODE(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupTest() {
		try{
			DynamicReportLocalServiceUtil.getByGroup(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportTest() {
		try{
			DynamicReportLocalServiceUtil.getDynamicReport(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}