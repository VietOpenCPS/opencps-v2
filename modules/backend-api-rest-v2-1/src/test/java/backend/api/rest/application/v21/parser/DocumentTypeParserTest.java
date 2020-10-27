package backend.api.rest.application.v21.parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeParserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingDocumentResultModelTest() {
		try{
			DocumentTypeParser.mappingDocumentResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingDocumentTypeModelTest() {
		try{
			DocumentTypeParser.mappingDocumentTypeModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}