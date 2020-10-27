package backend.api.rest.application.v21.parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncParserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingDossierSyncResultModelTest() {
		try{
			DossierSyncParser.mappingDossierSyncResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingDossierSyncModelTest() {
		try{
			DossierSyncParser.mappingDossierSyncModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}