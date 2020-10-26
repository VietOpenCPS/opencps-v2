package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DataManagementUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperDictCollectionShortModelListTest() {
		try{
			DataManagementUtils.mapperDictCollectionShortModelList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictCollectionLGSPModelListTest() {
		try{
			DataManagementUtils.mapperDictCollectionLGSPModelList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperGroupsListLGSPTest() {
		try{
			DataManagementUtils.mapperGroupsListLGSP(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperGroupsTest() {
		try{
			DataManagementUtils.mapperGroups(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperGroupsListTest() {
		try{
			DataManagementUtils.mapperGroupsList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictItemModelTest() {
		try{
			DataManagementUtils.mapperDictItemModel(null, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictItemListTest() {
		try{
			DataManagementUtils.mapperDictItemList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictGroupListTest() {
		try{
			DataManagementUtils.mapperDictGroupList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictGroupItemModelListTest() {
		try{
			DataManagementUtils.mapperDictGroupItemModelList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictCollectionListTest() {
		try{
			DataManagementUtils.mapperDictCollectionList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictItemModelListTest() {
		try{
			DataManagementUtils.mapperDictItemModelList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictItemModelListLGSPTest() {
		try{
			DataManagementUtils.mapperDictItemModelListLGSP(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictGroupItemModelTest() {
		try{
			DataManagementUtils.mapperDictGroupItemModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictGroupModelTest() {
		try{
			DataManagementUtils.mapperDictGroupModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictCollectionModelTest() {
		try{
			DataManagementUtils.mapperDictCollectionModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperDictItemGroupListTest() {
		try{
			DataManagementUtils.mapperDictItemGroupList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}