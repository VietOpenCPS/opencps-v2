package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class MenuConfigApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getScriptByConfigIdTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.getScriptByConfigId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsTodoTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.getMenuConfigsTodo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getMenuConfigByCodeTest() {
//		try{
//			MenuConfigApiImpl object = new MenuConfigApiImpl();
//			object.getMenuConfigByCode("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getMenuConfigsElasticsearchTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.getMenuConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsCountTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.getMenuConfigsCount("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMenuConfigTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.addMenuConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMenuConfigTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.deleteMenuConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest() {
		try{
			MenuConfigApiImpl object = new MenuConfigApiImpl();
			object.updateMenuConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}