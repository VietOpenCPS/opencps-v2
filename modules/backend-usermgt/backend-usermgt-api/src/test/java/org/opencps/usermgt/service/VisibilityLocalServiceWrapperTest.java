package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VisibilityLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibility(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilitiesByUuidAndCompanyIdTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibilitiesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilitiesByUuidAndCompanyIdTest19() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibilitiesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchVisibilityByUuidAndGroupIdTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.fetchVisibilityByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilityByUuidAndGroupIdTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibilityByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilitiesCountTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibilitiesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.updateVisibility(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.deleteVisibility(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteVisibilityTest25() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.deleteVisibility(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.createVisibility(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.fetchVisibility(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVisibilitiesTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.getVisibilities(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addVisibilityTest() {
		try{
			VisibilityLocalServiceWrapper object = new VisibilityLocalServiceWrapper(null);
			object.addVisibility(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}