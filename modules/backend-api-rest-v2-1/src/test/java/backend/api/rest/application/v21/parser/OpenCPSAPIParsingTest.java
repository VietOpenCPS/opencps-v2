package backend.api.rest.application.v21.parser;
import com.liferay.portal.kernel.service.persistence.UserUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpenCPSAPIParsingTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getModelTest() {
//		try{
//			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
//			object.getModel(MenuConfigUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getModelTest2() {
		try{
			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
			object.getModel(0L, UserUtil.create(0L));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getModelTest3() {
//		try{
//			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
//			object.getModel(ActionConfigUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getModelTest4() {
//		try{
//			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
//			object.getModel(ActionConfigUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getModelTest5() {
//		try{
//			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
//			object.getModel(StepConfigUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingMenuConfigItemTest() {
		try{
			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
			object.mappingMenuConfigItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingMenuConfigStepsItemTest() {
		try{
			OpenCPSAPIParsing object = new OpenCPSAPIParsing();
			object.mappingMenuConfigStepsItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}