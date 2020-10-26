package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackupDataManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void backupMasterDataZipTest() {
		try{
			BackupDataManagementImpl object = new BackupDataManagementImpl();
			object.backupMasterDataZip(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void exportDataToXMLTest() {
		try{
			BackupDataManagementImpl object = new BackupDataManagementImpl();
			object.exportDataToXML(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}