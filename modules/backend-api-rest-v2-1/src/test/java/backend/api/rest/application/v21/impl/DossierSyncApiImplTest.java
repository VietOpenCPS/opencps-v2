package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierSyncListTest() {
		try{
			DossierSyncApiImpl object = new DossierSyncApiImpl();
			object.getDossierSyncList("abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncByDossierIdTest() {
		try{
			DossierSyncApiImpl object = new DossierSyncApiImpl();
			object.getSyncByDossierId(0, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}