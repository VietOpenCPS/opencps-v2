package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessStepRoleLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.updateProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepRoleTest7() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.updateProcessStepRole(Long.valueOf(0), Long.valueOf(0), true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.removeProcessStepRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.addProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.deleteProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessStepRoleTest11() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.deleteProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void getProcessStepRolesTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getProcessStepRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByP_S_IDTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.findByP_S_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRoleCodeTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getByRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepRoleDBTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.updateProcessStepRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest20() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest22() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest25() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.fetchProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepRolesCountTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.getProcessStepRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStepAndRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.findByStepAndRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceWrapper object = new ProcessStepRoleLocalServiceWrapper(null);
			object.createProcessStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}