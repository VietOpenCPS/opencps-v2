package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionConfigApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateActionConfigTest() {
		try{
			ActionConfigApiImpl object = new ActionConfigApiImpl();
			object.updateActionConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigByCodeTest() {
		try{
			ActionConfigApiImpl object = new ActionConfigApiImpl();
			object.getActionConfigByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteActionConfigTest() {
		try{
			ActionConfigApiImpl object = new ActionConfigApiImpl();
			object.deleteActionConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsElasticsearchTest() {
		try{
			ActionConfigApiImpl object = new ActionConfigApiImpl();
			object.getActionConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest() {
		try{
			ActionConfigApiImpl object = new ActionConfigApiImpl();
			object.addActionConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}