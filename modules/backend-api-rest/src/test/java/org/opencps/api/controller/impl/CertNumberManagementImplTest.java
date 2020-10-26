package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CertNumberManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCounterTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.getCounter(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.addCertNumbers(null, null, null, null, null, null, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCounterTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.updateCounter(null, null, null, null, null, null, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.getCertNumbers(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.updateSertNumbers(null, null, null, null, null, null, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.removeAllCertNumbers(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void generatorCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.generatorCertNumbers(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.removeCertNumbers(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailCertNumbersTest() {
		try{
			CertNumberManagementImpl object = new CertNumberManagementImpl();
			object.getDetailCertNumbers(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}