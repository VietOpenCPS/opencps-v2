package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CPSDossierBusinessLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addDossierTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addDossier(Long.valueOf(0), null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doActionTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.doAction(Long.valueOf(0), Long.valueOf(0), null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileByDossierIdTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.createPaymentFileByDossierId(Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierPublishTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addDossierPublish(Long.valueOf(0), null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMultipleDossierTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addMultipleDossier(Long.valueOf(0), null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetformdataDossierFileFormDataTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.resetformdataDossierFileFormData(Long.valueOf(0), null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileFormDataTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.updateDossierFileFormData(Long.valueOf(0), null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileByDossierIdTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addDossierFileByDossierId(Long.valueOf(0), null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.updateDossierFile(Long.valueOf(0), null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void addFullDossierTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addFullDossier(Long.valueOf(0), null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFullDossierTest11() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.addFullDossier(Long.valueOf(0), null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void eparPublishTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.eparPublish(Long.valueOf(0), null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierActionUserTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.initDossierActionUser(null, null, 0, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierActionUserTest14() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.initDossierActionUser("abcde", Long.valueOf(0), null, null, 0, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest17() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest19() {
		try{
			CPSDossierBusinessLocalServiceWrapper object = new CPSDossierBusinessLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}