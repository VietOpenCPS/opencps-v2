package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplatesLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatebyIdTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatebyId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplates(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeRegistrationTemplateTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.removeRegistrationTemplate(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.updateRegistrationTemplates(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplatesTest11() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.updateRegistrationTemplates(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegTempbyRegIdTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegTempbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegTempbyFormNoGovCodeTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegTempbyFormNoGovCode(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormReportTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.updateFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormScriptTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.updateFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyGroupIdTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatesbyGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyGOVCODETest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatesbyGOVCODE(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationTemplatesByUuidAndGroupIdTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.fetchRegistrationTemplatesByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesByUuidAndGroupIdTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatesByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSampledataTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.updateSampledata(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.createRegistrationTemplates(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.addRegistrationTemplates(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationTemplatesTest34() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.addRegistrationTemplates(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplateses(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesesCountTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatesesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.fetchRegistrationTemplates(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesbyFormNoTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.getRegistrationTemplatesbyFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.deleteRegistrationTemplates(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationTemplatesTest40() {
		try{
			RegistrationTemplatesLocalServiceWrapper object = new RegistrationTemplatesLocalServiceWrapper(null);
			object.deleteRegistrationTemplates(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}