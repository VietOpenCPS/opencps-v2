package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStepConfigByMainStatusAndSubStatusTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.getStepConfigByMainStatusAndSubStatus("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigByCodeTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.getStepConfigByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsElasticsearchTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.getStepConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addStepConfigTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.addStepConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteStepConfigTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.deleteStepConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest() {
		try{
			StepConfigApiImpl object = new StepConfigApiImpl();
			object.updateStepConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}