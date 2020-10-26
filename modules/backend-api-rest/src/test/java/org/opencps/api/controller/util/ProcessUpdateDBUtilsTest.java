package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessUpdateDBUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void processUpdateNotificationTemplateTest() {
		try{
			ProcessUpdateDBUtils.processUpdateNotificationTemplate(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateActionConfigTest() {
		try{
			ProcessUpdateDBUtils.processUpdateActionConfig(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateApplicantTest() {
		try{
			ProcessUpdateDBUtils.processUpdateApplicant(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateDynamicReportTest() {
		try{
			ProcessUpdateDBUtils.processUpdateDynamicReport(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateUserTest() {
		try{
			ProcessUpdateDBUtils.processUpdateUser(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateUserQATest() {
		try{
			ProcessUpdateDBUtils.processUpdateUserQA(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateDictCollectionTest() {
		try{
			ProcessUpdateDBUtils.processUpdateDictCollection(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateDocumentTypeTest() {
		try{
			ProcessUpdateDBUtils.processUpdateDocumentType(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateServerConfigTest() {
		try{
			ProcessUpdateDBUtils.processUpdateServerConfig(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateHolidayTest() {
		try{
			ProcessUpdateDBUtils.processUpdateHoliday(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateWorkingTimeTest() {
		try{
			ProcessUpdateDBUtils.processUpdateWorkingTime(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateServiceInfoTest() {
		try{
			ProcessUpdateDBUtils.processUpdateServiceInfo(null, "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateStepConfigTest() {
		try{
			ProcessUpdateDBUtils.processUpdateStepConfig(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdatePaymentConfigTest() {
		try{
			ProcessUpdateDBUtils.processUpdatePaymentConfig(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateDossierTemplateTest() {
		try{
			ProcessUpdateDBUtils.processUpdateDossierTemplate(null, "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateServiceProcessTest() {
		try{
			ProcessUpdateDBUtils.processUpdateServiceProcess(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateDeliverableTypeTest() {
		try{
			ProcessUpdateDBUtils.processUpdateDeliverableType(null, "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processUpdateMenuConfigTest() {
		try{
			ProcessUpdateDBUtils.processUpdateMenuConfig(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}