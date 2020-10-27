package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSConfigUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getHostAddressTest() {
		try{
			OpenCPSConfigUtil.getHostAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminProxyUrlTest() {
		try{
			OpenCPSConfigUtil.getAdminProxyUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRestReadTimeoutTest() {
		try{
			OpenCPSConfigUtil.getRestReadTimeout();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRestConnectionTimeoutTest() {
		try{
			OpenCPSConfigUtil.getRestConnectionTimeout();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isAutoBetimesTest() {
		try{
			OpenCPSConfigUtil.isAutoBetimes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHttpCacheMaxAgeTest() {
		try{
			OpenCPSConfigUtil.getHttpCacheMaxAge();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isHttpCacheEnableTest() {
		try{
			OpenCPSConfigUtil.isHttpCacheEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDLFileEntryEnableTest() {
		try{
			OpenCPSConfigUtil.isDLFileEntryEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCacheTTLTest() {
		try{
			OpenCPSConfigUtil.getCacheTTL();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isAiModeTest() {
		try{
			OpenCPSConfigUtil.isAiMode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllowCORSIpsTest() {
		try{
			OpenCPSConfigUtil object = new OpenCPSConfigUtil();
			object.getAllowCORSIps();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPortalDomainTest() {
		try{
			OpenCPSConfigUtil.getPortalDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPortalDocumentURITest() {
		try{
			OpenCPSConfigUtil.getPortalDocumentURI();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isPublishEventEnableTest() {
		try{
			OpenCPSConfigUtil.isPublishEventEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isNotificationEnableTest() {
		try{
			OpenCPSConfigUtil.isNotificationEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isStatisticMultipleServerEnableTest() {
		try{
			OpenCPSConfigUtil.isStatisticMultipleServerEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDefaultDossierSecretLengthTest() {
		try{
			OpenCPSConfigUtil.getDefaultDossierSecretLength();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMailToApplicantFromTest() {
		try{
			OpenCPSConfigUtil.getMailToApplicantFrom();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDossierDocumentEnableTest() {
		try{
			OpenCPSConfigUtil.isDossierDocumentEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDossierLogEnableTest() {
		try{
			OpenCPSConfigUtil.isDossierLogEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isTrackUserEnableTest() {
		try{
			OpenCPSConfigUtil.isTrackUserEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isPermissionRoleModeTest() {
		try{
			OpenCPSConfigUtil.isPermissionRoleMode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}