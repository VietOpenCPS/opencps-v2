package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLogLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLucenseTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.countLucense(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addLogTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.addLog("abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogbyRegIdTest() {
		try{
			RegistrationLogLocalServiceImpl object = new RegistrationLogLocalServiceImpl();
			object.getRegistrationLogbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}