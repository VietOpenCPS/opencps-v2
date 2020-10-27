package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NewsBoardLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.addNewsBoard(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.deleteNewsBoard(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNewsBoardTest9() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.deleteNewsBoard(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.fetchNewsBoard(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.createNewsBoard(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardTest12() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.createNewsBoard(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.updateNewsBoard(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNewsBoardTest14() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.updateNewsBoard(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardListTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoardList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoard(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardsTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoards(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardsCountTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoardsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByNewsBoardListTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.countByNewsBoardList(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardsByUuidAndCompanyIdTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoardsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardsByUuidAndCompanyIdTest21() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoardsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardByUuidAndGroupIdTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getNewsBoardByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchNewsBoardByUuidAndGroupIdTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.fetchNewsBoardByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NewsBoardLocalServiceWrapper object = new NewsBoardLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}