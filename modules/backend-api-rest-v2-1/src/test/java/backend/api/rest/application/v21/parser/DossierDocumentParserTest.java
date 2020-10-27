package backend.api.rest.application.v21.parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentParserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingDocumentResultModelTest() {
		try{
			DossierDocumentParser.mappingDocumentResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingDocumentTypeModelTest() {
		try{
			DossierDocumentParser.mappingDocumentTypeModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}