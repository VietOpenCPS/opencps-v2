package org.opencps.adminconfig.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleLocalServiceUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ReportRoleLocalServiceUtil.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ReportRoleLocalServiceUtil.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ReportRoleLocalServiceUtil.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ReportRoleLocalServiceUtil.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ReportRoleLocalServiceUtil.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ReportRoleLocalServiceUtil.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest7() {
		try{
			ReportRoleLocalServiceUtil.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ReportRoleLocalServiceUtil.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceTest() {
		try{
			ReportRoleLocalServiceUtil.getService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ReportRoleLocalServiceUtil.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest11() {
		try{
			ReportRoleLocalServiceUtil.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest12() {
		try{
			ReportRoleLocalServiceUtil.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest13() {
		try{
			ReportRoleLocalServiceUtil.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ReportRoleLocalServiceUtil.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAllTest() {
		try{
			ReportRoleLocalServiceUtil.countAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.addReportRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addReportRoleTest17() {
		try{
			ReportRoleLocalServiceUtil.addReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.createReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.deleteReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteReportRoleTest20() {
		try{
			ReportRoleLocalServiceUtil.deleteReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByDRID_RIDTest() {
		try{
			ReportRoleLocalServiceUtil.fetchByDRID_RID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.fetchReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDRIDTest() {
		try{
			ReportRoleLocalServiceUtil.findByDRID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.getReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByRIDSTest() {
		try{
			ReportRoleLocalServiceUtil.findByRIDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRolesTest() {
		try{
			ReportRoleLocalServiceUtil.getReportRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReportRoleTest() {
		try{
			ReportRoleLocalServiceUtil.updateReportRole(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReportRoleTest28() {
		try{
			ReportRoleLocalServiceUtil.updateReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRolesCountTest() {
		try{
			ReportRoleLocalServiceUtil.getReportRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}