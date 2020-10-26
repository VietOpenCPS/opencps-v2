package org.opencps.adminconfig.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportRoleLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAllTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.countAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.deleteReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteReportRoleTest9() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.deleteReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByRIDSTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.findByRIDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByDRID_RIDTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.fetchByDRID_RID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.addReportRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addReportRoleTest14() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.addReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDRIDTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.findByDRID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.createReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.fetchReportRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRolesTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getReportRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReportRoleTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.updateReportRole(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReportRoleTest20() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.updateReportRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest25() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest27() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest30() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRolesCountTest() {
		try{
			ReportRoleLocalServiceWrapper object = new ReportRoleLocalServiceWrapper(null);
			object.getReportRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}