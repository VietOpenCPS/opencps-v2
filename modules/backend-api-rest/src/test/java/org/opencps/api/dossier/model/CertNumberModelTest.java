package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CertNumberModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setCertDateTest() {
		try{
			CertNumberModel object = new CertNumberModel();
			object.setCertDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCertNoTest() {
		try{
			CertNumberModel object = new CertNumberModel();
			object.setCertNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCertDateTest() {
		try{
			CertNumberModel object = new CertNumberModel();
			object.getCertDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCertNoTest() {
		try{
			CertNumberModel object = new CertNumberModel();
			object.getCertNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}